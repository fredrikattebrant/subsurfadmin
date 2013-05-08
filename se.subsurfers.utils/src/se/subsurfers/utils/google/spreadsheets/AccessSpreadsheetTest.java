package se.subsurfers.utils.google.spreadsheets;

import java.net.URL;
import java.util.List;

import com.google.gdata.client.Service;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;

public class AccessSpreadsheetTest {

	public static void main(String[] args) {
		try {
			URL metafeedUrl = new URL(
					"https://spreadsheets.google.com/feeds/spreadsheets/private/full");
			Service service = new SpreadsheetService("subsurfers-members");
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
			System.err.println("Exception: " + e);
		}
	}
}
