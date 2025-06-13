package com.example.mcpserver.advice;

import com.example.mcpserver.service.DocumentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DocumentNotFoundException.class)
    protected ResponseEntity<Object> handleDocumentNotFound(
            DocumentNotFoundException ex, WebRequest request) {
        // You can create a custom error response object if needed
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), "Document not found");
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    // Inner class for structured error response
    private static class ApiError {
        private HttpStatus status;
        private String message;
        private String error;

        public ApiError(HttpStatus status, String message, String error) {
            super();
            this.status = status;
            this.message = message;
            this.error = error;
        }

        // Getters for serialization
        public HttpStatus getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public String getError() {
            return error;
        }
    }
}
