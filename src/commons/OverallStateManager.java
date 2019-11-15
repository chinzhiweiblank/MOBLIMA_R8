package commons;

import movie.MovieListingStateManager;

public class OverallStateManager {
	private static OverallStateManager singleton_instance = null;
	private Person userAccount;
	private Person adminAccount;
	private boolean UserloginStatus;
	private boolean AdminloginStatus;
	private CineplexStateManager cineplexStateManager;
	private MovieListingStateManager movieListingStateManager;
	private AccountStateManager accountStateManager;
	private ConfigurationStateManager configurationStateManager;

	// singleton initialisation
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

	public static OverallStateManager getInstance() {
		if (singleton_instance == null) {

			// Synchronised ensures that only one thread is able to create the singleton
			synchronized (OverallStateManager.class) {
				singleton_instance = new OverallStateManager();
			}
		}
		return singleton_instance;
	}

	public void setCurrentUserAccount(Person account, AccountType accountType) {
		if (accountType.equals(AccountType.USER)) {
			this.UserloginStatus = true;
			this.userAccount = account;
		} else if (accountType.equals(AccountType.ADMIN)) {
			this.AdminloginStatus = true;
			this.adminAccount = account;
		}
	}

	public Person getCurrentUserAccount(AccountType accountType) {
		if (accountType.equals(AccountType.USER)) {
			return this.userAccount;
		} else {
			return this.adminAccount;
		}
	}

	public boolean getloginStatus(AccountType accountType) {
		if (accountType.equals(AccountType.USER)) {
			return this.UserloginStatus;
		} else {
			return this.AdminloginStatus;
		}
	}

	// provide access to all other state managers
	public CineplexStateManager getCineplexStateManager() {
		return this.cineplexStateManager;
	}

	public MovieListingStateManager getMovieListingStateManager() {
		return this.movieListingStateManager;
	}

	public AccountStateManager getAccountStateManager() {
		return this.accountStateManager;
	}

	// serialize
	public void serialize() {
		this.cineplexStateManager.serialize();
		this.movieListingStateManager.serialize();
		this.accountStateManager.serialize();
		this.configurationStateManager.serialize();
	}

	public void deserialize() {
		this.cineplexStateManager.deserialize();
		this.movieListingStateManager.deserialize();
		this.accountStateManager.deserialize();
		this.configurationStateManager.deserialize();
	}

}
