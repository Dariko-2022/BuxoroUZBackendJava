package uz.dariko.exception.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UniversalException extends RuntimeException {
    private String message;
    private Integer status;

    public UniversalException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.status = httpStatus.value();
    }
}
