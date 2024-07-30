package ru.cft.task_backend.core.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * CustomExceptionHandler is a controller advice class that handles exceptions globally
 * for the entire application.
 */
@Log4j2
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Handles all exceptions that occur during the execution of the application.
     * This method logs the exception and delegates the handling to the superclass method.
     *
     * @param ex the exception that was thrown
     * @param request the current web request
     * @return a {@code ResponseEntity} object containing the exception details and appropriate HTTP status code
     * @throws Exception if an error occurs while handling the exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception ex, WebRequest request) throws Exception {
        log.error("Exception during execution an application: {}", ex.getMessage());
        return handleException(ex, request); //method of superclass
    }
}
