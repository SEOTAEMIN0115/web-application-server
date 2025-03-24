package servlet.response.enums;

public enum StatusCode {
    OK(200), FOUND(302);

    private final int statusCode;
    private StatusCode(int statusCode){
        this.statusCode =statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
