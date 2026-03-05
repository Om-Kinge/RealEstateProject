
package com.realestate.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Property {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public Double getPrice() {
	return price;
}

public void setPrice(Double price) {
	this.price = price;
}

public String getConfiguration() {
	return configuration;
}

public void setConfiguration(String configuration) {
	this.configuration = configuration;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getLocality() {
	return locality;
}

public void setLocality(String locality) {
	this.locality = locality;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public LocalDate getPossessionDate() {
	return possessionDate;
}

public void setPossessionDate(LocalDate possessionDate) {
	this.possessionDate = possessionDate;
}

public boolean isIspremium() {
	return Ispremium;
}

public void setIspremium(boolean ispremium) {
	Ispremium = ispremium;
}

public PropertyStatus getStatus() {
	return status;
}

public void setStatus(PropertyStatus status) {
	this.status = status;
}

public User getSeller() {
	return seller;
}

public void setSeller(User seller) {
	this.seller = seller;
}

private String title;
 private Double price;
 private String configuration;

 private String city;
 private String locality;
 private String state;

 private LocalDate possessionDate;

 private boolean Ispremium;

 @Enumerated(EnumType.STRING)
 private PropertyStatus status;

 @ManyToOne
 private User seller;
}
