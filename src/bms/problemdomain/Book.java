package bms.problemdomain;

/**
 * Parent class of all books. Has general attributes all books share such as:
 * ISBN, call number, availability number, total number and title.
 * 
 * @author Roman Kapitoulski
 * @author Nicholas Costa
 * @author Joshua Lokhorst
 * @author Jinki Lee
 * @version September 28, 2022
 */
public class Book {
	private long isbn;
	private String callNumber;
	private int available;
	private int total;
	private String title;
	
	/**
	 * Protected Book method to prevent construction but still allow availability to child classes for modification
	 */
	protected Book() {}
	
	/**
	 * Constructor to be overridden by children classes.
	 * 
	 * @param isbn Book's ISBN.
	 * @param callNumber Book's call number.
	 * @param available Number of books available.
	 * @param total Number of books total.
	 * @param title Title of book.
	 */
	protected Book(long isbn, String callNumber, int available, int total, String title) {
		setIsbn(isbn);
		setCallNumber(callNumber);
		setAvailable(available);
		setTotal(total);
		setTitle(title);
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getCallNumber() {
		return callNumber;
	}

	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Overridden toString method for better readability.
	 */
	@Override
	public String toString() {
		String bookString = String.format("ISBN:             %d\n"
										+ "Call Number:      %s\n"
										+ "Available:        %d\n"
										+ "Total:            %d\n"
										+ "Title:            %s\n", getIsbn(), getCallNumber(), getAvailable(), getTotal(), getTitle());
		return bookString;
	}
	
}
