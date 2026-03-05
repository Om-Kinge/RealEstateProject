package com.realestate.service.impl;

import com.realestate.entity.*;
import com.realestate.repository.AppointmentRepository;
import com.realestate.repository.PropertyRepository;
import com.realestate.repository.UserRepository;
import com.realestate.service.AppointmentService;

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
                .orElseThrow(() -> new RuntimeException("Property not found"));

        Appointment appointment = new Appointment();

        appointment.setBuyer(buyer);
        appointment.setSeller(seller);
        appointment.setProperty(property);
        appointment.setAppointmentTime(appointmentTime);
        appointment.setType(type);
        appointment.setStatus(AppointmentStatus.SCHEDULED);

        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getBuyerAppointments(Long buyerId) {

        return appointmentRepository.findByBuyerId(buyerId);
    }

    @Override
    public List<Appointment> getSellerAppointments(Long sellerId) {

        return appointmentRepository.findBySellerId(sellerId);
    }

    @Override
    public Appointment updateStatus(Long appointmentId, AppointmentStatus status) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus(status);

        return appointmentRepository.save(appointment);
    }
}