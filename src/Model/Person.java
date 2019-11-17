package Model;

/**
 * Model class representing a person
 */
public abstract class Person implements java.io.Serializable {
	/**
	 * first name of the person
	 */
	private String firstName;
	/**
	 * last name of the person
	 */
	private String lastName;

	/**
	 * Constructor of the Person object
	 * @param firstName first name of the person
	 * @param lastName last name of the person
	 */
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Obtains the first name of the person
	 * @return the first name of the person
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Obtains the last name of the person
	 * @return the last name of the person
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Obtains the full name of the person
	 * @return the full name of the person
	 */
	public String getFullName() {return firstName + " " + lastName;}

	/**
	 * Sets the first name of the person
	 * @param newFirstName the first name of the person
	 */
	public void setFirstName(String newFirstName) {
		this.firstName = newFirstName;
	}

	/**
	 * Sets the last name of the person
	 * @param newLastName the last name of the person
	 */
	public void setLastName(String newLastName) {
		this.lastName = newLastName;
	}

	/**
	 * Verifies whether a login is successful using username and password
	 * @param inputUsername username of the user
	 * @param inputPassword password of the user
	 * @return true if login is successful and false otherwise
	 */
	public abstract boolean verifyLogin(String inputUsername, String inputPassword);
}
