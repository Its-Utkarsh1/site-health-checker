public class Result {
    private String url;
    private int statusCode;
    private long responseTime;
    private String error;
    private String sslStatus;

    public Result(String url, int statusCode, long responseTime, String error, String sslStatus) {
        this.url = url;
        this.statusCode = statusCode;
        this.responseTime = responseTime;
        this.error = error;
        this.sslStatus = sslStatus;
    }

    public boolean isSuccess() {
        return error == null;
    }

    public String getUrl(){
        return url;
    }
    public int getStatusCode(){
        return statusCode;
    }
    public long getResponseTimeMs(){
        return responseTime;
    }
    public String getError(){
        return error;
    }
    public String getSslStatus(){
        return sslStatus;
    }

    @Override
    public String toString() {
        if (isSuccess()) {
            return url + "  " + statusCode + "  " + responseTime + "ms" + sslStatus;
        }
        return url + "  FAILED  " + error;
    }
}