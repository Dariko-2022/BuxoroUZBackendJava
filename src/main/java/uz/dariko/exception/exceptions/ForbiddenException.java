package uz.dariko.exception.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ForbiddenException extends RuntimeException {
    private String message;
    private Integer status = HttpStatus.FORBIDDEN.value();


    public ForbiddenException(String message) {
        this.message = message;
    }
}
