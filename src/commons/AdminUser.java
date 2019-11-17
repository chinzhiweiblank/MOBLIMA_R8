package commons;

/**
 * Represents a user with administrative privileges
 */
public class AdminUser extends Person {
	/**
	 * Username of the administrator account
	 */
	private String username;
	/**
	 * Password of the administrator account
	 */
	private String password;

	/**
	 * Constructor for the administrator
	 * @param firstName the user's first name
	 * @param lastName the user's last name
	 * @param username the user's username
	 * @param password the user's password
	 */
	public AdminUser(String firstName, String lastName, String username, String password) {
		super(firstName, lastName);
		this.username = username;
		this.password = password;
	}

	/**
	 * Obtains the username of the user
	 * @return the user's username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Obtains the password of the user
	 * @return the user's password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * Sets the username for the user
	 * @param newUsername the new username of the user
	 */
	public void setUsername(String newUsername) {
		this.username = newUsername;
	}

	/**
	 * Sets the password for the user
	 * @param newPassword the new password of the user
	 */
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

	/**
	 * Verifies the username and password for a successful login
	 * @param inputUsername Username of user
	 * @param inputPassword Password of user
	 * @return True if the username and password matches an account in the database and False otherwise
	 */
	public boolean verifyLogin(String inputUsername, String inputPassword) {
		if (this.username.equals(inputUsername) && this.password.equals(inputPassword)) {
			return true;
		} else {
			return false;
		}
	}
}
