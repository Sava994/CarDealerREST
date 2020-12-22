package rc.springbootmongodb.Exceptions;

public class ServiceException extends Exception{
    private String message;
    private String code;

    public ServiceException(String code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
