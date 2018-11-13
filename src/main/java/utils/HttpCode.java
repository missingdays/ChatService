package utils;

public enum HttpCode {

    OK(200),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    METHOD_NOT_ALLOWED(405),
    INTERNAL_SERVER_ERROR(500);
    private HttpCode(int value){
        this.value = value;
    }
    private int value;

    public int getValue() {
        return value;
    }
}
