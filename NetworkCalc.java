// Anthony Templeton 2016
// Networking Calculation Tool
// This program is designed to help calculate various types of network delay and throughput
import java.util.Scanner;

public class NetworkCalc {
	// define global vars and global scanner
	private static final double soL = (3.0 * Math.pow(10, 8));
	private static final double megaConv = Math.pow(10, 6);
	private static final double kiloConv = Math.pow(10, 3);
	private static final double milliConv = Math.pow(10, -3);
	private static final double microConv = Math.pow(10, -6);
	
	private static double distance = 0;
	private static double size = 0;
	private static double tRate = 0;
	private static double answer = 0;
	private static double propDelay = 0;
	
	static Scanner inCalc = new Scanner(System.in);
	
	public static void main(String args[]){
		getMenu();
	}// main()

	private static void getMenu() {
		String response = "";
		while(!response.equals("EXIT")){
			System.out.println("Please select a calculation.");
			System.out.println("1) Throughput");
			System.out.println("2) Response time (Delay)");
			System.out.println("Type Exit to quit the application");
			
			response = inCalc.next();
			
			switch(response){
			case "1": 
					throughputCalc();
					System.out.println("");
					break;
			case "2": 
					delayCalc();
					System.out.println("");
					break;
			default: response = "EXIT";
				break;
			}// switch()
			
		}// while()
		
	}// GetMenu()

	public static void throughputCalc(){
		double totalMaxTime = 0;
		getIntroNums();
		
		System.out.println("What time units?\n1) Milliseconds\n2) Microseconds");
		int timeUnits = inCalc.nextInt();
		if (timeUnits == 1){
			System.out.println("Enter max time in milliseconds");
			totalMaxTime = inCalc.nextDouble();
			totalMaxTime *= milliConv;
		}else if (timeUnits == 2){
			System.out.println("Enter max time in microseconds");
			totalMaxTime = inCalc.nextDouble();
			totalMaxTime *= microConv;
		}
		
		double estDelay = totalMaxTime - propDelay;
		double r = size/estDelay;
		System.out.println("How would you like your answer?\n1) kb/s\n2) Mb/s");
		int choice = inCalc.nextInt();
		if (choice == 1){
			answer = r * milliConv;
		}else if (choice == 1){
			answer = r * microConv;
		}
		System.out.println(Math.round(answer) + " kb/s");
	}// ThroughputCalc()

	public static void delayCalc(){
		getIntroNums();
		
		System.out.println("Is your rate in: \n 1) kb/s \n 2) Mb/s");
		int rateUnits = inCalc.nextInt();
		if (rateUnits == 1){
			System.out.println("Enter transmission rate in kb/s");
			tRate = inCalc.nextDouble();
			tRate *= kiloConv;
		}else if (rateUnits == 2){
			System.out.println("Enter transmission rate in Mb/s: ");
			tRate = inCalc.nextDouble();
			tRate *= megaConv;
		}
		double xmitDelay = size/tRate;
		answer = propDelay + xmitDelay;
		
		System.out.println("How would you like your answer?\n1) Seconds\n2) Milliseconds\n3) Microseconds");
		int ansUnits = inCalc.nextInt();
		switch (ansUnits){
		case 1: 
			System.out.println(answer + " second delay.");
			break;
		case 2: 
			answer *= kiloConv;
			System.out.println((int)(Math.round(answer)) + " millisecond delay.");
			break;
		case 3: 
			answer *= megaConv;
			System.out.println((int)(Math.round(answer)) + " microsecond delay.");
			break;
		default: 
			System.out.println("Answer in Microseconds");
			break;
		}// switch()
	}// DelayCalc()
	
	public static void getIntroNums(){
		System.out.print("Enter distance in Km (D): ");
		distance = inCalc.nextDouble();
		distance *= kiloConv;
		propDelay = distance/soL;
		
		System.out.println("Enter file size in bits (L): " );
		size = inCalc.nextDouble();
	}// GetIntroNums()
}
