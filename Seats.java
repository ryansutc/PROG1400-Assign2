package airplaneseatbooker;

//The class Seats is simply an object array of Seats with it's own methods
// 1a 1b 1c 1d
// 2a 2b 2c 2d
// 3a 3b 3d 3e
// 4a 4b 4c 4d

public class Seats {
	//NOTE TO SELF: http://www.mkyong.com/java/java-object-sorting-example-comparable-and-comparator/
	protected Seat[][] seats; 
	//private int[] availabilityArray = new int[4]; //(ttl, window, aisle, first class, economy)
	//constructor
	public Seats()
	{

		seats = new Seat[4][4];
		
		for (int row=0; row < 4; row++)
		{
			for (int col=0; col < 4; col++)
			{
				String myseat = "" + (row + 1) + getLetter(col);
				//1 = FirstClass, 2 = Economy
				int mysection = ((row + 1) > 2) ? 2: 1;
				String mytype;
				if (myseat.charAt(1) == 'a' || myseat.charAt(1) == 'd')
				{
					mytype = "Window";
				}
				else 
				{
					mytype = "Aisle";
				}
	
				seats[row][col] = new Seat(myseat, mysection, mytype);
			}
			
		}
		
	}

		
	//choose a free seat based on criteria and reserve it (owner name, section request, aisle/window)
	public String bookSeat(String name, int section, String type){
		
		int myclass = (section == 1) ? 0 : 2; 

		//search for seat in section with type
		for (int row = myclass; row < 4; row++)
		{
			for (int col = 0; col < 4; col++)
			{
				if (seats[row][col].avail == true)
				{
					if (seats[row][col].getType() == type)
					{
						seats[row][col].setOccupant(name);
						return seats[row][col].name;
					}
				}
			}
		}
		return null; //TODO throw exception
	}
	
	//Check and return availability of sections to make sure requests can be met
	//Another Example of 2D array
	public int[][] checkAvailability()
	{

		int fcwindow = 0; //first class window seat
		int fcaisle = 0; //first class aisle seat
		int ewindow = 0; //economy class window seat
		int eaisle =0; //economy class aisle seat
			
		//for seat in list check if available. if/then to each category and total
		for (int row = 0; row < 4; row++)
		{
			for (int col = 0; col < 4; col++)
			{
				if (seats[row][col].avail){				
					if (seats[row][col].getType() == "Window") 
					{
						if (seats[row][col].getSection() == 1)
						{
							fcwindow += 1;
						}
						else { ewindow += 1;}
					}
					else { 
						if (seats[row][col].getSection() == 1)
						{
							fcaisle += 1;
						}
						else { eaisle += 1;}
					}					
				}
			}
		}
		int list[][] = {{fcwindow, fcaisle},{ewindow, eaisle}};
		return list;
	}
	
	//checks if seats are full
	public int getBookedCount(){
		int mycheckA[][] = checkAvailability();
		int total = 0;
		for (int col = 0; col < 2; col++){
			for (int row = 0; row < 2; row++){
				total += mycheckA[col][row];
			}
		}
		return (16-total);
	}

	//private method to assign letter to seatID	
	private char getLetter(int num)
	{
		int cnum = num % 4;
		switch (cnum) {
			case 0:
				return 'a';
			case 1: 
				return 'b';
			case 2:
				return 'c';
			case 3:
				return 'd';
		}
		//TODO throw exception
		return 'x';
	}

}
