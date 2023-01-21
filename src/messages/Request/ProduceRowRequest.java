package messages.Request;

public class ProduceRowRequest extends Request {
    private String[] row;
    private boolean last;


    public ProduceRowRequest(String line, boolean last) {
        row = line.split("\\s*,\\s*");
        this.last = last;
    }

    @Override
    public String getPath() {
        return "stations/" + System.getProperty("station") + "/produce/single";
    }


    public String[] getRow() {
        return row;
    }

}
