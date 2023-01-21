package Memphis;

import com.google.gson.*;
import messages.Request.AuthenticateRequest;
import messages.Request.Request;
import messages.Responses.JwtResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import Logger.Logger;

public class Memphis {

    private String jwt;

    private Memphis() throws IOException {
        this.authenticate();
        Logger.log(jwt);
    }

    private static class SingletonHolder {
        private static final Memphis instance;

        static {
            try {
                instance = new Memphis();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static Memphis getInstance() {
        return Memphis.SingletonHolder.instance;
    }

    public void authenticate() throws IOException {
        AuthenticateRequest au = new AuthenticateRequest("root", "memphis", 60, 10000092);

        JwtResponse jwtResponse = sendData(au, JwtResponse.class);
        this.jwt = jwtResponse.getJwt();

    }

    private String readResponse(HttpURLConnection connection) throws IOException {
        String response;
        try (InputStream is = connection.getInputStream()) {
            byte[] output = is.readAllBytes();
            response = new String(output);
        }

        return response;
    }

    private HttpURLConnection createConnection(String path) throws IOException {
        URL url = new URL("http://localhost:4444/" + path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(true);
        if (this.jwt != null) connection.setRequestProperty("Authorization", "Bearer " + jwt);
        return connection;
    }

    public <T> T sendData(Request message, Class<T> responseClass) throws IOException {

        HttpURLConnection connection = createConnection(message.getPath());
        String data = message.jsonifyMessage();
        Logger.log("request", data);
        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        int length = out.length;
        connection.setFixedLengthStreamingMode(length);
        connection.connect();
        try (OutputStream os = connection.getOutputStream()) {
            os.write(out);
        }
        String responseString = readResponse(connection);
        Logger.log("response", responseString);

        return new Gson().fromJson(responseString, responseClass);
    }

}
