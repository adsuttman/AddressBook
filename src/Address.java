import java.io.FileWriter;
import java.io.IOException;
import java.util.StringJoiner;

public class Address {
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	
	//constructor
	public Address(String firstName, String lastName, String phone, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
	}

	//Getters and Setters
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void saveToFile(FileWriter file) throws IOException {
		StringJoiner data = new StringJoiner(",", "", "\n");
		data.add(firstName);
		data.add(lastName);
		data.add(phone);
		data.add(email);
		file.append(data.toString());
	}

	@Override
	public String toString() {
		return "First Name: " + firstName + "\nLast Name: " + lastName + "\nPhone Number: " + phone + "\nEmail Address: " + email
				+ "\n";
	}
	
	
}
