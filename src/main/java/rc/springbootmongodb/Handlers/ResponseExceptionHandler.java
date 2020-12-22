package rc.springbootmongodb.Handlers;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rc.springbootmongodb.Exceptions.ServiceException;
import rc.springbootmongodb.Models.RestException;

@ControllerAdvice
public class ResponseExceptionHandler {
    @ExceptionHandler(value
            = { ServiceException.class })
    protected ResponseEntity<RestException> handleConflict(
            ServiceException ex) {

        RestException exception = new RestException(ex.getMessage(), ex.getCode());

        return new ResponseEntity<>(exception, HttpStatus.CONFLICT);
    }


}
