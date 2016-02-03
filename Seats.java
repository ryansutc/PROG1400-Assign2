package airplaneseatbooker;

//The class Seats is simply an object array of Seats with it's own methods
// 1a 1b 1c 1d
// 2a 2b 2c 2d
// 3a 3b 3d 3e
// 4a 4b 4c 4d

public class Seats {
	
	public Seat[] seats; 
	//private int[] availabilityArray = new int[4]; //(ttl, window, aisle, first class, economy)
	//constructor
	public Seats()
	{

		seats = new Seat[16];
		
		for (int i=0; i < 16; i++)
		{
			
			String myseat = "" + getSeat(i);
			//1 = FirstClass, 2 = Economy
			int mysection = ((i / 8) > 0) ? 2: 1;
			String mytype;
			if (myseat.charAt(1) == 'a' || myseat.charAt(1) == 'd')
			{
				mytype = "Window";
			}
			else 
			{
				mytype = "Aisle";
			}

			seats[i] = new Seat(myseat, mysection, mytype);
			
		}
		
		/*
		int x = 0;
		int y = 0;
		while ( x < 16)
		{
			if (x == 8)
			{
				System.out.println("------   -----");
			}
			System.out.print("" +(y +1)+ " ");
			
			for (int col = 0; col < 4; col++)
			{

				if (col == 2)
				{
					System.out.print("|| ");
				}
				
				if (seats[x].avail == true)
				{
					System.out.print("o:" + seats[x].name + seats[x].getSection());
				}
				else
				{
					System.out.print("x " + seats[x].name + seats[x].getSection());
				}
				x +=1;
			}
			y +=1;
			System.out.println("");
		}
		*/
	}

		
	//choose a free seat based on criteria and reserve it (owner name, section request, aisle/window)
	public String bookSeat(String name, int section, String type){
		
		//TODO: write code
		int myclass = (section == 1) ? 0 : 8;

		//search for seat in section with type
		for (int i = myclass; i < 16; i++)
		{
			if (seats[i].avail == true)
			{
				if (seats[i].getType() == type)
				{
					seats[i].setOccupant(name);
					return seats[i].name;
				}
			}
		}
		return null; //TODO throw exception
	}
	
	//Check and return availability of sections to make sure requests can be met
	//(returns array with: 0totalcount, 1windowcount, 2aislecount, 3firstclasscount, 4economycount)
	public int[][] checkAvailability()
	{

		int fcwindow = 0; //first class window seat
		int fcaisle = 0; //first class aisle seat
		int ewindow = 0; //economy class window seat
		int eaisle =0; //economy class aisle seat
			
		//for seat in list check if available. if/then to each category and total
		for (int i = 0; i < 16; i++)
		{
			if (seats[i].avail){				
				if (seats[i].getType() == "Window") 
				{
					if (seats[i].getSection() == 1)
					{
						fcwindow += 1;
					}
					else { ewindow += 1;}
				}
				else { 
					if (seats[i].getSection() == 1)
					{
						fcaisle += 1;
					}
					else { eaisle += 1;}
				}					
			}
		}
		int list[][] = {{fcwindow, fcaisle},{ewindow, eaisle}};
		return list;
	}
	
	//private method to get seatID
	private String getSeat(int num){
		int mynumber = (num / 4) + 1;
		char myletter = getLetter(num);
		return "" + mynumber + myletter;
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
