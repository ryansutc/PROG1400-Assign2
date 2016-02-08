package airplaneseatbooker;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class Seat { //seat is a type parameter
//Seats Class

	protected boolean avail = true; //made directly accessible for simplicity/speed
	protected String name = null;
	private String occupant = null;
	private int section; //1 = first class, 2 = economy
	private String type; //Window or Aisle
    //get current date time with Date()
	protected Date bookTime = null;
  
	
	//Constructor
	public Seat(String name, int section, String type)
	{
		this.name = name;
		this.setSection(section);
		this.setType(type);
	}
	//derived from site here: http://www.mkyong.com/java/java-object-sorting-example-comparable-and-comparator/
	public static Comparator<Seat> SeatNameComparator 
    				= new Comparator<Seat>() {

		public int compare(Seat seat1, Seat seat2) {
		
			String seatName1 = seat1.getOccupant().toUpperCase();
			String seatName2 = seat2.getOccupant().toUpperCase();
			
			//ascending order
			return seatName1.compareTo(seatName2);
		}
	};
	//getter
	
	public String getOccupant() 
	{
		return occupant;
	}
	//setter
	public void setOccupant(String occupant)
	{
		this.occupant = occupant;
		this.avail = false;
		Date date = new Date();
		this.bookTime = date;
	}
	

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getBookTime() {
		
		return bookTime;
	}

	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}
}

	
