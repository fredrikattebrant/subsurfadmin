package se.subsurfers.utils.google.spreadsheets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import com.google.gdata.client.Service;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;

public class AccessSpreadsheetTest {
	
	private static final String CLIENT_ID = "32164137343.apps.googleusercontent.com";
	private static final String CLIENT_SECRET = "BMJ8iOwiG-2iiVpgqh69nvTt";

	public static void main(String[] args) {
		try {
			URL metafeedUrl = new URL(
					"https://spreadsheets.google.com/feeds/spreadsheets/private/full");
			Service service = new SpreadsheetService("subsurfers-members");
			String user = readInput("User: ");
			String pass = readInput("Password: ");
			
			String authorizedUrl = "";
			System.out.println("User info: " + metafeedUrl.getUserInfo());
			SpreadsheetFeed feed = service.getFeed(metafeedUrl,
					SpreadsheetFeed.class);
			List<SpreadsheetEntry> spreadsheets = feed.getEntries();
			System.out.println("About to loop");
			for (int i = 0; i < spreadsheets.size(); i++) {
				SpreadsheetEntry entry = spreadsheets.get(i);
				System.out.println("\t" + entry.getTitle().getPlainText());
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Stop! Caught exception: " + e);
			e.printStackTrace();
		}
	}
	
	private static String readInput(String prompt) {
		System.out.print(prompt);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
		int c;
		try {
			while ((c = br.read()) != -1 &&  c != Character.LETTER_NUMBER) {
				s = s + c;
			}
		} catch (IOException e) {
			return "";
		}
		return s;
	}
}
