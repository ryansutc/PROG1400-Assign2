package airplaneseatbooker;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class Application 
{
	
	public Seats myseats; 
	
	private Scanner scanObj = new Scanner(System.in);
	private String userInput; //anytime user types something
	
	
	//constructor
	public void Start(){
		myseats = new Seats();

		printWelcome();
		printSeating();
		
		recieveCommands();
		
		 
	}

	//Print Welcome Message
	public void printWelcome(){
		System.out.println("**********");
		System.out.println("Welcome to Seating Utility");
		System.out.println("Created: RSutcliffe, 2016");
		System.out.println("**********");
		System.out.println("Press any key (plus Enter) to Continue...\n");
		userInput = scanObj.next();
	}
	
	//Print Seating Layout
	public void printSeating(){
		System.out.println("Seating Layout:");
		System.out.print("\n  a b    c d\n");
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
				
				if (myseats.seats[x].avail == true)
				{
					System.out.print("o ");
				}
				else
				{
					System.out.print("x ");
				}
				x +=1;
			}
			y +=1;
			System.out.println();
		}
	}

	//Show options screen.
	private void recieveCommands() {
	
		while (!userInput.equals("q"))
		{	
			String msg = "Please choose from following options" +
					"\n-   's' to show seating" +
					"\n-   'r' to print the report" +
					"\n-   'p' to process another passenger" +
					"\n-   'q' to exit";
			
			System.out.println(msg);
			userInput = scanObj.next().toString().trim().toLowerCase();
			
			while (validateInput("command") == false)
			{
				System.out.println("Invalid Response. " + msg);
				userInput = scanObj.next().toString().trim().toLowerCase();
			}
			
			if (userInput.equals("p"))
			{
				processPassenger();
			}
			else if (userInput.equals("r"))
			{
				createReport();
			}
			else if (userInput.equals("s"))
			{
				printSeating();
			}
		}
	}
	
	
	//Process a seat for a passenger
	public void processPassenger()	{
		int[][] myavailability = myseats.checkAvailability(); //{fcwindow, fcaisle, ewindow, eaisle}
		int fcavail = myavailability[0][0] + myavailability[0][1];
		int ecavail = myavailability[1][0] + myavailability[1][1];
		int ttlavail = fcavail + ecavail;
		
		String sorrymsg = "Sorry. No Seats avail. Next Flight in 3 Hours";
		if ((ttlavail) == 0) 
		{
			System.out.println(sorrymsg);
			return;
		}
		
		String msg;
		int mysection;
		if (fcavail == 0 || ecavail == 0)
		{
			if (ecavail == 0)
			{
				mysection = 1;
				msg = "Only First Class Seats are available. " +
					"\nPress 'y' to accept or any other key to cancel";
			}
			else {
				msg = "Only Economy Seats are available. " +
					"\nPress 'y' to accept or any other key to cancel";
				mysection = 2;
			}
			System.out.println(msg);
			userInput = scanObj.next().toString().trim().toLowerCase();
			if (!userInput.equals("y"))
			{
				System.out.println(sorrymsg);
				return;
			}
			
		}
		else
		{
			//pick class/section
			msg = "Please choose a section. Press 1 for First Class, 2 for Economy";
			System.out.println(msg);
			userInput = scanObj.next().toString().trim().toLowerCase();
			while (validateInput("class") == false)
			{
				System.out.println("Invalid Response." + msg);
				userInput = scanObj.next().toString().trim().toLowerCase();
			}
			mysection = Integer.valueOf(userInput);
		}
		
		
		
		String mytype; //Window or Aisle seat
		msg = "Please choose seat type preference." +
				"\nPress 'a' for aisle, 'w' for window.";
		if (myavailability[mysection-1][0] > 0 && myavailability[mysection-1][1] > 0) 
		{
			System.out.println(msg);
			userInput = scanObj.next().toString().trim().toLowerCase();
			while (validateInput("seattype") == false)
			{ 
				System.out.println("Invalid Response. " + msg);
				userInput = scanObj.next().toString().trim().toLowerCase();
			}
			if (userInput.charAt(0) == 'a'){
				mytype = "Aisle";
			}
			else {
				mytype = "Window";
			}

		}
		else {
			mytype = (myavailability[mysection-1][0] > 0) ? "Window": "Aisle";
		}
		
		System.out.println("Please Enter Your Name.");
		userInput = scanObj.next().toString().trim();
		while (validateInput("name") == false)
		{
			System.out.println("Invalid/Blank Response." + msg);
			userInput = scanObj.next().toString().trim();
		}
		String myname = userInput;
		String seatname = myseats.bookSeat(myname, mysection, mytype);
		System.out.println("Seat Booked:\nId: " + seatname);
		
	}
	
	
	//validate User Input under various circumstances
	public boolean validateInput(String mytype){
		
		if (mytype == "class") //handle user input for class selection
		{
			
			if (userInput.equals("1") || userInput.equals("2"))
			{
				return true;
			}
			else
			{	return false;}
		}
		else if (mytype == "command")
		{
			if (userInput.equals("s") || userInput.equals("r") || userInput.equals("p"))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if (mytype == "name")
		{
			if (userInput.length() > 0)
			{
				return true;
			}
			else {
				return false;
			}
				
		}
		else if (mytype == "seattype")
		{
			if (userInput.equals("w") || userInput.equals("a"))
			{
				return true;
			}
			else {
				return false;
			}
		}
		
		return false;
	}
}
	

