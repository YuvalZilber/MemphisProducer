package messages.Request;

import com.google.gson.Gson;

import java.util.UUID;

public abstract class Request {
    private final String msgId;

    public Request() {
        msgId = UUID.randomUUID().toString();
    }

    public abstract String getPath();

    public String jsonifyMessage() {
        return new Gson().toJson(this);
    }
}
