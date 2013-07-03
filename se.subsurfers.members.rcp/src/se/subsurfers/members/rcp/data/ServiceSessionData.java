package se.subsurfers.members.rcp.data;

import javax.inject.Singleton;

import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.jface.viewers.TableViewer;

import se.subsurfers.members.rcp.parts.MembersContentProvider;

import com.google.gdata.client.spreadsheet.SpreadsheetService;

@Creatable @Singleton
public class ServiceSessionData {

	public static String SERVICE_VERSION = "1";
	public static String SERVICE_NAME = "se.subsurfers.members." + SERVICE_VERSION;
	public static String SHEET_NAME = "GWT-Stockholm Sub Surfers Medlemsregister";
	
	private SpreadsheetService spreadsheetService;
	
	private String userName;
	private String password;
	private boolean saveCredentials = false;
	
	// kludge
	private TableViewer viewer;
	private MembersContentProvider contentProvider;
	
	public ServiceSessionData() {
		// need this for singleton?
		if (spreadsheetService == null) {
			spreadsheetService = new SpreadsheetService(SERVICE_NAME);
		}
	}
	
	public SpreadsheetService getSpreadsheetService() {
		return spreadsheetService;
	}
	
	public void setSpreadsheetService(SpreadsheetService spreadsheetService) {
		this.spreadsheetService = spreadsheetService;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setSaveCredentials(boolean saveCredentials) {
		this.saveCredentials = saveCredentials;
	}
	
	public boolean isSaveCredentials() {
		return saveCredentials;
	}
	
	public void setViewer(TableViewer viewer) {
		this.viewer = viewer;
	}
	
	public TableViewer getViewer() {
		return viewer;
	}

	public void setContentProvider(MembersContentProvider contentProvider) {
		this.contentProvider = contentProvider;
	}
	
	public MembersContentProvider getContentProvider() {
		return contentProvider;
	}
}
