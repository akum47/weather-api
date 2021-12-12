package com.inshur.weather.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        List<String> errorMessageList = ex.getBindingResult().getFieldErrors().stream()
                .map(x -> "'" + x.getField() + "' -> " + x.getDefaultMessage())
                .collect(Collectors.toList());

        return handleError(errorMessageList, headers, status);
    }

    @ExceptionHandler({BadInputException.class})
    public ResponseEntity<Object> handleBadInputException(
            HttpServletRequest request, BadInputException ex) {

        List<String> errorMessage = Arrays.asList(ex.getMessage());
        return handleError(errorMessage, null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({WeatherClientException.class})
    public ResponseEntity<Object> handleBadInputException(
            HttpServletRequest request, WeatherClientException ex) {

        List<String> errorMessage = Arrays.asList(ex.getMessage());
        return handleError(errorMessage, null, HttpStatus.BAD_REQUEST);
    }

    ResponseEntity<Object> handleError(List<String> errorDetails, HttpHeaders headers, HttpStatus status) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("errorDetails", errorDetails);

        return new ResponseEntity<>(body, headers, status);
    }

}
