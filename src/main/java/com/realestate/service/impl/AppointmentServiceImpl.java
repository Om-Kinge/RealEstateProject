package com.realestate.service.impl;

import com.realestate.entity.*;
import com.realestate.exception.PropertyNotFoundException;
import com.realestate.repository.AppointmentRepository;
import com.realestate.repository.PropertyRepository;
import com.realestate.repository.UserRepository;
import com.realestate.service.AppointmentService;
import com.realestate.service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyRepository propertyRepository;
    
    @Autowired
    private NotificationService notificationService;

    @Override
    public Appointment bookAppointment(Long buyerId,
                                       Long sellerId,
                                       Long propertyId,
                                       String appointmentTime,
                                       AppointmentType type) {

        User buyer = userRepository.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        User seller = userRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new PropertyNotFoundException("Property not found"));

        Appointment appointment = new Appointment();

        appointment.setBuyer(buyer);
        appointment.setSeller(seller);
        appointment.setProperty(property);
        appointment.setAppointmentTime(appointmentTime);
        appointment.setType(type);
        appointment.setStatus(AppointmentStatus.SCHEDULED);

        Appointment savedAppointment = appointmentRepository.save(appointment);

        notificationService.sendAppointmentNotification(
                seller,
                savedAppointment.getId(),
                "New appointment scheduled for property " + property.getTitle()
        );
        
        return savedAppointment;
    }

    @Override
    public List<Appointment> getBuyerAppointments(Long buyerId) {

        return appointmentRepository.findByBuyerId(buyerId);
    }

    @Override
    public List<Appointment> getSellerAppointments(Long sellerId) {

        return appointmentRepository.findBySellerId(sellerId);
    }
    
    public Appointment updateAppointment(Long appointmentId, String newTime) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setAppointmentTime(newTime);

        Appointment updatedAppointment = appointmentRepository.save(appointment);

        // Send notification
        notificationService.sendAppointmentNotification(
                appointment.getSeller(),
                appointment.getId(),
                "Appointment updated. New time: " + newTime +
                " Appointment updated for property " + appointment.getProperty().getTitle()
        );

        return updatedAppointment;
    }
}