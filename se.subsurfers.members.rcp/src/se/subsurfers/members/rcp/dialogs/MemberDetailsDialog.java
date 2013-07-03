package se.subsurfers.members.rcp.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.DateTime;

import se.subsurfers.members.rcp.data.ServiceSessionData;
import se.subsurfers.utils.SubsurfMember;

public class MemberDetailsDialog extends Dialog {
	
	private Text membershipNumberTxt;
	private Text firstNameTxt;
	private Text lastnameTxt;
	private Text emailTxt;
	private Text mobileTxt;
	private DateTime registrationDateTime;
	private DateTime paidDateTime;
	private DateTime validUntilDateTime;
	
	private ServiceSessionData sessionData;
	private SubsurfMember memberData;

	/**
	 * Create the dialog.
	 * @param parentShell
	 * @param sessionData 
	 * @param memberData 
	 */
	public MemberDetailsDialog(Shell parentShell, ServiceSessionData sessionData, SubsurfMember memberData) {
		super(parentShell);
		this.sessionData = sessionData;
		this.memberData = memberData;
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
		
		Label lblMembershipNumber = new Label(container, SWT.NONE);
		lblMembershipNumber.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMembershipNumber.setText("Medlemsnummer");
		
		membershipNumberTxt = new Text(container, SWT.BORDER | SWT.READ_ONLY);
		membershipNumberTxt.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label lblFirstname = new Label(container, SWT.NONE);
		lblFirstname.setText("Förnamn");
		
		firstNameTxt = new Text(container, SWT.BORDER);
		firstNameTxt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblLastname = new Label(container, SWT.NONE);
		lblLastname.setText("Efternamn");
		
		lastnameTxt = new Text(container, SWT.BORDER);
		lastnameTxt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblEmail = new Label(container, SWT.NONE);
		lblEmail.setText("E-mail");
		
		emailTxt = new Text(container, SWT.BORDER);
		emailTxt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblMobil = new Label(container, SWT.NONE);
		lblMobil.setText("Mobil");
		
		mobileTxt = new Text(container, SWT.BORDER);
		mobileTxt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblRegistration = new Label(container, SWT.NONE);
		lblRegistration.setText("Registrerad");
		
		registrationDateTime = new DateTime(container, SWT.BORDER);
		
		Label lblPaid = new Label(container, SWT.NONE);
		lblPaid.setText("Betalat");
		
		paidDateTime = new DateTime(container, SWT.BORDER);
		
		Label lblValidUntil = new Label(container, SWT.NONE);
		lblValidUntil.setText("Giltigt till");
		
		validUntilDateTime = new DateTime(container, SWT.BORDER | SWT.LONG);
		
		

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
		return new Point(450, 438);
	}
	
	

}
