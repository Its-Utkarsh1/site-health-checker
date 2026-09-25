public class Result {
    private String url;
    private int statusCode;
    private long responseTime;
    private String error;

    public Result(String url, int statusCode, long responseTime, String error) {
        this.url = url;
        this.statusCode = statusCode;
        this.responseTime = responseTime;
        this.error = error;
    }

    public boolean isSuccess() {
        return error == null;
    }

    public String getUrl()         { return url; }
    public int getStatusCode()     { return statusCode; }
    public long getResponseTimeMs(){ return responseTime; }
    public String getError()       { return error; }

    @Override
    public String toString() {
        if (isSuccess()) {
            return url + "  " + statusCode + "  " + responseTime + "ms";
        }
        return url + "  FAILED  " + error;
    }
}