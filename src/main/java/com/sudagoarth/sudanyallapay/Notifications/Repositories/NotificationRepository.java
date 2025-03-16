package com.sudagoarth.sudanyallapay.Notifications.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sudagoarth.sudanyallapay.Notifications.Entities.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {


} 