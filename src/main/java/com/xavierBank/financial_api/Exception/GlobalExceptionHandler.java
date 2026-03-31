package com.xavierBank.financial_api.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handle(ResponseStatusException ex, HttpServletRequest request) {

        Map<String, Object> error = new HashMap<>();
        error.put("error", ex.getReason());
        error.put("message", ex.getReason());
        error.put("path", request.getRequestURI());
        error.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }
}
