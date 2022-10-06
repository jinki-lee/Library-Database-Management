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
public class Periodical extends Book{
	private char frequency;
	private String frequencyName;
	
	/**
	 * Constructor for initializing book from books.txt. 
	 * 
	 * @param isbn Book's ISBN.
	 * @param callNumber Book's call number.
	 * @param available Number of books available.
	 * @param total Number of books total.
	 * @param title Title of book.
	 * @param frequency Frequency of book.
	 */
	public Periodical(long isbn, String callNumber, int available, int total, String title, char frequency) {
		super(isbn, callNumber, available, total, title);
		setFrequency(frequency);
		
		switch(getFrequency()) {
			case 'D': 
				setFrequencyName("Daily");
				break;
			case 'W': 
				setFrequencyName("Weekly");
				break;
			case 'M': 
				setFrequencyName("Monthly");
				break;
			case 'B': 
				setFrequencyName("Bi-monthly");
				break;
			case 'Q': 
				setFrequencyName("Quarterly");
				break;
		}
	}
	
	public char getFrequency() {
		return frequency;
	}

	public void setFrequency(char frequency) {
		this.frequency = frequency;
	}
	
	public String getFrequencyName() {
		return frequencyName;
	}

	public void setFrequencyName(String frequencyName) {
		this.frequencyName = frequencyName;
	}
	
	/**
	 * Overridden Book toString method to add extra features.
	 */
	@Override
	public String toString() {
		String extraString = String.format("Frequency:        %s\n", getFrequencyName());
		return super.toString() + extraString; 
	}
}
