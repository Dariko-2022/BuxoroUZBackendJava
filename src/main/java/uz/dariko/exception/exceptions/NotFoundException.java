package uz.dariko.exception.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class NotFoundException extends RuntimeException {
    private String message;
    private Integer status = HttpStatus.NOT_FOUND.value();

    public NotFoundException(String message) {
        this.message = message;
    }
}
