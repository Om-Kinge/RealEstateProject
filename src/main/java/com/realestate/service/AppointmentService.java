package com.realestate.service;

import com.realestate.entity.Appointment;
import com.realestate.entity.AppointmentStatus;
import com.realestate.entity.AppointmentType;

import java.util.List;

public interface AppointmentService {

    Appointment bookAppointment(Long buyerId,
                                Long sellerId,
                                Long propertyId,
                                String appointmentTime,
                                AppointmentType type);

    List<Appointment> getBuyerAppointments(Long buyerId);

    List<Appointment> getSellerAppointments(Long sellerId);

    Appointment updateStatus(Long appointmentId, AppointmentStatus status);
}