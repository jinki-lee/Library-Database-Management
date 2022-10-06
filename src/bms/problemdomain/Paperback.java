package bms.problemdomain;

/**
 * Child class of Book that includes extra features such as authors, genre, and year.
 * 
 * @author Roman Kapitoulski
 * @author Nicholas Costa
 * @author Joshua Lokhorst
 * @author Jinki Lee
 * @version September 28, 2022
 */
public class Paperback extends Book{
	private String authors;
	private short year;
	private char genre;
	private String genreName;
	
	/**
	 * Constructor for initializing book from books.txt. 
	 * 
	 * @param isbn Book's ISBN.
	 * @param callNumber Book's call number.
	 * @param available Number of books available.
	 * @param total Number of books total.
	 * @param title Title of book.
	 * @param authors Authors of book.
	 * @param year Year book was published.
	 * @param genre Genre of book.
	 */
	public Paperback(long isbn, String callNumber, int available, int total, String title, String authors, short year, char genre) {
		super(isbn, callNumber, available, total, title);
		setAuthors(authors);
		setYear(year);
		setGenre(genre);
		
		switch(genre) {
		case 'A':
			setGenreName("Adventure");
			break;
		case 'D':
			setGenreName("Drama");
			break;
		case 'E':
			setGenreName("Education");
			break;
		case 'C':
			setGenreName("Classic");
			break;
		case 'F':
			setGenreName("Fantasy");
			break;
		case 'S':
			setGenreName("Science Fiction");
			break;
		}
	}
	
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public short getYear() {
		return year;
	}
	public void setYear(short year) {
		this.year = year;
	}
	public char getGenre() {
		return genre;
	}
	public void setGenre(char genre) {
		this.genre = genre;
	}
	
	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	
	/**
	 * Overridden Book toString method to add extra features.
	 */
	@Override
	public String toString() {
		String extraString = String.format("Authors:          %s\n"
										 + "Year:             %d\n"
										 + "Genre:            %s\n", getAuthors(), getYear(), getGenreName());
		return super.toString() + extraString; 
	}

}
