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
	
	public Address(String[] data) {
		this.firstName = data[0];
		this.lastName = data[1];
		this.phone = data[2];
		this.email = data[3];
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
	

	public String formatData(String delimiter) {
		StringJoiner data = new StringJoiner(delimiter, "", "\n");
		data.add(firstName);
		data.add(lastName);
		data.add(phone);
		data.add(email);
		return data.toString();
	}

	@Override
	public String toString() {
		return "First Name: " + firstName + "\nLast Name: " + lastName + "\nPhone Number: " + phone + "\nEmail Address: " + email
				+ "\n";
	}
	
	
}
