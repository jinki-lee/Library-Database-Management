package bms.problemdomain;

/**
 * Child class of Book that includes extra features such as authors, and format.
 * 
 * @author Roman Kapitoulski
 * @author Nicholas Costa
 * @author Joshua Lokhorst
 * @author Jinki Lee
 * @version September 28, 2022
 */
public class ChildrensBook extends Book {
	private String authors;
	private char format;
	private String formatName;
	
	/**
	 * Constructor for initializing book from books.txt. 
	 * 
	 * @param isbn Book's ISBN.
	 * @param callNumber Book's call number.
	 * @param available Number of books available.
	 * @param total Number of books total.
	 * @param title Title of book.
	 * @param authors Authors of book.
	 * @param format Format of book.
	 */
	public ChildrensBook(long isbn, String callNumber, int available, int total, String title, String authors, char format){
		super(isbn, callNumber, available, total, title);
		setAuthors(authors);
		setFormat(format);
		
		switch(getFormat()) {
			case 'P': 
				setFormatName("Picture Book");
				break;
			case 'E': 
				setFormatName("Early Readers");
				break;
			case 'C': 
				setFormatName("Chapter");
				break;
		}
	}
	
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public char getFormat() {
		return format;
	}
	public void setFormat(char format) {
		this.format = format;
	}
	
	public String getFormatName() {
		return formatName;
	}
	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}
	
	/**
	 * Overridden Book toString method to add extra features.
	 */
	@Override
	public String toString() {
		String extraString = String.format("Authors:          %s\n"
										 + "Format:           %s\n", getAuthors(), getFormatName());
		return super.toString() + extraString; 
	}
	
}
