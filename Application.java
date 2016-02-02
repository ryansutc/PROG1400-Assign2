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
		System.out.print("\n  a b c d");
		for (int col = 0; col < 4; col++)
		{
			System.out.print("\n");
			if (col == 2)
			{
				System.out.println("  ------");
			}
			System.out.print(""+ (col + 1) + " ");
			
			for (int row=0; row < 4; row++)
			{
				if (myseats.seats[col][row].avail == true)
				{
					System.out.print("o ");
				}
				else
				{
					System.out.print("x ");
				}
				//need to print actual seating here
				
			}
		}
		System.out.println();
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
				//TODO need to code something to create reports here
			}
			else if (userInput.equals("s"))
			{
				printSeating();
			}
		}
	}
	
	
	//Process a seat for a passenger
	public void processPassenger()	{
		String sorrymsg = "Sorry. No Seats avail. Next Flight in 3 Hours";
		int[] myavailability = myseats.checkAvailability();
		
		if (myavailability[0] == 0) 
		{
			System.out.println(sorrymsg);
			return;
		}
		String msg;
		if (myavailability[3] == 0 || myavailability[4] == 0)
		{
			if (myavailability[3] == 0)
			{
			msg = "Only First Class Seats are available. " +
					"\nPress 'y' to accept or any other key to cancel";
			}
			else {
			msg = "Only Economy Seats are available. " +
					"\nPress 'y' to accept or any other key to cancel";
			}
			System.out.println(msg);
			userInput = scanObj.next().toString().trim().toLowerCase();
			if (!userInput.equals("y"))
			{
				System.out.println(sorrymsg);
				return;
			}
		}
		
		
		//pick class/section
		msg = "Please choose a section. Press 1 for First Class, 2 for Economy";
		System.out.println(msg);
		userInput = scanObj.next().toString().trim().toLowerCase();
		
		while (validateInput("class") == false)
		{
			System.out.println("Invalid Response." + msg);
			userInput = scanObj.next().toString().trim().toLowerCase();
			
		}
		char mysection = userInput.charAt(0);
		
		char mytype = 0; //workaround. char can't be null
		msg = "Please choose seat type preference." +
				"\nPress 'a' for aisle, 'w' for window.";
		if (myavailability[1] > 0 && myavailability[2] > 0)
		{
			System.out.println(msg);
			userInput = scanObj.next().toString().trim().toLowerCase();
			while (validateInput("seattype") == false)
			{ 
				System.out.println("Invalid Response. " + msg);
				userInput = scanObj.next().toString().trim().toLowerCase();
			}
			mytype = userInput.charAt(0);
		}
		
		System.out.println("Please Enter Your Name.");
		userInput = scanObj.next().toString().trim();
		while (validateInput("name") == false)
		{
			System.out.println("Invalid/Blank Response." + msg);
			userInput = scanObj.next().toString().trim();
		}
		String myname = userInput;
		myseats.bookSeat(myname, mysection, mytype);
		System.out.println("Seat Booked\n");
	}
	
	
	
	//validate User Input under various circumstances
	public boolean validateInput(String mytype){
		
		//JOptionPane.showMessageDialog(null, "Validate Input function has recieved:"  + userInput.length()+".");
		if (mytype == "class") //handle user input for class selection
		{
			
			if (userInput.equals("1") || userInput.equals("2"))
			{
				return true;
			}
			else
			{
				return false;
			}
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
	

