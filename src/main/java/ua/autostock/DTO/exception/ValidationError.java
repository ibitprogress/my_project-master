package ua.autostock.DTO.exception;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Getter

public class ValidationError {

    private String details;
    private String error;
    private LocalDateTime time;

    public ValidationError(String details, String error) {
        this.details = details;
        this.error = error;
        this.time = LocalDateTime.now();
    }


}
