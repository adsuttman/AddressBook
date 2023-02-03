import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {
	static List<Address> addresses = new ArrayList<Address>();
	static Scanner input = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		while(true) {
			//Print menu
			System.out.println("\n1) Add an entry\n"
					+ "2) Remove an entry\n"
					+ "3) Search for a specific entry\n"
					+ "4) Print Address Book\n"
					+ "5) Delete Book\n"
					+ "6) Quit\n");
			//prompt user for selection
			int response = promptInt("Please choose what you'd like to do with the database:", 1, 6);
			//switch to execute the user's desired action
			switch (response) {
			case 1:
				addEntry();
				break;
			case 2:
				removeEntry();
				break;
			case 3:
				break;
			case 4:
				printAddresses();
				break;
			case 5:
				break;
			case 6:
				quit();
			default:
				break;
		}
		}


	}

	//menu methods
	private static void quit() {
		System.out.println("Address book closed");
		System.exit(0);
		
	}
	
	private static void addEntry() {
		String firstName = prompt("First Name: ");
		String lastName = prompt("Last Name: ");
		String phone = prompt("Phone Number: ");
		String email = prompt("Email Address: ");
		addresses.add(new Address(firstName, lastName, phone, email));
		System.out.println("Added new entry!");
		
	}
	
	private static void removeEntry() {
		String email = prompt("Enter an entry's email to remove: ");
		for (Address entry: addresses) {
			if (entry.getEmail().equals(email)) {
				System.out.println("Deleted the following entry: ");
				printEntry(entry);
				addresses.remove(entry);
				break;
			}
		}
	}
	
	private static void printAddresses() {
		for (Address entry: addresses) {
			printEntry(entry);
		}
	}
	//helper methods
	private static void printEntry(Address entry) {
		System.out.println("************\n" + entry + "************\n");
	}
	//prompt methods
	private static String prompt(String question) {
		while (true) {
			System.out.println(question);
			String result = input.nextLine();
			if (result.isEmpty()) {
				System.out.println("Please enter something!");
			} else {
				return result;
			}
		}
	}
	
	private static int promptInt(String question) {
		while(true) {
			System.out.println(question);
			String result = input.nextLine();
			try {
				if (result.matches("\\d+")) {
					return Integer.parseInt(result);
				} else {
					System.out.println("Please enter a postive integer!");
				}
			} catch (NumberFormatException e) {
				System.out.println("Please don't enter a number that's too big to be stored in an int!");
			}
			
		}		
	}
	
	private static int promptInt(String question, int min, int max) {
		while(true) {
			int result = promptInt(question);
			if (result < min || result > max) {
				System.out.printf("Please enter a number between %d and %d!\n", min, max);
			} else {
				return result;
			}
			
		}		
	}
	


}
