package br.com.insurance.reactive.quote.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {

	private Integer customerId;
	private String name;
	private String sublime;
	private LocalDate birthDate;
	private List<String> appointments = new ArrayList<>();
	
	
	
	public Customer(Integer customerId, String name, String sublime, LocalDate birthDate, List<String> appointments) {
	    this.customerId = customerId;
		this.name = name;
		this.sublime = sublime;
		this.birthDate = birthDate;
		this.appointments = appointments;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSublime() {
		return sublime;
	}


	public void setSublime(String sublime) {
		this.sublime = sublime;
	}


	public LocalDate getbirthDate() {
		return birthDate;
	}


	public void setbirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}


	public List<String> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<String> appointments) {
		this.appointments = appointments;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
}
