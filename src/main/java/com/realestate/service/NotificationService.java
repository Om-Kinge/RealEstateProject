package com.realestate.service;

import com.realestate.entity.User;

public interface NotificationService {
	public void sendAppointmentNotification(User user, Long appointmentId, String message);

}
