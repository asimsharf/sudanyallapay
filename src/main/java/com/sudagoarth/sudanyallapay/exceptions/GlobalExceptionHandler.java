package com.sudagoarth.sudanyallapay.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sudagoarth.sudanyallapay.utils.ApiResponse;
import com.sudagoarth.sudanyallapay.utils.LocaledData;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
        private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiResponse> handleGeneralException(Exception ex) {
                LOGGER.error("Unhandled exception: ", ex);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(ApiResponse.error(new LocaledData(
                                                "An unexpected error occurred.",
                                                "حدث خطأ غير متوقع"),
                                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                                "INTERNAL_SERVER_ERROR", null));
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResponse> handleValidationException(MethodArgumentNotValidException ex) {
                List<Map<String, String>> errorDetails = ex.getBindingResult().getFieldErrors()
                                .stream()
                                .map(fieldError -> Map.of("field", fieldError.getField(), "error",
                                                fieldError.getDefaultMessage()))
                                .collect(Collectors.toList());

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(ApiResponse.error(new LocaledData(
                                                "Validation error",
                                                "خطأ في التحقق"),
                                                HttpStatus.BAD_REQUEST.value(),
                                                "VALIDATION_ERROR",
                                                errorDetails));
        }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
                LOGGER.warn("IllegalArgumentException: {}", ex.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(ApiResponse.error(new LocaledData(
                                                "Invalid argument",
                                                "معطى غير صالح"),
                                                HttpStatus.BAD_REQUEST.value(),
                                                "INVALID_ARGUMENT",
                                                List.of(Map.of("message", ex.getMessage()))));
        }

        @ExceptionHandler(HttpMessageConversionException.class)
        public ResponseEntity<ApiResponse> handleHttpMessageConversionException(HttpMessageConversionException ex) {
                LOGGER.error("HttpMessageConversionException: ", ex);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(ApiResponse.error(new LocaledData(
                                                "Serialization error: Unable to process the request.",
                                                "خطأ في التسلسل: غير قادر على معالجة الطلب."),
                                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                                "SERIALIZATION_ERROR",
                                                null));
        }

        @ExceptionHandler(DuplicateException.class)
        public ResponseEntity<ApiResponse> handleDuplicateException(DuplicateException ex) {
                LOGGER.warn("DuplicateException: {}", ex.getMessage());
                return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(ApiResponse.error(new LocaledData(
                                                "Duplicate entry",
                                                "تكرار الإدخال"),
                                                HttpStatus.CONFLICT.value(),
                                                "DUPLICATE_ENTRY",
                                                List.of(Map.of("message", ex.getMessage()))));
        }

        @ExceptionHandler(NotFoundException.class)
        public ResponseEntity<ApiResponse> handleNotFoundException(NotFoundException ex) {
                LOGGER.warn("NotFoundException: {}", ex.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(ApiResponse.error(new LocaledData(
                                                "Resource not found",
                                                "المورد غير موجود"),
                                                HttpStatus.NOT_FOUND.value(),
                                                "RESOURCE_NOT_FOUND",
                                                List.of(Map.of("message", ex.getMessage()))));
        }

        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<ApiResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
                LOGGER.warn("EntityNotFoundException: {}", ex.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(ApiResponse.error(new LocaledData(
                                                "Entity not found",
                                                "الكيان غير موجود"),
                                                HttpStatus.NOT_FOUND.value(),
                                                "ENTITY_NOT_FOUND",
                                                List.of(Map.of("message", ex.getMessage()))));
        }

        @ExceptionHandler(ConstraintViolationException.class)
        public ResponseEntity<ApiResponse> handleConstraintViolationException(ConstraintViolationException ex) {
                LOGGER.warn("ConstraintViolationException: {}", ex.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(ApiResponse.error(new LocaledData(
                                                "Constraint violation",
                                                "انتهاك القيد"),
                                                HttpStatus.BAD_REQUEST.value(),
                                                "CONSTRAINT_VIOLATION",
                                                List.of(Map.of("message", ex.getMessage()))));
        }

        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<ApiResponse> handleAccessDeniedException(AccessDeniedException ex) {
                LOGGER.warn("AccessDeniedException: {}", ex.getMessage());
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                .body(ApiResponse.error(new LocaledData(
                                                "Access denied",
                                                "تم رفض الوصول"),
                                                HttpStatus.FORBIDDEN.value(),
                                                "ACCESS_DENIED",
                                                null));
        }

        @ExceptionHandler(MissingServletRequestParameterException.class)
        public ResponseEntity<ApiResponse> handleMissingServletRequestParameterException(
                        MissingServletRequestParameterException ex) {
                LOGGER.warn("MissingServletRequestParameterException: {}", ex.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(ApiResponse.error(new LocaledData(
                                                "Missing request parameter",
                                                "معلمة مفقودة في الطلب"),
                                                HttpStatus.BAD_REQUEST.value(),
                                                "MISSING_PARAMETER",
                                                List.of(Map.of("parameter", ex.getParameterName()))));
        }

        @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
        public ResponseEntity<ApiResponse> handleHttpRequestMethodNotSupportedException(
                        HttpRequestMethodNotSupportedException ex) {
                LOGGER.warn("HttpRequestMethodNotSupportedException: {}", ex.getMessage());
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                                .body(ApiResponse.error(new LocaledData(
                                                "Method not allowed",
                                                "الطريقة غير مسموحة"),
                                                HttpStatus.METHOD_NOT_ALLOWED.value(),
                                                "METHOD_NOT_ALLOWED",
                                                List.of(Map.of("message", ex.getMessage()))));
        }
}
