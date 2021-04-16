import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BibCreator {

	final static String ARTICLE_KEY = "@ARTICLE";
	final static String AUTHOR_KEY = "author";
	final static String TITLE_KEY = "title";
	final static String YEAR_KEY = "year";
	final static String VOLUME_KEY = "volume";
	final static String NUMBER_KEY = "number";
	final static String PAGES_KEY = "pages";
	final static String KEYWORD_KEY = "keywords";
	final static String DOI_KEY = "doi";
	final static String ISSN_KEY = "ISSN";
	final static String MONTH_KEY = "month";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub


		readFromFile();

	}



	public static void readFile2() {

		for (int i = 1; i < 11; i++) {

			File fileId = new File("Latex" + i + ".bib");

			try {
				Scanner data = new Scanner(fileId);
				int lineNumber = 1;
				while (data.hasNextLine()) {

					String oneLine = data.nextLine();
					System.out.println("Line " + lineNumber + " :" + oneLine);
					lineNumber++;

				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				return;
			}
		}
	}

	public static void readFromFile() {

		for (int i = 1; i < 11; i++) {
			File fileId = new File("Latex" + i + ".bib");
			try {
				Scanner data = new Scanner(fileId);
				ArrayList<JournalClass> journals = new ArrayList<JournalClass>();
				JournalClass journal = new JournalClass();

				while (data.hasNextLine()) {
					if (!data.equals("")) {
						String oneLine = data.nextLine();
						StringTokenizer st = new StringTokenizer(oneLine, ",");
						String value = st.nextToken();
						String[] line = value.split("=");
						if (value.equals("}")) {
							writeTofiles(i,journal);
							journals.add(journal);
							journal = new JournalClass();
						}else {
							journal = parseJournal(line, journal);
						}
					}
				}


			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static JournalClass parseJournal(String[] data, JournalClass journal) {

		if (data.length > 1) {
			switch (data[0]) {
			case AUTHOR_KEY: {
				journal.setAuthor(data[1]);
				break;
			}
			case TITLE_KEY: {
				journal.setTitle(data[1]);
				break;
			}
			case YEAR_KEY: {
				journal.setYear(data[1]);
				break;
			}
			case VOLUME_KEY: {
				journal.setVolume(data[1]);
				break;
			}
			case NUMBER_KEY: {
				journal.setNumber(data[1]);
				break;
			}
			case PAGES_KEY: {
				journal.setPages(data[1]);
				break;
			}
			case KEYWORD_KEY: {
				journal.setKeyword(data[1]);
				break;
			}
			case DOI_KEY: {
				journal.setDoi(data[1]);
				break;
			}
			case ISSN_KEY: {
				journal.setISSN(data[1]);
				break;
			}
			case MONTH_KEY: {
				journal.setMonth(data[1]);
				break;
			}
			}
		}
		return journal;
	}
	
	public static void writeTofiles(int i,JournalClass journal) {
		BufferedWriter outputIEEE = null;
		BufferedWriter outputACM = null;
		BufferedWriter outputNJ = null;

		try {
			File fileIEEE = new File("IEEE"+i+".json");
			File fileACM = new File("ACM"+i+".json");
			File fileNJ = new File("NJ"+i+".json");

			outputIEEE = new BufferedWriter(new FileWriter(fileIEEE));
			outputACM = new BufferedWriter(new FileWriter(fileACM));
			outputNJ = new BufferedWriter(new FileWriter(fileNJ));

			outputIEEE.write(journal.toIEEEString());
			outputACM.write(journal.toACMString());
			outputNJ.write(journal.toNJString());

		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(outputIEEE != null) {
				try {
					outputIEEE.close();
					outputACM.close();
					outputACM.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
