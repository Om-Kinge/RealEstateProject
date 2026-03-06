package com.realestate.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Appointment {

 public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public AppointmentType getType() {
		return type;
	}

	public void setType(AppointmentType type) {
		this.type = type;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

@Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @ManyToOne
 private User buyer;

 @ManyToOne
 private User seller;

 @ManyToOne
 private Property property;
 
 private String appointmentTime;
 
 @Enumerated(EnumType.STRING)
 private AppointmentType type;
 
 @Enumerated(EnumType.STRING)
 private AppointmentStatus status;
}
