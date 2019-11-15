package commons;

public abstract class Person implements java.io.Serializable {
	private String firstName;
	private String lastName;

	// constructor
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	// getters
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	// setters
	public void setFirstName(String newFirstName) {
		this.firstName = newFirstName;
	}

	public void setLastName(String newLastName) {
		this.lastName = newLastName;
	}

	// abstract method
	public abstract boolean verifyLogin(String inputUsername, String inputPassword);
}
