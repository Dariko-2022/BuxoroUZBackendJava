package uz.dariko.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
public class ApplicationError {
    private String code;
    private String path;
    private Integer status;
    private String message;
    private LocalDateTime time;


    public ApplicationError() {
    }

    public ApplicationError(String message, WebRequest webRequest, HttpStatus httpStatus) {
        this(message, ((ServletWebRequest) webRequest).getRequest().getRequestURI(), httpStatus);
    }

    public ApplicationError(String message, String path, HttpStatus httpStatus) {
        this.path = path;
        this.message = message;
        this.time = LocalDateTime.now();
        this.status = httpStatus.value();
        this.code = httpStatus.getReasonPhrase();
    }

    @Builder
    public ApplicationError(HttpStatus status, String message, String path) {
        this.path = path;
        this.message = message;
        this.status = status.value();
        this.time = LocalDateTime.now();
        this.code = status.getReasonPhrase();
    }

}
