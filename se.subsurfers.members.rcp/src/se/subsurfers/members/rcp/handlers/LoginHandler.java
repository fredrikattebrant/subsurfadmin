package se.subsurfers.members.rcp.handlers;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import se.subsurfers.members.rcp.data.ServiceSessionData;
import se.subsurfers.members.rcp.dialogs.LoginDialog;

import com.google.gdata.client.spreadsheet.FeedURLFactory;
import com.google.gdata.data.BaseEntry;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

public class LoginHandler {

	@Inject
	IEclipseContext context;

	@Inject
	private ServiceSessionData userData;

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell) {
		System.out.println("Shell: " + shell);
		System.out.println("Context: " + context);
		// userData = ContextInjectionFactory.make(ServiceSessionData.class,
		// context);
		// ContextInjectionFactory.inject(ServiceSessionData.class, context);
		if (userData != null) {
			System.out.println("OLD " + userData.getUserName());
		}
		//userData = new ServiceSessionData();
		System.out.println("Login - execute. userData.user -> "
				+ (userData != null ? userData.getUserName() : "null"));
		LoginDialog dialog = new LoginDialog(shell, userData);
		dialog.create();
		if (dialog.open() == Window.OK) {
			userData.setUserName(dialog.getUser());
			userData.setPassword(dialog.getPassword());
			System.out.println("Login: " + userData.getUserName()
					+ ", password: " + "xxxx");
			try {
				login();
			} catch (AuthenticationException e) {
				IStatus status = new Status(IStatus.ERROR, "members",
						e.getMessage());
				String message = e.getMessage();
				ErrorDialog.openError(shell, "Login failed", message, status);
				return;
			}

			try {
				URL listFeedUrl = loadSheet();
				System.out.println("listFeedURL: " + listFeedUrl);
				userData.getContentProvider().setModel(listFeedUrl,
						userData.getSpreadsheetService());
				userData.getViewer().setInput(listFeedUrl);
				//listAllEntries(listFeedUrl);
			} catch (IOException | ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@CanExecute
	public boolean canExecute() {
		System.out.println("Login - can execute");
		return true;
	}

	public ServiceSessionData getUserData() {
		return userData;
	}

	public void login() throws AuthenticationException {
		userData.getSpreadsheetService().setUserCredentials(
				userData.getUserName(), userData.getPassword());
	}

	public URL loadSheet() throws IOException, ServiceException {
		URL feedUrl = FeedURLFactory.getDefault().getSpreadsheetsFeedUrl();
		SpreadsheetFeed feed = userData.getSpreadsheetService().getFeed(
				feedUrl, SpreadsheetFeed.class);
		List<SpreadsheetEntry> spreadsheets = feed.getEntries();

		SpreadsheetEntry entry = null;
		for (BaseEntry baseEntry : spreadsheets) {
			if (baseEntry.getTitle().getPlainText()
					.equals(ServiceSessionData.SHEET_NAME)) {
				System.out.println("Entry: "
						+ baseEntry.getTitle().getPlainText());
				entry = (SpreadsheetEntry) baseEntry;
				break;
			}
		}

		WorksheetEntry worksheet;
		if (entry != null) {
			// always use first sheet
			worksheet = entry.getWorksheets().get(0);
		} else {
			// handle error
			throw new ServiceException("No matching sheet found");
		}

		URL listFeedUrl = worksheet.getListFeedUrl();
		return listFeedUrl;
	}

	public void listAllEntries(URL listFeedUrl) {
		try {
			ListFeed feed = userData.getSpreadsheetService().getFeed(
					listFeedUrl, ListFeed.class);
			System.out.println("ALL ENTRIES:");
			for (ListEntry entry : feed.getEntries()) {
				String id = entry.getId().substring(
						entry.getId().lastIndexOf('/') + 1);
				String title = entry.getTitle().getPlainText();
				System.out.println("ID:  " + id);
				System.out.println("Tag: " + title);
				for (String tag : entry.getCustomElements().getTags()) {
					System.out.println("Cell: "
							+ entry.getCustomElements().getValue(tag));
				}

			}
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}