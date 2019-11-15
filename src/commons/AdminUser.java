package commons;

public class AdminUser extends Person {

	private String username;
	private String password;

	// constructor
	public AdminUser(String firstName, String lastName, String username, String password) {
		super(firstName, lastName);
		this.username = username;
		this.password = password;
	}

	// getters
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	// setters
	public void setUsername(String newUsername) {
		this.username = newUsername;
	}

	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

	// functions for admin user
	public boolean verifyLogin(String inputUsername, String inputPassword) {
		if (this.username.equals(inputUsername) && this.password.equals(inputPassword)) {
			return true;
		} else {
			return false;
		}
	}

	public void configSettings() {
		return;
	}

	public void updateShowTime() {
		return;
	}

	// TODO functions for admin user - createListing, readListing, updateListing,
	// deleteListing

}
