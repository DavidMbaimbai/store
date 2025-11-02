package com.example.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Standard API error response shape.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
