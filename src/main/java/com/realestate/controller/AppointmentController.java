package com.realestate.controller;

import com.realestate.entity.Appointment;
import com.realestate.entity.AppointmentType;
import com.realestate.service.AppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Buyer books appointment
    @PostMapping("/book")
    public Appointment bookAppointment(
            @RequestParam Long buyerId,
            @RequestParam Long sellerId,
            @RequestParam Long propertyId,
            @RequestParam String appointmentTime,
            @RequestParam AppointmentType type) {

        return appointmentService.bookAppointment(
                buyerId,
                sellerId,
                propertyId,
                appointmentTime,
                type);
    }

    // Buyer views their appointments
    @GetMapping("/buyer/{buyerId}")
    public List<Appointment> getBuyerAppointments(@PathVariable Long buyerId) {

        return appointmentService.getBuyerAppointments(buyerId);
    }

    // Seller views their appointments
    @GetMapping("/seller/{sellerId}")
    public List<Appointment> getSellerAppointments(@PathVariable Long sellerId) {

        return appointmentService.getSellerAppointments(sellerId);
    }

}