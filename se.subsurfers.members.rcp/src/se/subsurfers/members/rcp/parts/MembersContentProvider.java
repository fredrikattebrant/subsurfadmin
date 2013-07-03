package se.subsurfers.members.rcp.parts;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IContentProvider;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.util.ServiceException;

public class MembersContentProvider extends ArrayContentProvider implements
		IContentProvider {

	URL listFeedUrl; 
	SpreadsheetService service;
	
	public void setModel(URL listFeedUrl, SpreadsheetService service) {
		this.listFeedUrl = listFeedUrl;
		this.service = service;
	}
	
	@Override
	public Object[] getElements(Object inputElement) {
		try {
			ListFeed feed = service.getFeed(listFeedUrl, ListFeed.class);
			List<ListEntry> entries = feed.getEntries();
			
			// remove the first 4 rows
			System.out.println("ROWS: " + entries.size());
			for (int i=0; i<2; i++) {
				entries.remove(i);
			}
			System.out.println("ROWS: " + entries.size());
			
			return entries.toArray();
		} catch (IOException | ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
