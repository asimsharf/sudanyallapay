package com.sudagoarth.sudanyallapay.Notifications.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.sudagoarth.sudanyallapay.Notifications.Dtos.NotificationRequest;
import com.sudagoarth.sudanyallapay.Notifications.Dtos.NotificationResponse;
import com.sudagoarth.sudanyallapay.Notifications.Interfaces.NotificationInterface;
import com.sudagoarth.sudanyallapay.utils.ApiResponse;
import com.sudagoarth.sudanyallapay.utils.LocaledData;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    @Autowired
    private NotificationInterface notificationInterface;

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class.getName());

    @GetMapping
    public ResponseEntity<ApiResponse> getNotifications(@RequestParam("userId") Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        LOGGER.info("Getting notifications - User ID: {} | Page: {} | Size: {}", userId, page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<NotificationResponse> notificationResponses = notificationInterface.getNotifications(userId, pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        new LocaledData("Notifications retrieved successfully",
                                "تم استرجاع الإشعارات بنجاح"),
                        HttpStatus.OK.value(),
                        notificationResponses.getContent(),
                        notificationResponses.getPageable()));
    }

    @GetMapping("/notification")
    public ResponseEntity<ApiResponse> getNotification(@RequestParam Long notificationId) {
        LOGGER.info("Getting notification by ID: {}", notificationId);
        NotificationResponse notificationResponse = notificationInterface.getNotification(notificationId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        new LocaledData("Notification retrieved successfully",
                                "تم استرجاع الإشعار بنجاح"),
                        HttpStatus.OK.value(),
                        notificationResponse));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createNotification(@RequestBody NotificationRequest notificationRequest) {
        LOGGER.info("Creating notification - User ID: {} | Title: {} | Message: {}",
                notificationRequest.getUserId(), notificationRequest.getTitle(), notificationRequest.getMessage());
        NotificationResponse notificationResponse = notificationInterface.createNotification(notificationRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        new LocaledData("Notification created successfully",
                                "تم إنشاء الإشعار بنجاح"),
                        HttpStatus.CREATED.value(),
                        notificationResponse));
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateNotification(@RequestParam("notificationId") Long notificationId,
            @RequestBody NotificationRequest notificationRequest) {
        LOGGER.info("Updating notification - ID: {} | Title: {} | Message: {}", notificationId,
                notificationRequest.getTitle(),
                notificationRequest.getMessage());
        NotificationResponse notificationResponse = notificationInterface.updateNotification(notificationId,
                notificationRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        new LocaledData("Notification updated successfully",
                                "تم تحديث الإشعار بنجاح"),
                        HttpStatus.OK.value(),
                        notificationResponse));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteNotification(@RequestParam Long notificationId) {
        LOGGER.info("Deleting notification by ID: {}", notificationId);
        notificationInterface.deleteNotification(notificationId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        new LocaledData("Notification deleted successfully",
                                "تم حذف الإشعار بنجاح"),
                        HttpStatus.OK.value(), null));
    }

}
