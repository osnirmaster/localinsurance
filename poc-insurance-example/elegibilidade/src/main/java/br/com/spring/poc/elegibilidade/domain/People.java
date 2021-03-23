package br.com.spring.poc.elegibilidade.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class People {

	private String name;
	private String sublime;
	private Date bornDate;
	private List<String> appointments = new ArrayList<String>();
	
	
	public People(String name, String sublime, Date bornDate, List<String> appointments) {
		super();
		this.name = name;
		this.sublime = sublime;
		this.bornDate = bornDate;
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


	public Date getBornDate() {
		return bornDate;
	}


	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}


	public List<String> getAppointments() {
		return appointments;
	}


	public void setAppointments(List<String> appointments) {
		this.appointments = appointments;
	}
	
	
	
	
	
}
