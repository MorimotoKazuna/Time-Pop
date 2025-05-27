package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Record {
	 private LocalDate date;
	    private LocalTime clockIn;
	    private LocalTime clockOut;

	    public Record(LocalDate date, LocalTime clockIn, LocalTime clockOut) {
	        this.date = date;
	        this.clockIn = clockIn;
	        this.clockOut = clockOut;
	    }

	    public LocalDate getDate() { return date; }
	    public LocalTime getClockIn() { return clockIn; }
	    public LocalTime getClockOut() { return clockOut; }
}