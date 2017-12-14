package model;

import javafx.beans.property.*;

/** 
 * Explanation:
 * This file holds all columns of the 'screening' table. It contains private variables for each column,
 * a constructor to initialize them, and getters and setters for each.
 * The variables are created as properties.
 * 
 * "A Property notifies us when any variable such as name, last name, etc. is changed. 
 * This helps us keep the view in sync with the data." (SW Test Academy)
 * 
 * 
 * References: 
 * http://www.swtestacademy.com/database-operatäions-javafx/
 * https://www.youtube.com/watch?v=H1mePFyqqiE&t=18s
 * 
 * @author Michael Aring
 * @version 04/12/2017
 */


public class Screening {
	
	// variables (correspond to table columns)
	private IntegerProperty screening_id; // unique id
	private StringProperty date_id;
	private IntegerProperty year_id;
	private IntegerProperty month_id;
	private IntegerProperty day_id;
	private IntegerProperty time_int; // time saved as 4 etc.
	private StringProperty  time_string; // time saved as "4 pm" etc.
	private StringProperty  film_title; // foreign key corresponds to 'Film' table
	private IntegerProperty available_seats; // keeps count of the number of available seats for a particular screening
	private IntegerProperty booked_seats; // keeps count of the number of booked seats for a particular screening
	private StringProperty  available_info; 	// availableInfo: "8 / 16 seats available"
	private StringProperty  occupancy_rate; 		// occupancy: "80%"
	private StringProperty ticket_status;	// returns "Tickets available" or "Sold out"

	
	// constructor
	public Screening()	{
		this.screening_id = new SimpleIntegerProperty();
		this.date_id = new SimpleStringProperty();
		this.year_id = new SimpleIntegerProperty();
		this.month_id = new SimpleIntegerProperty();
		this.day_id = new SimpleIntegerProperty();
		this.time_int = new SimpleIntegerProperty();
		this.time_string = new SimpleStringProperty();
		this.film_title = new SimpleStringProperty();
		this.available_seats = new SimpleIntegerProperty();
		this.booked_seats = new SimpleIntegerProperty();
		this.available_info = new SimpleStringProperty();
		this.occupancy_rate = new SimpleStringProperty();
		this.ticket_status = new SimpleStringProperty();
	}
	
	// *** SCREENING ID ***
		// getter
	public int getScreeningID()	{
		return screening_id.get();
	}
	
		// setter
	public void setScreeningID(int screeningID)	{
		this.screening_id.set(screeningID);
	}
	
		// property
	public IntegerProperty screeningID()	{
		return screening_id;
	}	
	
	
	// *** DATE ID ***
		// getter
	public String getDateID()	{
		return date_id.get();
	}
	
		// setter
	public void setDateID(String dateID)	{
		this.date_id.set(dateID);
	}
	
		// property
	public StringProperty dateIDProperty()	{
		return date_id;
	}
	
	
	// *** YEAR ID ***
		// getter
	public int getYearID()	{
		return year_id.get();
	}
	
		// setter
	public void setYearID(int yearID)	{
		this.year_id.set(yearID);
	}
	
		// property
	public IntegerProperty yearID()	{
		return year_id;
	}	
	
	
	// *** MONTH ID ***
			// getter
	public int getMonthID()	{
		return month_id.get();
	}
	
		// setter
	public void setMonthID(int monthID)	{
		this.month_id.set(monthID);
	}
	
		// property
	public IntegerProperty monthID()	{
		return month_id;
	}	
	
	
	// *** DAY ID ***
		// getter
	public int getDayID()	{
	return day_id.get();
	}
	
	// setter
	public void setDayID(int dayID)	{
	this.day_id.set(dayID);
	}
	
	// property
	public IntegerProperty dayID()	{
	return day_id;
	}	

	
	
	// *** TIME INT ***
		// getter
	public int getTimeInt()	{
		return time_int.get();
	}
	
		// setter
	public void setTimeInt(int timeInt)	{
		this.time_int.set(timeInt);
	}
	
		// property
	public IntegerProperty timeIntProperty()	{
		return time_int;
	}
	
	
	// *** FILM ID ***
			// getter
		public String getFilmTitle()	{
			return film_title.get();
		}
		
			// setter
		public void setFilmTitle(String filmTitle)	{
			this.film_title.set(filmTitle);
		}
		
			// property
		public StringProperty filmTitle()	{
			return film_title;
		}	
		
		
	// *** TIME STRING ***
			// getter
		public String getTimeString()	{
			return time_string.get();
		}
		
			// setter
		public void setTimeString(String timeString)	{
			this.time_string.set(timeString);
		}
		
			// property
		public StringProperty timeString()	{
			return time_string;
		}	
		
		
	// *** AVAILABLE SEATS ***
			// getter
		public int getAvailableSeats()	{
			return available_seats.get();
		}
		
			// setter
		public void setAvailableSeats(int availableSeats)	{
			this.available_seats.set(availableSeats);
		}
		
			// property
		public IntegerProperty availableSeats()	{
			return available_seats;
		}	

		
		// *** BOOKED SEATS ***
			// getter
		public int getBookedSeats()	{
			return booked_seats.get();
		}
		
			// setter
		public void setBookedSeats(int bookedSeats)	{
			this.booked_seats.set(bookedSeats);
		}
		
			// property
		public IntegerProperty bookedSeats()	{
			return booked_seats;
	}	

		
		// *** AVAILABLE INFO ***
		// getter
		public String getAvailableInfo()	{
			return available_info.get();
		}
		
			// setter
		public void setAvailableInfo(String availableInfo)	{
			this.available_info.set(availableInfo);
		}
		
			// property
		public StringProperty availableInfo()	{
			return available_info;
		}	
	
	
		// *** OCCUPANCY RATE ***
		// getter
		public String getOccupancyRate()	{
			return occupancy_rate.get();
		}
		
			// setter
		public void setOccupancyRate(String occupancyRate)	{
			this.occupancy_rate.set(occupancyRate);
		}
		
			// property
		public StringProperty occupancyRate()	{
			return occupancy_rate;
		}
		
		
		// *** TICKET STATUS***
		// getter
		public String getTicketStatus()	{
			return ticket_status.get();
		}
		
			// setter
		public void setTicketStatus(String ticketStatus)	{
			this.ticket_status.set(ticketStatus);
		}
		
			// property
		public StringProperty ticketStatus()	{
			return ticket_status;
		}
}

