package bms.application;

import java.util.Scanner;

import bms.problemdomain.Book;
import bms.problemdomain.ChildrensBook;
import bms.problemdomain.Cookbook;
import bms.problemdomain.Paperback;
import bms.problemdomain.Periodical;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * The BookManager program will allow the public and staff to checkout books, find books by title,
 * display lists of books based on their types and sub types, and finally display a random list of books
 * depending on how many the person wants to see. The BookManager has a preset list of books with ISBNs,
 * call numbers, total and available books, their authors, and more specifications based on the book type.
 * The four book types that the BookManager contains are children's books, cook books, paperbacks, and periodicals
 * each with their own sub types that you will be able to see displayed using the program. 
 * 
 * @author Roman Kapitoulski
 * @author Nicholas Costa
 * @author Joshua Lokhorst
 * @author Jinki Lee
 * @version September 28, 2022
 */
public class BookManager {
	public static void main(String[] args) throws Exception {
		// Create a list of books from parseData method and assign it to variable bookLibrary
		ArrayList<Book> bookLibrary = parseData();
		
		// Create condition for main program to run until user wants to quit
		boolean running = true;
		
		// Initiate main loop
		while (running) {
			
			displayMenu();
			
			// Based on user's choice invoke relevant methods
			switch(userChoice()) {
				case 1:
					checkBook(bookLibrary);
					break;
				case 2:
					findBook(bookLibrary);
					break;
				case 3:
					displayType(bookLibrary);
					break;
				case 4:
					displayRandomList(bookLibrary);
					break;
				case 5:
					saveAndExit(bookLibrary);
					running = false;
					break;
			}
		}
	}
	
	/**
	 * Parses a text file called books.txt located in the resource folder.
	 * Goes line by line to extract data that will be used to create 
	 * specific types of books based on the ending of the ISBN.
	 * Returns an ArrayList of Books to the main method.
	 * 
	 * @returns A list of books.
	 */
	public static ArrayList<Book> parseData() throws Exception {
		// Create index variables to collect values
		long isbn = 0;
		String callNumber = "";
		int available = 0;
		int total = 0;
		String title = "";
	    String authors = "";
		char format = '0';
		String publisher = "";
		char diet = '0';
		short year = 0;
		char genre = '0';
		char frequency = '0';
		
		// Create a File object from the books.txt
		File bookList = new File("res/books.txt");
		
		// Create a Scanner object for parsing the bookList file object
		Scanner listParser = new Scanner(bookList);
		
		// Create book list
		ArrayList<Book> listOfBooks = new ArrayList<Book>();
		
		
		// Parse through text file line by line
		while (listParser.hasNextLine()) {
			String currentLine;
			String isbnEnd;
			
			// Current line of list
			currentLine = listParser.nextLine();
			
			// Create scanner to parse current line for values
			Scanner parseLine = new Scanner(currentLine);
			parseLine.useDelimiter(";");
			
			
			// Collect the character at the 12th position in the current line which is the last number of ISBN
			isbnEnd = currentLine.substring(12, 13);
			
			// Check if ChildrensBook
			if (isbnEnd.equals("0") || isbnEnd.equals("1")) {
				// Parse through line to index values for constructor
				while (parseLine.hasNext()) {
					isbn = Long.parseLong(parseLine.next());
					callNumber = parseLine.next();
					available = Integer.parseInt(parseLine.next());
					total = Integer.parseInt(parseLine.next());
					title = parseLine.next();
					authors = parseLine.next();
					format = parseLine.next().charAt(0);
					
				}
				ChildrensBook currentBook = new ChildrensBook(isbn, callNumber, available, total, title, authors, format);
				listOfBooks.add(currentBook);
			}
			
			// Check if Cookbook
			if (isbnEnd.equals("2") || isbnEnd.equals("3")) {
				// Parse through line to index values for constructor
				while (parseLine.hasNext()) {
					isbn = Long.parseLong(parseLine.next());
					callNumber = parseLine.next();
					available = Integer.parseInt(parseLine.next());
					total = Integer.parseInt(parseLine.next());
					title = parseLine.next();
					publisher = parseLine.next();
					diet = parseLine.next().charAt(0);
					
				}
				Cookbook currentBook = new Cookbook(isbn, callNumber, available, total, title, publisher, diet);
				listOfBooks.add(currentBook);
			}
			
			// Check if Paperback
			if (isbnEnd.equals("4") || isbnEnd.equals("5") || isbnEnd.equals("6") || isbnEnd.equals("7")) {
				// Parse through line to index values for constructor
				while (parseLine.hasNext()) {
					isbn = Long.parseLong(parseLine.next());
					callNumber = parseLine.next();
					available = Integer.parseInt(parseLine.next());
					total = Integer.parseInt(parseLine.next());
					title = parseLine.next();
					authors = parseLine.next();
					year = Short.parseShort(parseLine.next());
					genre = parseLine.next().charAt(0);
					
				}
				Paperback currentBook = new Paperback(isbn, callNumber, available, total, title, authors, year, genre);
				listOfBooks.add(currentBook);
			}
			
			// Check if Periodical
			if (isbnEnd.equals("8") || isbnEnd.equals("9")) {
				// Parse through line to index values for constructor
				while (parseLine.hasNext()) {
					isbn = Long.parseLong(parseLine.next());
					callNumber = parseLine.next();
					available = Integer.parseInt(parseLine.next());
					total = Integer.parseInt(parseLine.next());
					title = parseLine.next();
					frequency = parseLine.next().charAt(0);
				}
				Periodical currentBook = new Periodical(isbn, callNumber, available, total, title, frequency);
				listOfBooks.add(currentBook);
			}
		}
		
		return listOfBooks;
	}
	
	/**
	 * Displays the menu output or user interface
	 */
	public static void displayMenu() {
		System.out.print("\nWelcome in ABC Book Company: How May We Assist You?\n"
				+ "1     Checkout Book\n"
				+ "2     Find Books by Title\n"
				+ "3     Display Books by Type\n"
				+ "4     Produce Random Book List\n"
				+ "5     Save & Exit\n"
				+ "\n"
				+ "Enter option: ");
	}

	
	/**
	 * Gets the user's choice.
	 * 
	 * @return User's choice.
	 */
	public static int userChoice() {
		Scanner input = new Scanner(System.in);
		int userOption = 0;
		userOption = input.nextInt();
		
		return userOption;
	}
	
	/**
	 * User enters the ISBN of a book they want and if it is present then
	 * the book is checked out and displayed for the user. It's availability gets
	 * decreased by one if the ISBN is valid, if not, then tells the user there is an error.
	 * 
	 * @param bookLibrary Book library to loop through.
	 */
	public static void checkBook(ArrayList<Book> bookLibrary) {
		// Create local library variable to work with main library
		ArrayList<Book> library = bookLibrary;
		
		// Create Scanner object to get user input
		Scanner userInput = new Scanner(System.in);
		
		// Initial value assuming that book doesn't exist, used to exit loop.
		boolean contains = false;
		
		// Prompt user for ISBN
		System.out.print("Enter ISBN of book: ");
		
		// Collect ISBN from user's input.
		long isbn = userInput.nextLong();
		
		// Loop through library to see if ISBN matches any of the books.
		for (Book currentBook: library) {
			// If ISBN matches a book, decrement it's availability by one and display the book and transaction.
			if (currentBook.getIsbn() == isbn) {
				currentBook.setAvailable(currentBook.getAvailable() - 1);
				System.out.println("\nThe book " + "\"" + currentBook.getTitle() + "\" " + "has been checked out.");
				System.out.println("It can be located usng a call number: " + currentBook.getCallNumber());
				// Library contains the ISBN so break through loop
				contains = true;
			}
		}
		// Display error message saying the library doesn't own the book.
		if (!contains) {
			System.out.println("\n********\tERROR\t********");
			System.out.println("This book is not in our library.");
		}
	}
	
	/**
	 * Users can enter a title or part of a title of the book they wish to find
	 * and the method will search for that book by comparing the keywords with the
	 * books in the library.
	 * 
	 * @param bookLibrary Book library to loop through.
	 */
	public static void findBook(ArrayList<Book> bookLibrary) {
		// Create local library variable to work with main library.
		ArrayList<Book> library = bookLibrary;
		
		// Create Scanner object to get user input.
		Scanner userInput = new Scanner(System.in);
		
		// Initial value assuming that no books contain the user typed title.
		boolean contains = false;
		
		// Prompt user to enter title.
		System.out.print("Enter title to serach for: ");
		
		// Store user's input.
		String title = "";
		title = userInput.next();
		
		System.out.println("Matching books:");
		// Loop through library to see if title is in any of the books.
		for (Book currentBook: library) {
			// Display books with the user input contained in their titles.
			if (currentBook.getTitle().toLowerCase().contains(title.toLowerCase())) {
				System.out.println(currentBook.toString());
				contains = true;
			}
		}
		// If no books contain user input let the user know.
		if (!contains) {
			System.out.println("No books were found with such title.");
		}
	}
	
	/**
	 * Displays all books with the type and sub type the user specifies.
	 * 
	 * @param bookLibrary Book library to loop through.
	 */
	public static void displayType(ArrayList<Book> bookLibrary) {
		// Create local library variable to work with main library.
		ArrayList<Book> library = bookLibrary;
		
		// Create Scanner object to get user input.
		Scanner userInput = new Scanner(System.in);
		
		// Create user choice variables.
		int type = 0;
		char subtype = '0';
		
		// Display types of books.
		System.out.println("#     Type\n"
					   + "1     Children's Books\n"
					   + "2     Cookbooks\n"
					   + "3     Paperbacks\n"
					   + "4     Periodicals\n");
		
		// Prompt user to choose type of book.
		System.out.print("Enter type of book: ");
		
		// Store user's selected type.
		type = userInput.nextInt();
		
		// Process user's option and display subtypes.
		switch(type) {
		case 1:
			System.out.print("Enter a format (P for Picture, E for Early Readers, or C for Chapter book): ");
			break;
		case 2:
			System.out.print("Enter a diet (D for Diabetic, V for Vegetarian, G for Gluten-free, I for International, or N for None): ");
			break;
		case 3:
			System.out.print("Enter a genre (A for Adventure, D for Drama, E for Education, C for Classic, F for Fantasy, or S for Science Fiction): ");
			break;
		case 4:
			System.out.print("Enter a frequency (D for Daily, W for Weekly, M for Monthly, B for Bi-monthly, or Q for Quarterly): ");
			break;
			
		}
		
		// Store user's sub type.
		subtype = userInput.next().charAt(0);
		
		// Display the matching books.
		System.out.println("\nMatching books:");
		
		for (Book currentBook: library) {
			switch(type) {
			case 1:
				// Look for children's books and the selected sub type.
				if (currentBook instanceof ChildrensBook) {
					if (((ChildrensBook) currentBook).getFormat() == subtype) {
						System.out.println(currentBook.toString());
					}
				}
				break;
			case 2:
				// Look for cookbooks and the selected sub type.
				if (currentBook instanceof Cookbook) {
					if (((Cookbook)currentBook).getDiet() == subtype) {
						System.out.println(currentBook.toString());
					}
				}
				break;
			case 3:
				// Look for paperbacks and the selected sub type.
				if (currentBook instanceof Paperback) {
					if (((Paperback)currentBook).getGenre() == subtype) {
						System.out.println(currentBook.toString());
					}
				}
				break;
			case 4:
				// Look for periodicals and the selected sub type.
				if (currentBook instanceof Periodical) {
					if (((Periodical)currentBook).getFrequency() == subtype) {
						System.out.println(currentBook.toString());
					}
				}
				break;
			}
		}
	}
	
	/**
	 * User selects how many random books they wish to see and they are provided with that many 
	 * amount of books.
	 * 
	 * @param bookLibrary Book library to loop through.
	 */
	public static void displayRandomList(ArrayList<Book> bookLibrary) {
		// Create local library variable to work with main library.
		ArrayList<Book> library = bookLibrary;
		
		// Create Scanner object to get user input.
		Scanner userInput = new Scanner(System.in);
		
		// Create a Random object to randomize the books the user will see.
		Random random= new Random();
		
		// Create variable to store how many books the user wishes to see.
		int numberOfBooks = 0;
		
		// Prompt the user for how many books they wish to see.
		System.out.print("Enter number of books: ");
		numberOfBooks = userInput.nextInt();
		
		
	
		// Display the amount of random books the user wishes to see
		System.out.println("\nRandom books: ");
		for (int i = 0; i < numberOfBooks; i++) {
			// Randomize the book
			System.out.println(library.get(random.nextInt(library.size())).toString());
		}
	}
	
	/**
	 * Saves any changes made to the book library and exits the program.
	 * 
	 * @param bookLibrary Book library to loop through.
	 */
	public static void saveAndExit(ArrayList<Book> bookLibrary) throws Exception {
		// Create local library variable to work with main library.
		ArrayList<Book> library = bookLibrary;
		
		// Create a File object from the books.txt
		File bookList = new File("res/books.txt");

		// Rewrite and update the book library
		PrintWriter bookWriter = new PrintWriter(bookList);
		// Loop through current list of books and add them to the books.txt in the proper format.
		for (Book currentBook: library) {
			if (currentBook instanceof ChildrensBook) {
				bookWriter.printf("%d;%s;%d;%d;%s;%s;%c\n", currentBook.getIsbn(), currentBook.getCallNumber(),
						currentBook.getAvailable(), currentBook.getTotal(), currentBook.getTitle(),
						((ChildrensBook) currentBook).getAuthors(), ((ChildrensBook) currentBook).getFormat());
			}
			if (currentBook instanceof Cookbook) {
				bookWriter.printf("%d;%s;%d;%d;%s;%s;%c\n", currentBook.getIsbn(), currentBook.getCallNumber(),
						currentBook.getAvailable(), currentBook.getTotal(), currentBook.getTitle(),
						((Cookbook) currentBook).getPublisher(), ((Cookbook) currentBook).getDiet());
				
			}
			if (currentBook instanceof Paperback) {
				bookWriter.printf("%d;%s;%d;%d;%s;%s;%d;%c\n", currentBook.getIsbn(), currentBook.getCallNumber(),
						currentBook.getAvailable(), currentBook.getTotal(), currentBook.getTitle(),
						((Paperback) currentBook).getAuthors(),((Paperback) currentBook).getYear(),
						((Paperback) currentBook).getGenre());
				
			}
			if (currentBook instanceof Periodical) {
				bookWriter.printf("%d;%s;%d;%d;%s;%c\n", currentBook.getIsbn(), currentBook.getCallNumber(),
						currentBook.getAvailable(), currentBook.getTotal(), currentBook.getTitle(),
						((Periodical) currentBook).getFrequency());
				
			}
		}
		// Close PrintWriter object
		bookWriter.close();	
		
		// Display a message thanking the user.
		System.out.print("Your progress has been saved. Thank you have a good one.");
	}
}
