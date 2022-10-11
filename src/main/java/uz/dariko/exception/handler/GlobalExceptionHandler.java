package uz.dariko.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.dariko.exception.exceptions.*;
import uz.dariko.response.ApplicationError;
import uz.dariko.response.Data;

import java.time.LocalDateTime;

@RestControllerAdvice()
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({InvalidValidationException.class})
    public ResponseEntity<Data<ApplicationError>> invalidExceptionHandler(InvalidValidationException exception, WebRequest webRequest) {
        ApplicationError applicationError = new ApplicationError();
        applicationError.setCode("InvalidValidation");
        applicationError.setMessage(exception.getMessage());
        applicationError.setStatus(exception.getStatus());
        applicationError.setTime(LocalDateTime.now());
        applicationError.setPath(webRequest.getContextPath());
        return new ResponseEntity<>(new Data<>(applicationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Data<ApplicationError>> notFoundExceptionHandler(NotFoundException exception, WebRequest webRequest) {
        ApplicationError applicationError = new ApplicationError();
        applicationError.setCode("Not Found");
        applicationError.setMessage(exception.getMessage());
        applicationError.setStatus(exception.getStatus());
        applicationError.setTime(LocalDateTime.now());
        applicationError.setPath(webRequest.getContextPath());
        return new ResponseEntity<>(new Data<>(applicationError), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ForbiddenException.class})
    public ResponseEntity<Data<ApplicationError>> forbiddenExceptionHandler(ForbiddenException exception, WebRequest webRequest) {
        ApplicationError applicationError = new ApplicationError();
        applicationError.setCode("Forbidden");
        applicationError.setMessage(exception.getMessage());
        applicationError.setStatus(exception.getStatus());
        applicationError.setTime(LocalDateTime.now());
        applicationError.setPath(webRequest.getContextPath());
        return new ResponseEntity<>(new Data<>(applicationError), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Data<ApplicationError>> badRequestExceptionHandler(BadRequestException exception, WebRequest webRequest) {
        ApplicationError applicationError = new ApplicationError();
        applicationError.setCode("Bad Request");
        applicationError.setMessage(exception.getMessage());
        applicationError.setStatus(exception.getStatus());
        applicationError.setTime(LocalDateTime.now());
        applicationError.setPath(webRequest.getContextPath());
        return new ResponseEntity<>(new Data<>(applicationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<Data<ApplicationError>> nullPointerExceptionHandler(NullPointerException exception, WebRequest webRequest) {
        ApplicationError applicationError = new ApplicationError();
        applicationError.setCode("Internal Server Error");
        applicationError.setMessage("NullPointer");
        applicationError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        applicationError.setTime(LocalDateTime.now());
        applicationError.setPath(webRequest.getContextPath());
        return new ResponseEntity<>(new Data<>(applicationError), HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler({SQLException.class})
//    public ResponseEntity<Data<ApplicationError>> sqlExceptionHandler(SQLException exception, WebRequest webRequest) {
//        ApplicationError applicationError = new ApplicationError();
//        applicationError.setCode("Internal Server Error");
//        applicationError.setMessage("PSQL Exception");
//        applicationError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        applicationError.setTime(LocalDateTime.now());
//        applicationError.setPath(webRequest.getContextPath());
//        return new ResponseEntity<>(new Data<>(applicationError), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler({UniversalException.class})
    public ResponseEntity<Data<ApplicationError>> universalExceptionHandler(UniversalException exception, WebRequest webRequest) {
        ApplicationError applicationError = new ApplicationError();
        applicationError.setMessage(exception.getMessage());
        applicationError.setStatus(exception.getStatus());
        applicationError.setTime(LocalDateTime.now());
        applicationError.setPath(webRequest.getContextPath());
        return new ResponseEntity<>(new Data<>(applicationError), HttpStatus.valueOf(exception.getStatus()));
    }
}

