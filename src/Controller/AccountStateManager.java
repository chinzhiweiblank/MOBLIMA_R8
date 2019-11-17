package Controller;

import Model.AccountType;
import Model.AdminUser;
import Model.MovieGoer;
import Model.Person;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
/**
 * Represents the Controller Class for Managing the Accounts for Admin and Movie Goers
 */
public class AccountStateManager implements java.io.Serializable {
	/**
	 * To make this class a Singleton
	 */
	private static AccountStateManager singleton_instance = null;
	/**
	 * A set of usernames and the Moviegoer instances
	 */
	private Hashtable<String, MovieGoer> movieGoerStateManager; // key is username,
	/**
	 * A set of usernames and the Administrator instances
	 */
	private Hashtable<String, AdminUser> adminUserStateManager; // key is username,

	// singleton initialisation

	/**
	 * Initialization of Singleton
	 */
	private AccountStateManager() {
		this.movieGoerStateManager = new Hashtable<String, MovieGoer>();
		this.adminUserStateManager = new Hashtable<String, AdminUser>();
	}

	/**
	 * Only allows one thread to create the singleton
	 * @return an instance of the AccountStateManager
	 */
	public static AccountStateManager getInstance() {
		if (singleton_instance == null) {

			// Synchronised ensures that only one thread is able to create the singleton
			synchronized (AccountStateManager.class) {
				singleton_instance = new AccountStateManager();
				singleton_instance.deserialize();
			}
		}
		return singleton_instance;
	}

	/**
	 * Returns the account details based on the username
	 * @param userName name of user
	 * @param accountType type of account (admin/moviegoer)
	 * @return Person, the user of the account
	 */
	public Person readAccount(String userName, AccountType accountType) {
		if (accountType == AccountType.ADMIN) {
			return this.adminUserStateManager.get(userName);
		} else if (accountType == AccountType.USER) {
			return this.movieGoerStateManager.get(userName);
		} else {
			System.out.println("Wrong account type value");
			return null;
		}
	}

	/**
	 * Creates account for a moviegoer
	 * @param movieGoer person going to the movies
	 */
	public void createAccount(MovieGoer movieGoer) {
		this.movieGoerStateManager.put(movieGoer.getEmail(), movieGoer);
	}

	/**
	 * Creates account for an administrator
	 * @param adminUser administrator
	 */
	public void createAccount(AdminUser adminUser) {
		this.adminUserStateManager.put(adminUser.getUsername(), adminUser);
	}

	/**
	 * Converts the state of an object into a byte stream of data
	 */
	public void serialize() {

		try {
			String filename = "data/AccountState.ser";
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);

			// Method for serialization of object
			out.writeObject(this);

			out.close();
			file.close();

			System.out.println("Object has been serialized");
		}

		catch (IOException exceptionMessage) {
			System.out.println(exceptionMessage);
		}
	};

	/**
	 * Converts a byte stream of data into an object for storage
	 */
	public void deserialize() {
		// Deserialization
		try {
			// Reading the object from a file
			String filename = "data/AccountState.ser";
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);

			// Method for deserialization of object
			AccountStateManager deserializeObj = (AccountStateManager) in.readObject();

			in.close();
			file.close();
			this.adminUserStateManager = deserializeObj.adminUserStateManager;
			this.movieGoerStateManager = deserializeObj.movieGoerStateManager;
		}

		catch (IOException exceptionMessage) {
			System.out.println("Account state manager");
			System.out.println("IOexception is caught");
		}

		catch (ClassNotFoundException exceptionMessage) {
			System.out.println("Account state manager");
			System.out.println("ClassNotFoundException is caught");
		}

	};

	/**
	 * Verifies whether the set of username and password exist in the database
	 * @param Username name of user
	 * @param password password of user
	 * @param accountType type of account (admin/moviegoer)
	 * @return True if the username and password matches the accounts in the database and false otherwise
	 */
	public boolean verifyAccount(String Username, String password, AccountType accountType) {

		if (accountType == AccountType.ADMIN) {
			if (this.adminUserStateManager.containsKey(Username)) {
				Person account = this.adminUserStateManager.get(Username);
				return account.verifyLogin(Username, password);
			} else {
				return false;
			}
		} else {
			if (this.movieGoerStateManager.containsKey(Username)) {
				Person account = this.movieGoerStateManager.get(Username);
				return account.verifyLogin(Username, password);
			} else {
				return false;
			}
		}
	}
}
