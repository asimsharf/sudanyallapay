package com.sudagoarth.sudanyallapay.Notifications.Dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {
    @NotNull(message = "ID is required")
    private Long id;
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Message is required")
    private String message;

    @Enumerated(EnumType.STRING)
    private Boolean isRead;

    @NotBlank(message = "Created at is required")
    private String createdAt;

}
