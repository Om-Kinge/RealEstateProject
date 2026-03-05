package com.realestate.repository;

import com.realestate.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByBuyerId(Long buyerId);

    List<Appointment> findBySellerId(Long sellerId);
}