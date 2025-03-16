package com.sudagoarth.sudanyallapay.Notifications.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sudagoarth.sudanyallapay.Notifications.Dtos.NotificationRequest;
import com.sudagoarth.sudanyallapay.Notifications.Dtos.NotificationResponse;
import com.sudagoarth.sudanyallapay.Notifications.Interfaces.NotificationInterface;

@Service
public class NotificationService implements NotificationInterface {

    @Override
    public Page<NotificationResponse> getNotifications(Long userId, Pageable pageable) {
        throw new UnsupportedOperationException("Unimplemented method 'getNotifications'");
    }

    @Override
    public NotificationResponse getNotification(Long notificationId) {
        throw new UnsupportedOperationException("Unimplemented method 'getNotification'");
    }

    @Override
    public NotificationResponse createNotification(NotificationRequest notificationRequest) {
        throw new UnsupportedOperationException("Unimplemented method 'createNotification'");
    }

    @Override
    public NotificationResponse updateNotification(Long notificationId, NotificationRequest notificationRequest) {
        throw new UnsupportedOperationException("Unimplemented method 'updateNotification'");
    }

    @Override
    public void deleteNotification(Long notificationId) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteNotification'");
    }
    
}
