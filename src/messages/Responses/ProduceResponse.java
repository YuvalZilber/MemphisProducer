package messages.Responses;

public class ProduceResponse {
    private String error;
    private boolean success;

    public ProduceResponse(String error, boolean success) {
        this.error = error;
        this.success = success;
    }
}
