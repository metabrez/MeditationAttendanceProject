package edu.mum.cs.projects.attendance.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class AttendanceRecord {
	@NotNull
	private String barcode;
	
	@NotNull
	private String date;
	
	@NotNull
	private String location;
	
	@NotNull
	private String timeslot;
	
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTimeslot() {
		return timeslot;
	}
	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}
}
