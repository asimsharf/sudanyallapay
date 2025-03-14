package com.sudagoarth.sudanyallapay.utils;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse(LocaledData message, int status, Object data, Object pagination, String code, boolean success,
        List<Map<String, String>> errorDetails) {

    // Constructor to create success response with or without pagination
    public static ApiResponse success(LocaledData message, int status, Object data) {
        return new ApiResponse(message, status, data, null, "OK", true, null);
    }

    public static ApiResponse success(LocaledData message, int status, Object data, Object pagination) {
        return new ApiResponse(message, status, data, pagination, "OK", true, null);
    }

    // Constructor to create error response
    public static ApiResponse error(LocaledData message, int status, String code, List<Map<String, String>> errorDetails) {
        return new ApiResponse(message, status, null, null, code, false, errorDetails);
    }

    // Builder pattern for flexibility
    public static ApiResponseBuilder builder() {
        return new ApiResponseBuilder();
    }

    // Inner builder class
    public static class ApiResponseBuilder {
        private LocaledData message;
        private int status;
        private Object data;
        private Object pagination;
        private String code;
        private boolean success;
        private List<Map<String, String>> errorDetails;

        public ApiResponseBuilder message(LocaledData message) {
            this.message = message;
            return this;
        }

        public ApiResponseBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ApiResponseBuilder data(Object data) {
            this.data = data;
            return this;
        }

        public ApiResponseBuilder pagination(Object pagination) {
            this.pagination = pagination;
            return this;
        }

        public ApiResponseBuilder code(String code) {
            this.code = code;
            return this;
        }

        public ApiResponseBuilder success(boolean success) {
            this.success = success;
            return this;
        }

        public ApiResponseBuilder errorDetails(List<Map<String, String>> errorDetails) {
            this.errorDetails = errorDetails;
            return this;
        }

        public ApiResponse build() {
            return new ApiResponse(message, status, data, pagination, code, success, errorDetails);
        }
    }
}