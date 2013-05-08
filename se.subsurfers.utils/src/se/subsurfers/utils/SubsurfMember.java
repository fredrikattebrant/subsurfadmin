package se.subsurfers.utils;

import java.util.Date;

public class SubsurfMember {
	private Long id;
	private int membershipNumber; // == id? Or is 'id' generated "via" JPA?
	private String firstname;
	private String lastname;
	private String registrationDate;
	private Date registrationValidUntil;
	private Date deRegistered;
	private int membershipFee;
	private boolean feePaid;
	private Date feePaidOnDate;
	private String mobilePhoneNo;
	private String sssEmail;
	private String otherEmail;
	private String streetAddress;
	private String postalNumber;
	private String city;
	private String fixedPhone;
	private String birthdate; // yyyy-mm-dd{-nnnn}
	private boolean genderIsMale;
	private boolean skateboarder;
	private boolean surfer;
	private boolean snowboarder;
	private boolean longboarder;
	private boolean supportingMember;
	private boolean longboardMember;
	private boolean ssaMember;
	private Date ssaVoteValidUntil;
	private boolean ensuredForCompetition;

	boolean skateboard_street;
	boolean skateboard_vert;
	boolean skateboard_backyardpools;
	boolean skateboard_miniramp_bowlrider;
	boolean skateboard_slalom;
	boolean skateboard_flatland_freestyle;
	boolean skateboard_longboard_cruise;
	boolean skateboard_downhill;
	boolean skateboard_highjump_longjump;
	boolean snowboard_freeride;
	boolean snowboard_pipe;
	boolean snowboard_bigjump;
	boolean snowboard_snowcross;
	boolean snowboard_alpine;
	boolean surfing_shortboard;
	boolean surfing_longboard;
	boolean surfing_bodyboard;
	boolean skimboard;
	boolean sandboard;
	boolean wakeboard;
	boolean wakeskate;
	boolean kiteboard;
	boolean windsurf;
	boolean hoverboard;

	private String membershipInfo; // as submitted by registration form
	private String boardsportInfo; // as submitted by registration form
	private String otherInfo;
	private String notes;
	private String extraInfo1;
	private String extraInfo2;
	private String extraInfo3;
	private String extraInfo4;
	private String extraInfo5;

	public SubsurfMember(
			String firstname, String lastname,
			String registrationDate, Date registrationValidUntil,
			Date deRegistered, int membershipFee, 
			String mobilePhoneNo,
			String sssEmail, String otherEmail, 
			String streetAddress, String postalNumber, String city, 
			String fixedPhone,
			String birthdate, 
			boolean genderIsMale, 
			boolean skateboarder,
			boolean surfer, 
			boolean snowboarder, 
			boolean longboarder,
			boolean supportingMember,
			boolean longboardMember,
			boolean ssaMember,			
			boolean skateboard_street,
			boolean skateboard_vert,
			boolean skateboard_backyardpools,
			boolean skateboard_miniramp_bowlrider,
			boolean skateboard_slalom,
			boolean skateboard_flatland_freestyle,
			boolean skateboard_longboard_cruise,
			boolean skateboard_downhill,
			boolean skateboard_highjump_longjump,
			boolean snowboard_freeride,
			boolean snowboard_pipe,
			boolean snowboard_bigjump,
			boolean snowboard_snowcross,
			boolean snowboard_alpine,
			boolean surfing_shortboard,
			boolean surfing_longboard,
			boolean surfing_bodyboard,
			boolean skimboard,
			boolean sandboard,
			boolean wakeboard,
			boolean wakeskate,
			boolean kiteboard,
			boolean windsurf,
			boolean hoverboard,

			
			String membershipInfo,
			String boardsportInfo) {
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.registrationDate = registrationDate;
		this.registrationValidUntil = registrationValidUntil;
		this.membershipFee = membershipFee;
		this.feePaid = false; // Note - always set by the cashier, never when
								// creating new member
		this.mobilePhoneNo = mobilePhoneNo;
		this.sssEmail = sssEmail;
		this.otherEmail = otherEmail;
		this.streetAddress = streetAddress;
		this.postalNumber = postalNumber;
		this.city = city;
		this.fixedPhone = fixedPhone;
		this.birthdate = birthdate;
		this.genderIsMale = genderIsMale;
		this.skateboarder = skateboarder;
		this.surfer = surfer;
		this.snowboarder = snowboarder;
		this.longboarder = longboarder;
		this.supportingMember = supportingMember;
		this.longboardMember = longboardMember;
		this.ssaMember = ssaMember;
		this.skateboard_street =  skateboard_street;
		this.skateboard_vert =  skateboard_vert;
		this.skateboard_backyardpools =  skateboard_backyardpools;
		this.skateboard_miniramp_bowlrider =  skateboard_miniramp_bowlrider;
		this.skateboard_slalom =  skateboard_slalom;
		this.skateboard_flatland_freestyle =  skateboard_flatland_freestyle;
		this.skateboard_longboard_cruise =  skateboard_longboard_cruise;
		this.skateboard_downhill =  skateboard_downhill;
		this.skateboard_highjump_longjump =  skateboard_highjump_longjump;
		this.snowboard_freeride =  snowboard_freeride;
		this.snowboard_pipe =  snowboard_pipe;
		this.snowboard_bigjump =  snowboard_bigjump;
		this.snowboard_snowcross =  snowboard_snowcross;
		this.snowboard_alpine =  snowboard_alpine;
		this.surfing_shortboard =  surfing_shortboard;
		this.surfing_longboard =  surfing_longboard;
		this.surfing_bodyboard =  surfing_bodyboard;
		this.skimboard =  skimboard;
		this.sandboard =  sandboard;
		this.wakeboard =  wakeboard;
		this.wakeskate =  wakeskate;
		this.kiteboard =  kiteboard;
		this.windsurf =  windsurf;
		this.hoverboard =  hoverboard;
		
		this.membershipInfo = membershipInfo;
		this.boardsportInfo = boardsportInfo;
	}

	public Long getId() {
		return this.id;
	}

	public String getFirstName() {
		return this.firstname;
	}

	public String getLastName() {
		return this.lastname;
	}

	public String getBirthdate() {
		return this.birthdate;
	}

	public String getEMail() {
		if (this.sssEmail != null && this.sssEmail != "") {
			return this.sssEmail;
		}
		return this.otherEmail;
	}

	public void setFeePaid(Date date) {
		this.feePaid = true;
		this.feePaidOnDate = date;
	}

	public boolean hasPaidFee() {
		return feePaid;
	}

	public boolean isSkateboarder() {
		return skateboarder;
	}

	public boolean isSurfer() {
		return surfer;
	}

	public boolean isSnowboarder() {
		return snowboarder;
	}

	public boolean isLongboarder() {
		return longboarder;
	}

	public boolean isSupportingMember() {
		return supportingMember;
	}

	public boolean isLongboardMember() {
		return longboardMember;
	}

	public boolean isSsaMember() {
		return ssaMember;
	}

	public void deRegister() {
		// mark member as deRegistred
		this.deRegistered = new Date();
	}

	public String toString() {
		return "First name: " + firstname + "\n" + "Last name : " + lastname
				+ "\n" + "E-mail    : " + otherEmail + "\n" + "Mobile    : "
				+ mobilePhoneNo;
	}

	public String toCSV() {
		// Comments list which column data goes into - need manual checking
		// when changing spreadsheet format:
		return "" + ", " + 											// A - blank for "Nr"
				firstname + ", "									// B
				+ lastname + ", "									// C
				+ "" + ", "											// D - "Vi unga"
				+ registrationDate + ", " 							// E
				+ "" + ", " 										// F
				+ "" + ", " 										// G
				+ membershipFee + ", " 								// H
				+ "" + ", " 										// I
				+ mobilePhoneNo + ", " 								// J
				+ "" + ", "											// K
				+ otherEmail + ", " 								// L
				+ streetAddress + ", " 								// M
				+ postalNumber + ", " 								// N
				+ city + ", " 										// O
				+ fixedPhone + ", "									// P
				+ birthdate + ", "									// Q
				+ "" + ", " // TODO - formula for age? 				// R
				+ (genderIsMale     ? ",X" : "X,") + ", "			// S - Kvinna, T - Man
				+ (skateboarder     ? "X"  : "")   + ", " 			// U
				+ (surfer           ? "X"  : "")   + ", "			// V
				+ (snowboarder      ? "X"  : "")   + ", " 			// W
				+ (longboardMember  ? "X"  : "")   + ", " 			// X
				+ (supportingMember ? "X"  : "")   + ", "			// Y
				+ (ssaMember        ? "X"  : "")   + ", "			// Z
				+ ", "												// AA - Medlemskap rösträtt SSA...
				+ ", "												// AB - Tävlingsförsäkring
				+ ", "												// AC - Försäkring under 17 år
				+ ", "												// AD - Övrigt
				// 24(!) extra colums for "boardsport kinds": 
				+ (skateboard_street? "X" : "") + ", "				// AE
				+ (skateboard_vert? "X" : "") + ", "				// AF
				+ (skateboard_backyardpools? "X" : "") + ", "		// AG
				+ (skateboard_miniramp_bowlrider? "X" : "")	+ ", "	// AH
				+ (skateboard_slalom? "X" : "") + ", "				// AI
				+ (skateboard_flatland_freestyle? "X" : "")+ ", "	// AJ
				+ (skateboard_longboard_cruise? "X" : "")+ ", "		// AK
				+ (skateboard_downhill? "X" : "") + ", "			// AL
				+ (skateboard_highjump_longjump? "X" : "")	+ ", "	// AM
				+ (snowboard_freeride? "X" : "") + ", "				// AN
				+ (snowboard_pipe? "X" : "") + ", "					// AO
				+ (snowboard_bigjump? "X" : "") + ", "				// AP
				+ (snowboard_snowcross? "X" : "") + ", "			// AQ
				+ (snowboard_alpine? "X" : "") 	+ ", "				// AR
				+ (surfing_shortboard? "X" : "") + ", "				// AS
				+ (surfing_longboard? "X" : "") + ", "				// AT
				+ (surfing_bodyboard? "X" : "") + ", "				// AU
				+ (skimboard? "X" : "") 	+ ", "					// AV
				+ (sandboard? "X" : "") 	+ ", "					// AW
				+ (wakeboard? "X" : "") 	+ ", "					// AX
				+ (kiteboard? "X" : "") 	+ ", "					// AY
				+ (windsurf? "X" : "") 	+ ", "						// AZ
				+ (hoverboard? "X" : "") 		+ ", "				// BA
				+ "" + ", "											// BB Kvinna ålder
				+ "" + ", "											// BC Man ålder
				+ membershipInfo.trim().replace(',', ';') 			// BD Notering - OBS! Nästa rad även den i samma fält
				+ boardsportInfo.trim().replace(',', ';') + ", "    // BD Notering -"-
			;

	}
}
