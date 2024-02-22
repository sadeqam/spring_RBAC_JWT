package ir.sadeqam.spring_RBAC_JWT.controller.customization;

import ir.sadeqam.spring_RBAC_JWT.service.exceptions.DataNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDataNotFoundException(DataNotFoundException e) {
        return new ResponseEntity<>(
                generateResponse(e),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
                generateResponse(e),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Map<String, String>> handleGeneralThrowable(Throwable throwable) {
        return new ResponseEntity<>(
                generateResponse(throwable),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }


    private HashMap<String, String> generateResponse(Throwable e) {
        var response = new HashMap<String, String>();
        response.put("time", new Date().toString());
        response.put("message", e.getMessage());
        return response;
    }
}
