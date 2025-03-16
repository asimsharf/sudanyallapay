package com.sudagoarth.sudanyallapay.Notifications.Interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sudagoarth.sudanyallapay.Notifications.Dtos.NotificationRequest;
import com.sudagoarth.sudanyallapay.Notifications.Dtos.NotificationResponse;

public interface NotificationInterface {

    Page<NotificationResponse> getNotifications(Long userId, Pageable pageable);

    NotificationResponse getNotification(Long notificationId);

    NotificationResponse createNotification(NotificationRequest notificationRequest);

    NotificationResponse updateNotification(Long notificationId, NotificationRequest notificationRequest);

    void deleteNotification(Long notificationId);
    
}