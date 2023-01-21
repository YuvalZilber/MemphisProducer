package messages.Responses;

public class JwtResponse {
    private String jwt;
    private double expires_in;

    public JwtResponse(String jwt, double expires_in) {
        this.jwt = jwt;
        this.expires_in = expires_in;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public double getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(double expires_in) {
        this.expires_in = expires_in;
    }
}
