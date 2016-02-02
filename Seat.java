package airplaneseatbooker;

public class Seat {
//Seats Class

	public boolean avail = true; //made public for simplicity/speed
	public String name = null;
	private String occupant = null;
	private char section; //a = first class, b = economy
	
	public Seat(String name, char section, char type)
	{
		this.name = name;
		this.setSection(section);
	}
	
	public String getOccupant() 
	{
		return occupant;
	}
	
	public void setOccupant(String occupant)
	{
		this.occupant = occupant;
		this.avail = false;
	}

	public char getSection() {
		return section;
	}

	public void setSection(char section) {
		this.section = section;
	}
}
