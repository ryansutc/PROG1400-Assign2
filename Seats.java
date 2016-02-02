package airplaneseatbooker;

//The class Seats is simply an object array of Seats with it's own methods
// 1a 1b 1c 1d
// 2a 2b 2c 2d
// 3a 3b 3d 3e
// 4a 4b 4c 4d

public class Seats {
	
	public Seat[][] seats; 
	private int[] availabilityArray = new int[4]; //(ttl, window, aisle, first class, economy)
	//constructor
	public Seats()
	{

		seats = new Seat[4][4];
		
		for (int col=0; col < 4; col++)
		{
			for (int row=0; row < 4; row++)
			{
				String myseat = "" + row + getLetter(col);
				char mysection = getSection(row);
				char mytype;
				if (col == 0 || col == 3)
				{
					mytype = 'W';
				}
				else 
				{
					mytype = 'A';
				}

				seats[col][row] = new Seat(myseat, mysection, mytype);
			}
		}
	}
	
	//choose a free seat based on criteria and reserve it
	public void bookSeat(String name, char section, char type){
		
		//TODO: write code
		int myclass = (section == '1') ? 0 : 2;

		//search for seat in section with type
		for (int row = 0; row < 4; row++)
		{
			for (int col = myclass; col < 4; col++)
			{
				if (seats[col][row].avail == true)
				{
					if (seats[col][row].getSection() == section)
					{
						seats[col][row].setOccupant(name);
					}
				}
			}
		}
	}
	
	//Check and return availability of sections to make sure requests can be met
	//(returns array with: totalcount, windowcount, aislecount, firstclasscount, economycount)
	public int[] checkAvailability()
	{
		int total = 0;
		int window = 0;
		int aisle = 0;
		int firstclass = 0;
		int economy = 0;
		
		//for seat in list check if available. if/then to each category and total
		for (int col = 0; col < 4; col++)
		{
			for (int row = 0; row < 4; row++) 
			{
				if (seats[col][row].avail){
					total += 1;
					
					if (col == 0 || col == 3) 
					{
						window += 1;
					}
					else { 
						aisle += 1;
					}
					
					if (row < 2)
					{
						firstclass += 1;
					}
					else {
						economy += 1;
					}
					
				}
			}
		}
		int list[] = {total, window, aisle, firstclass, economy};
		return list;
	}
	
	
	//private method to assign letter to seat
	private char getLetter(int num)
	{
		switch (num) {
			case 0:
				return 'a';
			case 1: 
				return 'b';
			case 2:
				return 'c';
			case 3:
				return 'd';
		}
		//throw error
		return 'x';
	}
	//private method to assign section to seat
	private char getSection(int num)
	{
		switch (num) {
		case 0: case 1:
			return 'a';
		case 2: case 3:
			return 'b';
	}
	//throw error
	return 'x';
	}

}
