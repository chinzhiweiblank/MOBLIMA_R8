package Controller;

import Model.AccountType;
import Model.Person;

/**
 * Main Controller Class managing the other controller classes
 */
public class OverallStateManager {

	/**
	 * Ensures that the OverallStateManager is a singleton
	 */
	private static OverallStateManager singleton_instance = null;

	/**
	 * User Account
	 */
	private Person userAccount;

	/**
	 * Admin Account
	 */
	private Person adminAccount;

	/**
	 * Status of the user login
	 */
	private boolean UserloginStatus;

	/**
	 * Status of the admin login
	 */
	private boolean AdminloginStatus;

	/**
	 * Manager for the states of the cineplexes
	 */
	private CineplexStateManager cineplexStateManager;

	/**
	 * Manager for the states of the movie listings
	 */
	private MovieListingStateManager movieListingStateManager;

	/**
	 * Manager for the states of the accounts
	 */
	private AccountStateManager accountStateManager;

	/**
	 * Manager for the configuration system settings
	 */
	private ConfigurationStateManager configurationStateManager;

	/**
	 * Initialization of the OverallStateManager Singleton
	 */
	private OverallStateManager() {
		this.userAccount = null;
		this.adminAccount = null;
		this.UserloginStatus = false;
		this.AdminloginStatus = false;
		this.cineplexStateManager = CineplexStateManager.getInstance();
		this.movieListingStateManager = MovieListingStateManager.getInstance();
		this.accountStateManager = AccountStateManager.getInstance();
		this.configurationStateManager = ConfigurationStateManager.getInstance();
	}

	/**
	 * Obtains an instance of OverallStateManager
	 * @return an instance of OverallStateManager
	 */
	public static OverallStateManager getInstance() {
		if (singleton_instance == null) {

			// Synchronised ensures that only one thread is able to create the singleton
			synchronized (OverallStateManager.class) {
				singleton_instance = new OverallStateManager();
			}
		}
		return singleton_instance;
	}

	/**
	 * Sets the current user account using an account and the type of an account
	 * @param account the account of the user
	 * @param accountType the type of the account
	 */
	public void setCurrentUserAccount(Person account, AccountType accountType) {
		if (accountType.equals(AccountType.USER)) {
			this.UserloginStatus = true;
			this.userAccount = account;
		} else if (accountType.equals(AccountType.ADMIN)) {
			this.AdminloginStatus = true;
			this.adminAccount = account;
		}
	}

	/**
	 * Obtains the current user account using a account type
	 * @param accountType the type of the account
	 * @return user account of the specified account type
	 */
	public Person getCurrentUserAccount(AccountType accountType) {
		if (accountType.equals(AccountType.USER)) {
			return this.userAccount;
		} else {
			return this.adminAccount;
		}
	}

	/**
	 * Obtains login status of the account
	 * @param accountType the type of the account
	 * @return login status of the account
	 */
	public boolean getloginStatus(AccountType accountType) {
		if (accountType.equals(AccountType.USER)) {
			return this.UserloginStatus;
		} else {
			return this.AdminloginStatus;
		}
	}

	/**
	 * Obtains the CineplexStateManager
	 * @return the CineplexStateManager
	 */
	public CineplexStateManager getCineplexStateManager() {
		return this.cineplexStateManager;
	}

	/**
	 * Obtains the MovieListingStateManager
	 * @return the MovieListingStateManager
	 */
	public MovieListingStateManager getMovieListingStateManager() {
		return this.movieListingStateManager;
	}

	/**
	 * Obtains the AccountStateManager
	 * @return the AccountStateManager
	 */
	public AccountStateManager getAccountStateManager() {
		return this.accountStateManager;
	}

	/**
	 * Generates a byte stream of data from the state of an object
	 */
	public void serialize() {
		this.cineplexStateManager.serialize();
		this.movieListingStateManager.serialize();
		this.accountStateManager.serialize();
		this.configurationStateManager.serialize();
	}

	/**
	 * Turns a byte stream of data into the state of an object
	 */
	public void deserialize() {
		this.cineplexStateManager.deserialize();
		this.movieListingStateManager.deserialize();
		this.accountStateManager.deserialize();
		this.configurationStateManager.deserialize();
	}

}
