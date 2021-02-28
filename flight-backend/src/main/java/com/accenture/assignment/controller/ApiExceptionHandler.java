package com.accenture.assignment.controller;

import com.accenture.assignment.exception.EntityNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleServiceException(final EntityNotFoundException ex,
                                                         final WebRequest request) {
        logDebug("ServiceRequestException ", ex, request);
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private void logDebug(String text, Exception ex, WebRequest request) {
        if (log.isDebugEnabled()) {
            try {
                log.debug(text + " " + objectMapper.writeValueAsString(request), ex);
            } catch (Exception omEx) {
                log.debug(text + " " + request, ex);
            }
        }
    }
}
