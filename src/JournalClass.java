import java.util.Arrays;

public class JournalClass {
	
	private   int id;
	private   String author;
	private   String journal;
	private   String title;
	private   String year;
	private   String volume;
	private   String number;
	private   String pages;
	private   String keyword;
	private   String doi;
	private   String ISSN;
	private   String month;
	

	public String toIEEEString() {
		return author.replace('}',' ').replace('{',' ') 
				+ ".\"" + title.replace('}',' ').replace('{',' ') +"\","
				+ journal.replace('}',' ').replace('{',' ')
				+ ", vol." + volume.replace('}',' ').replace('{',' ') 
				+ ", no." + number.replace('}',' ').replace('{',' ') 
				+ ", p." + pages.replace('}',' ').replace('{',' ')
				+ "," + month.replace('}',' ').replace('{',' ') 
				+ year.replace('}',' ').replace('{',' ') + ".";
	}
	
	public String toACMString() {
		return author.replace('}',' ').replace('{',' ') + "," 
				+ journal.replace('}',' ').replace('{',' ')
				+ title.replace('}',' ').replace('{',' ')+"." 
				+ ". "
				+ volume.replace('}',' ').replace('{',' ')+ ","  
				+ number.replace('}',' ').replace('{',' ')
				+ "(" + year.replace('}',' ').replace('{',' ')
				+ "), " + pages.replace('}',' ').replace('{',' ') + "." 
				+ "DOI:https://doi.org/" + doi.replace('}',' ').replace('{',' ');
	}
	
	public String toNJString() {
		return author.replace('}',' ').replace('{',' ') + "," 
				+ journal.replace('}',' ').replace('{',' ')
				+ title.replace('}',' ').replace('{',' ')+"." 
				+ ". "
				+ volume.replace('}',' ').replace('{',' ')+ ","  
				+ pages.replace('}',' ').replace('{',' ') 
				+ "(" + year.replace('}',' ').replace('{',' ') + ").";
	}


	public JournalClass() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getJournal() {
		return journal;
	}


	public void setJournal(String journal) {
		this.journal = journal;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getVolume() {
		return volume;
	}


	public void setVolume(String volume) {
		this.volume = volume;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getPages() {
		return pages;
	}


	public void setPages(String pages) {
		this.pages = pages;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public String getDoi() {
		return doi;
	}


	public void setDoi(String doi) {
		this.doi = doi;
	}


	public String getISSN() {
		return ISSN;
	}


	public void setISSN(String iSSN) {
		ISSN = iSSN;
	}


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}

}
