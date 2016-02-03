package airplaneseatbooker;

public class Seat {
//Seats Class

	public boolean avail = true; //made public for simplicity/speed
	public String name = null;
	private String occupant = null;
	private int section; //1 = first class, 2 = economy
	private String type; //Window or Aisle
	
	public Seat(String name, int section, String type)
	{
		this.name = name;
		this.setSection(section);
		this.setType(type);
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
}
