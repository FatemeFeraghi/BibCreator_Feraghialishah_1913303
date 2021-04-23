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
	final static String JOURNAL_KEY = "journal";
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

        System.out.println("Welcome to BibCreator!\n");
//		readFile2();
		readFromFile();
		displayFile();

	}
	
	private static void displayFile() throws IOException {
		// TODO Auto-generated method stub
    		int i = 2;
            Scanner kb = new Scanner(System.in);
    		while (i > 0) {
    		   	BufferedReader br = null;
                String line = null;
            	System.out.print("Please enter the name of a file you would like to review: ");
                String FileReview = kb.nextLine();
                try {
                	File fileId = new File(FileReview);
    				if (!fileId.exists()) {
    					throw new FileInvalidException("Could not open input file. File does not exist; possibly it could not be created!");
    				}
                    br = new BufferedReader(new FileReader(FileReview));
                    while((line = br.readLine()) != null) {
                        System.out.println(line + '\n');
                    }
        			br.close();
    				System.out.println("Thank you for using BibCreator");
                    kb.close();
                    System.exit(0);
                }
                catch (FileInvalidException e) {
        			// TODO: handle exception
        			System.out.println(e.getMessage());
        			i--;
        			if (i <= 0) {
        				System.out.println("Thank you for using BibCreator");
        				kb.close();
            			System.exit(0);
        				}
        			}

    		}     
	}

	public static void readFromFile() {

		int count = 0;
		try {
		
		for (int i = 1; i < 11; i++) {
			File fileId = new File("Latex" + i + ".bib");
			
			if (!fileId.exists()) {
				throw new FileInvalidException("Could not open input file Latex"+i+".bib for reading. \n\nPlease check if file exists! Program will terminate after closing any opened files.\n");
			}
			
			try {
				Scanner data = new Scanner(fileId);
				
				String error = checkEmptyField(data);
				if (error != null) {
					count++;
					throw new FileInvalidException("Error: Detected Empty Field!\n============================\n\n"
							+ "Problem detected with file Latex" + i + ".bib\n" + "File is Invalid: Field \"" 
							+ error + "\" is Empty. Processing stopped at this point. Other empty fields may be present as well!\n");
				}
				
				JournalClass journal = new JournalClass();
				ArrayList<JournalClass> journals = new ArrayList<JournalClass>();
				
				while (data.hasNextLine()) {
					String oneLine = data.nextLine().trim();
					StringTokenizer st = new StringTokenizer(oneLine, ",");
					if (st.hasMoreTokens()) {
						String value = st.nextToken();
						String[] line = value.split("=");
						if (value.equals("}")) {
							journals.add(journal);
							journal = new JournalClass();
						} else {
							journal = parseJournal(line, journal);
						}
					}
				data.close();
				}
				writeTofiles(i, journals);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	} catch (FileInvalidException e) {
			// TODO: handle exception
		
			System.out.println(e.getMessage());
			}
		System.out.println("A total of "+ count + " files were invalid, and could not be processed. All other " + (10 - count) + " \"Valid\" files have been created.\n");
	}
		

	private static String checkEmptyField(Scanner data) {
		// TODO Auto-generated method stub
			while (data.hasNextLine()) {
		        String line = data.nextLine();
		        if (line.contains("{}")) { 
					StringTokenizer st = new StringTokenizer(line, "=");
		            return st.nextToken();
		        }
		    }
			return null;
	}
	

	public static JournalClass parseJournal(String[] data, JournalClass journal) {

		if (data.length > 1) {
			switch (data[0]) {
			case AUTHOR_KEY: {
				journal.setAuthor(data[1].replace("{", "").replace("}", ""));
				break;
			}
			case JOURNAL_KEY: {
				journal.setJournal(data[1].replace("{", "").replace("}", ""));
				break;
			}
			case TITLE_KEY: {
				journal.setTitle(data[1].replace("{", "").replace("}", ""));
				break;
			}
			case YEAR_KEY: {
				journal.setYear(data[1].replace("{", "").replace("}", ""));
				break;
			}
			case VOLUME_KEY: {
				journal.setVolume(data[1].replace("{", "").replace("}", ""));
				break;
			}
			case NUMBER_KEY: {
				journal.setNumber(data[1].replace("{", "").replace("}", ""));
				break;
			}
			case PAGES_KEY: {
				journal.setPages(data[1].replace("{", "").replace("}", ""));
				break;
			}
			case KEYWORD_KEY: {
				journal.setKeyword(data[1].replace("{", "").replace("}", ""));
				break;
			}
			case DOI_KEY: {
				journal.setDoi(data[1].replace("{", "").replace("}", ""));
				break;
			}
			case ISSN_KEY: {
				journal.setISSN(data[1].replace("{", "").replace("}", ""));
				break;
			}
			case MONTH_KEY: {
				journal.setMonth(data[1].replace("{", "").replace("}", ""));
				break;
			}
			}
		}
		return journal;
	}

	public static void writeTofiles(int i, ArrayList<JournalClass> journals) {
		BufferedWriter outputIEEE = null;
		BufferedWriter outputACM = null;
		BufferedWriter outputNJ = null;

		try {
			File fileIEEE = new File("IEEE" + i + ".json");
			File fileACM = new File("ACM" + i + ".json");
			File fileNJ = new File("NJ" + i + ".json");

			outputIEEE = new BufferedWriter(new FileWriter(fileIEEE));
			outputACM = new BufferedWriter(new FileWriter(fileACM));
			outputNJ = new BufferedWriter(new FileWriter(fileNJ));
			String ieee = "";
			String acm = "";
			String nj = "";

			for (int j = 0; j < journals.size(); j++) {
				ieee += journals.get(j).toIEEEString();
				acm += journals.get(j).toACMString();
				nj += journals.get(j).toNJString();
			}

			outputIEEE.write(ieee);
			outputACM.write(acm);
			outputNJ.write(nj);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outputIEEE != null) {
				try {
					outputIEEE.close();
					outputACM.close();
					outputNJ.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
