import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {
	static List<Address> addresses = new ArrayList<Address>();
	static Scanner input = new Scanner(System.in);
	static final String DELIMITER = ",";
	
	
	public static void main(String[] args) {
		while(true) {
			//Print menu
			System.out.println("\n1) Add an entry\n"
					+ "2) Remove an entry\n"
					+ "3) Search for a specific entry\n"
					+ "4) Print Address Book\n"
					+ "5) Delete Book\n"
					+ "6) Save to File\n"
					+ "7) Load from File\n"
					+ "8) Quit\n");
			//prompt user for selection
			int response = promptInt("Please choose what you'd like to do with the database:", 1, 8);
			//switch to execute the user's desired action
			switch (response) {
			case 1:
				addEntry();
				break;
			case 2:
				removeEntry();
				break;
			case 3:
				search();
				break;
			case 4:
				printAddresses();
				break;
			case 5:
				deleteBook();
				break;
			case 6:
				save();
				break;
			case 7:
				load();
				break;
			case 8:
				quit();
			default:
				break;
			}
		}


	}
	//file manipulation methods
	private static void save() {
		String fileName = prompt("Enter name of file to save to: ");
		
		try {
			BufferedWriter file = new BufferedWriter(new FileWriter(fileName));
			try {
				for (Address entry : addresses) {
					file.append(entry.formatData(DELIMITER));
				}
				System.out.println("Successfully saved to " + fileName);
			} finally {
				file.close();	
			}

			
		} catch (IOException e) {
			System.err.println("An error occured!\n"+ e);
		} 
		
	}
	
	private static void load() {
		if(confirm("This will delete your current data!\nAre you sure?")) {
			String fileName = prompt("Enter name of file to load from: ");
			
			try {
				BufferedReader file = new BufferedReader(new FileReader(fileName));
				try {
					int c;
					StringBuilder fullData = new StringBuilder();
					while((c=file.read()) != -1) {
						fullData.append((char)c);
					}
					//System.out.println(fullData);
					addresses.clear();
					for(String data : fullData.toString().split("\n")) {
						//System.out.println("Data = " + data);
						String[] entry = data.split(DELIMITER);
//						for (String item : entry) {
//							System.out.print(item);
//							System.out.print("+");
//						}
						System.out.println("");
						addresses.add(new Address(entry));
					}
					System.out.println("Successfully loaded from " + fileName);
				} finally {
					file.close();	
				}
			} catch (IOException e) {
				System.err.println("An error occured!\n"+ e);
			}
		} else {
			System.out.println("Ok, returning to menu...");
		}
	}

	//menu methods
	private static void quit() {
		if(confirm("Do you want to save before quitting?")) {
			save();
		}
		System.out.println("Address book closed");
		System.exit(0);
	}
	
	private static void addEntry() {
		String firstName = prompt("First Name: ");
		String lastName = prompt("Last Name: ");
		String phone = prompt("Phone Number: ");
		String email = prompt("Email Address: ");
		if (checkEmail(email)) {
			System.out.println("An entry with that email already exists!");
		} else {
			addresses.add(new Address(firstName, lastName, phone, email));
			System.out.println("Added new entry!");
		}
	}
	
	private static void removeEntry() {
		String email = prompt("Enter an entry's email to remove: ");
		boolean found = false;
		for (Address entry: addresses) {
			if (entry.getEmail().equals(email)) {
				System.out.println("Deleted the following entry: ");
				printEntry(entry);
				addresses.remove(entry);
				found = true;
				break;
			}
		}
		if (!found) {
			System.out.println("No entry with that email found.");
		}
	}
	
	private static void search() {
		//Print menu
		System.out.println("\n1) First Name\n"
				+ "2) Last Name\n"
				+ "3) Phone Number\n"
				+ "4) Email Address\n");
		//prompt user for selection
		int response = promptInt("Choose a search type: ", 1, 4);
		String searchTerm = prompt("Enter your search: ");
		//switch to execute the user's desired action
		boolean empty = true;
		for (Address entry: addresses) {
			switch (response) {
			case 1:
				if (entry.getFirstName().contains(searchTerm)) {
					printEntry(entry);
					empty = false;
				}
				continue;
			case 2:
				if (entry.getLastName().contains(searchTerm)) {
					printEntry(entry);
					empty = false;
				}
				continue;
			case 3:
				if (entry.getPhone().contains(searchTerm)) {
					printEntry(entry);
					empty = false;
				}
				continue;
			case 4:
				if (entry.getEmail().contains(searchTerm)) {
					printEntry(entry);
					empty = false;
				}
				continue;
			default:
				break;
			}
		}
		if (empty) {
			System.out.println("No entries found.");
		}

	}
	
	private static void printAddresses() {
		if (addresses.isEmpty()) {
			System.out.println("Address book is empty!");
		} else {
			for (Address entry: addresses) {
				printEntry(entry);
			}
		}
	}
	
	private static void deleteBook() {
		if (confirm("This will delete all of your entries!\nAre you sure?")) {
			addresses.clear();
			System.out.println("Address boook cleared!");
		} else {
			System.out.println("Ok, returning to menu...");
		}

	}
	
	//helper methods
	private static void printEntry(Address entry) {
		System.out.println("************\n" + entry + "************\n");
	}
	
	private static boolean checkEmail(String email) {
		for (Address entry: addresses) {
			if (entry.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean confirm(String message) {
		String response = prompt(message + "\ny/n");
		return response.toLowerCase().startsWith("y");
	}
	
	//prompt methods (copied from MadLib project)
	private static String prompt(String question) {
		while (true) {
			System.out.println(question);
			String result = input.nextLine();
			if (result.isEmpty()) {
				System.out.println("Please enter something!");
			} else if (result.contains(DELIMITER)) {
				System.out.println("Your response can't contain " + DELIMITER);
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
