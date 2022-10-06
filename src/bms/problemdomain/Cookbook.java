package bms.problemdomain;

/**
 * Child class of Book that includes extra features such as publisher and diet.
 * 
 * @author Roman Kapitoulski
 * @author Nicholas Costa
 * @author Joshua Lokhorst
 * @author Jinki Lee
 * @version September 28, 2022
 */
public class Cookbook extends Book{
	private String publisher;
	private char diet;
	private String dietName;
	
	/**
	 * Constructor for initializing book from books.txt. 
	 * 
	 * @param isbn Book's ISBN.
	 * @param callNumber Book's call number.
	 * @param available Number of books available.
	 * @param total Number of books total.
	 * @param title Title of book.
	 * @param publisher Publisher of book.
	 * @param diet Diet type of book.
	 */
	public Cookbook(long isbn, String callNumber, int available, int total, String title, String publisher, char diet) {
		super(isbn, callNumber, available, total, title);
		setPublisher(publisher);
		setDiet(diet);
		
		switch(diet) {
		case 'D':
			setDietName("Diabetic");
			break;
		case 'V':
			setDietName("Vegetarian");
			break;
		case 'G':
			setDietName("Gluten-free");
			break;
		case 'I':
			setDietName("International");
			break;
		case 'N':
			setDietName("None");
			break;
		}
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public char getDiet() {
		return diet;
	}

	public void setDiet(char diet) {
		this.diet = diet;
	}
	
	public String getDietName() {
		return dietName;
	}

	public void setDietName(String dietName) {
		this.dietName = dietName;
	}
	
	/**
	 * Overridden Book toString method to add extra features.
	 */
	@Override
	public String toString() {
		String extraString = String.format("Publisher:        %s\n"
										 + "Diet:             %s\n", getPublisher(), getDietName());
		return super.toString() + extraString; 
	}

}
