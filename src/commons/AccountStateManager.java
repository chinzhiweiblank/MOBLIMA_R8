package commons;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

public class AccountStateManager implements java.io.Serializable {
	private static AccountStateManager singleton_instance = null;

	private Hashtable<String, MovieGoer> movieGoerStateManager; // key is username,
	private Hashtable<String, AdminUser> adminUserStateManager; // key is username,

	// singleton initialisation
	private AccountStateManager() {
		this.movieGoerStateManager = new Hashtable<String, MovieGoer>();
		this.adminUserStateManager = new Hashtable<String, AdminUser>();
	}

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

	// CRUD operations
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

	public void createAccount(MovieGoer movieGoer) {
		this.movieGoerStateManager.put(movieGoer.getEmail(), movieGoer);
	}

	public void createAccount(AdminUser adminUser) {
		this.adminUserStateManager.put(adminUser.getUsername(), adminUser);
	}

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
		}

		catch (ClassNotFoundException exceptionMessage) {
			System.out.println("ClassNotFoundException is caught");
		}

	};

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
