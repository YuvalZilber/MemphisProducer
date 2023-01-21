package messages.Request;

public class AuthenticateRequest extends Request {
    private final String username;
    private final String connection_token;
    private final int token_expiry_in_minutes;
    private final int refresh_token_expiry_in_minutes;

    public AuthenticateRequest(String username, String connection_token, int token_expiry_in_minutes, int refresh_token_expiry_in_minutes) {
        this.username = username;
        this.connection_token = connection_token;
        this.token_expiry_in_minutes = token_expiry_in_minutes;
        this.refresh_token_expiry_in_minutes = refresh_token_expiry_in_minutes;
    }

    public String getPath() {
        return "auth/authenticate";
    }

}
