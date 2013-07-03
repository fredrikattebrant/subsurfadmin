package se.subsurfers.members.rcp.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import se.subsurfers.members.rcp.data.ServiceSessionData;
import org.eclipse.swt.widgets.Button;

public class LoginDialog extends Dialog {
	private Text userText;
	private Text passwordText;
	
	private ServiceSessionData userData;
	private Button saveCredentialsCheckButton;

	/**
	 * Create the dialog.
	 * @param parentShell
	 * @param userData 
	 */
	public LoginDialog(Shell parentShell, ServiceSessionData userData) {
		super(parentShell);
		this.userData = userData;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.numColumns = 2;
		
		Label lblLoginToGoogle = new Label(container, SWT.NONE);
		GridData gd_lblLoginToGoogle = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_lblLoginToGoogle.widthHint = 170;
		lblLoginToGoogle.setLayoutData(gd_lblLoginToGoogle);
		lblLoginToGoogle.setText("Login to google");
		new Label(container, SWT.NONE);
		
		Label lblUser = new Label(container, SWT.NONE);
		lblUser.setText("User:");
		
		userText = new Text(container, SWT.BORDER);
		userText.setToolTipText("Enter your google username");
		userText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		if (userData.isSaveCredentials() && userData.getUserName() != null) {
			userText.setText(userData.getUserName());
		}
		
		Label lblPassword = new Label(container, SWT.NONE);
		lblPassword.setText("Password:");
		
		passwordText = new Text(container, SWT.BORDER | SWT.PASSWORD);
		passwordText.setToolTipText("Enter your google password");
		passwordText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		if (userData.isSaveCredentials() && userData.getPassword() != null) {
			passwordText.setText(userData.getPassword());
		}

		saveCredentialsCheckButton = new Button(container, SWT.CHECK);
		saveCredentialsCheckButton.setText("Save username and password");
		saveCredentialsCheckButton.setSelection(userData.isSaveCredentials());
		new Label(container, SWT.NONE);
		
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
	
	public String getUser() {
		return userData.getUserName();
	}
	
	public String getPassword() {
		return userData.getPassword();
	}
	
	@Override
	protected void okPressed() {
		userData.setUserName(userText.getText().trim());
		userData.setPassword(passwordText.getText().trim());
		userData.setSaveCredentials(saveCredentialsCheckButton.getSelection());
		super.okPressed();
	}

}
