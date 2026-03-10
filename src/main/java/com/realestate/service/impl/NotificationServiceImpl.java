package com.realestate.service.impl;

import com.realestate.entity.Notification;
import com.realestate.entity.User;
import com.realestate.repository.NotificationRepository;
import com.realestate.service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void sendAppointmentNotification(User user,
                                            Long appointmentId,
                                            String message) {

        Notification notification = new Notification();

        notification.setUser(user);
        notification.setAppointmentId(appointmentId);
        notification.setMessage(message);
        notification.setCreatedAt(LocalDateTime.now());

        // Mock delivery channels
        notification.setEmailSent(true);
        notification.setSmsSent(true);
        notification.setWhatsappSent(false);

        notificationRepository.save(notification);

        // Mock logs
        System.out.println("Email sent to " + user.getEmail());
        System.out.println("SMS sent to user " + user.getId());
    }
}