package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import CSVWriter.BlockbusterTicketManager;
import CSVWriter.ImaxTicketManager;
import CSVWriter.Movie3DTicketManager;
import CSVWriter.TicketManager;
import commons.AccountType;
import commons.Booking;
import commons.BookingManager;
import commons.Cinema;
import commons.Cinema.CinemaType;
import commons.CineplexStateManager;
import commons.ConfigurationStateManager;
import commons.MovieGoer;
import commons.OverallStateManager;
import movie.Movie.MovieType;
import movie.MovieListingStateManager;

public class SeatsMenu extends View {

	private String movieName;
	private MovieType movieType;
	private CinemaType cinemaType;

	protected SeatsMenu(String movieName, MovieType movieType, CinemaType cinemaType) {
		this.movieName = movieName;
		this.movieType = movieType;
		this.cinemaType = cinemaType;
	}

	Scanner sc = new Scanner(System.in);
	private CineplexStateManager cineplexStateManager = CineplexStateManager.getInstance();
	private OverallStateManager overallStateManager = OverallStateManager.getInstance();
	private MovieListingStateManager movieListingStateManager = MovieListingStateManager.getInstance();
	private ConfigurationStateManager configurationStateManager = ConfigurationStateManager.getInstance();
	private TicketManager ticketManager;

	@Override
	protected int options() {
		System.out.println("----------------------------");
		System.out.println("1) Book!");
		System.out.println("2) Previous menu");
		return 2;
	}

	@Override
	protected void runMenu() {

		String cineplex = inputCineplex();
		int timing = inputTiming(cineplex);
		String cinema = inputCinema(cineplex, this.movieName, timing, this.movieType);

		while (true) {
			int choice = getInput(options());
			switch (choice) {
			case 1:
				if (!overallStateManager.getloginStatus(AccountType.USER)) {
					// if user has not logged in yet
					display(this, new loginMenu(AccountType.USER));
				}
				inputSeatSelection(movieName, timing, cinema, cineplex, this.movieType);
				System.out.printf("%s ticket has been booked\n", movieName);

				// go back to home
				new MainMenu().runMenu();
				break;
			case 2:
				getPrevView();
				break;
			default:
				System.out.println("Please input a valid integer choice");
			}
		}

	}

	private void inputSeatSelection(String movieName, int showTime, String cinemaId, String cineplex,
			MovieType movieType) {

		System.out.println("Enter number of seats: ");
		int n = sc.nextInt();
		cineplexStateManager.printSeatAvailability(cineplex, movieName, showTime, cinemaId, movieType,this.cinemaType);

		String date;
		loop: while(true){
			System.out.println("Enter date (YYYYMMDD):");
			sc.nextLine();
			date = sc.nextLine();
			if (configurationStateManager.verifyDate(date)){
				break loop;
			}
		}

		for (int i = 1; i <= n; i++) {
			int success = 0;
			while (success == 0) {
				System.out.println("Enter row number of seat " + i + ": ");
				int row = sc.nextInt();
				System.out.println("Enter col number of seat " + i + ": ");
				int col = sc.nextInt();
				System.out.println("Enter age: ");
				int age = sc.nextInt();

				MovieGoer movieGoer = (MovieGoer) overallStateManager.getCurrentUserAccount(AccountType.USER);



				switch (this.movieType) {
				case Blockbuster:
					ticketManager = new BlockbusterTicketManager();
					break;
				case ThreeD:
					ticketManager = new Movie3DTicketManager();
					break;
				case IMAX:
					ticketManager = new ImaxTicketManager();
					break;
				default:
					break;
				}

				double ticketPrice;
				int cinemaTypeInt = enumToInt(this.cinemaType);
				try {
					ticketPrice = ticketManager.readPrice(cinemaTypeInt,
							configurationStateManager.getAgeType(age),
							configurationStateManager.getTodayDateType(date));
					Booking newBooking = new Booking(movieName, cinemaId, cineplex, row, col, showTime,
							movieGoer.getEmail(), movieGoer.getFullName(), movieGoer.getMobile(), ticketPrice,
							this.movieType,this.cinemaType);
					BookingManager bookingManager = new BookingManager(newBooking, cineplexStateManager, movieGoer,
							movieListingStateManager);
					success = bookingManager.bookTickets();
					if (success == 0) {
						System.out.println("Seat is already taken");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String inputCineplex() {
		while (true) {
			System.out.println("Enter Cineplex: ");
			String cineplexName = sc.nextLine();
			if (cineplexStateManager.verifyCineplex(cineplexName)) {
				return cineplexName;
			} else {
				System.out.println("cineplex does not exist!");
			}
		}
	}

	private int inputTiming(String cineplexName) {
		while (true) {
			System.out.println("Enter timing in 24 hour format ,eg. 2300: ");
			int timing = sc.nextInt();

			ArrayList<Cinema> cinemaOptions = cineplexStateManager.readCineplexState(cineplexName)
					.findShowTime(this.movieName, this.movieType,this.cinemaType);

			for (Cinema cinema : cinemaOptions) {
				if (cinema.getshowTime() == timing) {
					return timing;

				} else {
					System.out.println("There are no shows showing at this time");
				}
			}
		}
	}

	private String inputCinema(String Cineplex, String movieName, int timing, MovieType movieType) {

		while (true) {
			cineplexStateManager.printSeatAvailability(Cineplex, movieName, movieType, timing,this.cinemaType);
			System.out.println("Enter Cinema: ");
			String cinemaName = sc.next();

			ArrayList<Cinema> cinemaOptions = cineplexStateManager.readCineplexState(Cineplex)
					.findCinemaUsingTime(movieName, timing, movieType,this.cinemaType);

			for (Cinema cinema : cinemaOptions) {
				if (cinema.getUniqueId().equals(cinemaName)) {
					return cinemaName;

				} else {
					System.out.println("There are no cinemas showing at this time");
				}
			}
		}
	}

	private int enumToInt(CinemaType cinemaType) {
		switch (cinemaType) {
		case Regular:
			return 1;
		case Platinum:
			return 2;
		case Goldclass:
			return 3;
		default:
			return -1;
		}
	}
}
