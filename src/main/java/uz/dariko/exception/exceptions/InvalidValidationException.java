package uz.dariko.exception.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class InvalidValidationException extends RuntimeException {
    private String message;
    private Integer status=HttpStatus.BAD_REQUEST.value();

    public InvalidValidationException(String message) {
        this.message = message;
    }
}
