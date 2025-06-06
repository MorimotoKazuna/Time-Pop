package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceRecord {
		private int userId;
		private LocalDate date;
	    private LocalTime clockIn;
	    private LocalTime clockOut;
	    private String name;

	    public AttendanceRecord(LocalDate date, LocalTime clockIn, LocalTime clockOut) {
	        this.date = date;
	        this.clockIn = clockIn;
	        this.clockOut = clockOut;
	    }
	    
	    public AttendanceRecord(int userId, LocalDate date, LocalTime clockIn, LocalTime clockOut, String name) {
	    	this.userId = userId;
	    	this.date = date;
	    	this.clockIn = clockIn;
	    	this.clockOut = clockOut;
	    	this.name = name;
	    }
	    
	    public int getUserId() { return userId; }
	    public LocalDate getDate() { return date; }
	    public LocalTime getClockIn() { return clockIn; }
	    public LocalTime getClockOut() { return clockOut; }
	    public String getName() { return name;}
}