package ir.sadeqam.spring_RBAC_JWT.service.exceptions;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(String message) {
        super(message);
    }
}
