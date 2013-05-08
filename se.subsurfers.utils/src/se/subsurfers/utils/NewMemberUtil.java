package se.subsurfers.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

public class NewMemberUtil {

	private static final String FIRSTNAMETOKEN = "Förnamn";
	private static final String LASTNAMETOKEN = "Efternamn";
	private static final String BIRTHDATETOKEN = "Födelsedata (XXXX-XX-XX-XXXX)";
	private static final String MOBILETOKEN = "Mobilnr (07XXXXXXXX)";
	private static final String STREETTOKEN = "Gatuadress";
	private static final String POSTALTOKEN = "Postnummer";
	private static final String CITYTOKEN = "Postadress";
	private static final String EMAILTOKEN = "Email";
	private static final String MEMBERSHIPTOKEN = "Välj medlemskap";
	private static final String BOARDTOKEN = "Välj brädsport";
	private static final String REGISTRATIONDATETOKEN = "Date/Time";

	private static final String SKATEBOARDTOKEN = "Skateboard";
	private static final String SURFTOKEN = "Surfing";
	private static final String SNOWBOARDTOKEN = "Snowboard";
	private static final String LONGBOARDTOKEN = "Longboard";

	private static final String SKATEBOARD_STREET = "Skateboard - Street.";
	private static final String SKATEBOARD_VERT = " Skateboard - Vert.";
	private static final String SKATEBOARD_BACKYARD_POOLS = " Skateboard - Backyard pools.";
	private static final String SKATEBOARD_MINIRAMP_BOWLRIDER = " Skateboard - Miniramp/bowlrider.";
	private static final String SKATEBOARD_SLALOM = " Skateboard - Slalom.";
	private static final String SKATEBOARD_FLATLAND_FREESTYLE = " Skateboard - Flatland freestyle.";
	private static final String SKATEBOARD_LONGBOARD_CRUISE = " Skateboard - Longboard cruise.";
	private static final String SKATEBOARD_DOWNHILL = " Skateboard - Downhill.";
	private static final String SKATEBOARD_HIGH_LONG_JUMP = " Skateboard - Höjdhopp/längdhopp.";
	private static final String SNOWBOARD_FREERIDE = " Snowboard - Freeride.";
	private static final String SNOWBOARD_PIPE = " Snowboard - Pipe.";
	private static final String SNOWBOARD_BIGJUMP = " Snowboard - Big Jump.";
	private static final String SNOWBOARD_SNOWCROSS = " Snowboard - Snow Cross.";
	private static final String SNOWBOARD_ALPINE = " Snowboard - Alpine.";
	private static final String SURFING_SHORTBOARD = " Surfing - Shortboard.";
	private static final String SURFING_LONGBOARD = " Surfing - Longboard.";
	private static final String SURFING_BODYBOARD = " Surfing - Bodyboard.";
	private static final String SKIMBOARD = " Skimboard";
	private static final String SANDBOARD = " Sandboard";
	private static final String WAKEBOARD = " Wakeboard";
	private static final String WAKESKATE = " Wakeskate";
	private static final String KITEBOARD = " Kiteboard";
	private static final String WINDSURF = " Windsurf";
	private static final String HOVERBOARD = " Hoverboard";

	private static final String EXTRATOKEN = "Extra";

	private static boolean debugging = false;

	private static void debug(String message) {
		if (debugging) {
			System.err.println("!!! " + message);
		}
	}

	public static String yy2yyyy(String year) {
		Integer y = new Integer(year);
		if (y > 20) { // 19YY
			y = 1900 + y;
		} else {
			y = 2000 + y;
		}
		year = y.toString();

		return year;
	}

	/**
	 * Method used to parse text and extract key membership information
	 * 
	 * @param text
	 *            typically copied from e-mail with "Ny SSS medlem"
	 * 
	 * @return SubsurfMember
	 */
	public static SubsurfMember addMember(String text) {
		SubsurfMember member;

		String firstname = "";
		String lastname = "";
		String birthdate = "";
		String mobilePhoneNo = "";
		String streetAddress = "";
		String postalNumber = "";
		String city = "";
		String otherEmail = "";
		String membershipKind = "";
		String boardsportKind = "";
		String registrationDate = "";

		Date regDate = null;

		int membershipFee = 0;
		boolean genderIsMale = true; // mostly guys...
		boolean skateboarder = false;
		boolean surfer = false;
		boolean snowboarder = false;
		boolean longboarder = false;
		boolean supportingMember = false;
		boolean longboardMember = false;
		boolean ssaMember = false;
		
		boolean skateboard_street = false;
		boolean skateboard_vert = false;
		boolean skateboard_backyard_pools = false;
		boolean skateboard_miniramp_bowlrider = false;
		boolean skateboard_slalom = false;
		boolean skateboard_flatland_freestyle = false;
		boolean skateboard_longboard_cruise = false;
		boolean skateboard_downhill = false;
		boolean skateboard_highlongjump = false;
		boolean snowboard_freeride = false;
		boolean snowboard_pipe = false;
		boolean snowboard_bigjump = false;
		boolean snowboard_snowcross = false;
		boolean snowboard_alpine = false;
		boolean surfing_shortboard = false;
		boolean surfing_longboard = false;
		boolean surfing_bodyboard = false;
		boolean skimboard = false;
		boolean sandboard = false;
		boolean wakeboard = false;
		boolean wakeskate = false;
		boolean kiteboard = false;
		boolean windsurf = false;
		boolean hoverboard = false;

		// parse text and extract values
		String[] textByLine = text.split("[\\r\\n]+"); // split and remove empty
														// lines
		for (int i = 0; i < textByLine.length; i++) {
			String line = textByLine[i];
			// System.out.println("!!! line: " + line);
			if (!line.contains(":")) {
				// next line
				continue;
			}
			Scanner scanner = new Scanner(line);
			scanner.useDelimiter(":");
			if (scanner.hasNext()) {
				String token = scanner.next();
				String value = scanner.next();

				// to ensure nothing gets lost if someone enters a ":" in the
				// value
				// scan until nothings left and append to the value
				if (scanner.hasNext()) {
					while (scanner.hasNext()) {
						value += scanner.next();
					}
				}

				// remove leading and trailing whitespace
				value = value.trim();

				if (token.equals(FIRSTNAMETOKEN)) {
					firstname = value.trim();
				} else if (token.equals(LASTNAMETOKEN)) {
					lastname = value.trim();
				} else if (token.equals(BIRTHDATETOKEN)) {
					// TODO: convert to YYYY-MM-DD (free format in form...)

					// normalize to YYYY-MM-DD format:
					// - add dashes ("-") if missing (eg: 19810101 ->
					// 1981-01-01)
					// either: YYMMDD, YYYYMMDD or YYYYMMDDNNNN
					debug("Birthdate - orig:      " + value);

					StringBuilder birthDateSb = new StringBuilder();
					Formatter formatter = new Formatter(birthDateSb,
							Locale.ENGLISH); // TODO - Locale ok?

					// remove dashes and spaces - we replace them below
					value = value.trim().replace("-", "");
					value = value.trim().replace(" ", "");
					value = value.trim().replace("/", ""); // handle morons who use "/" as delimiter

					if (value.length() == 6) {
						// YYMMDD
						String year = yy2yyyy(value.substring(0, 2));
						int y = new Integer(year);
						int m = new Integer(value.substring(2, 4));
						int d = new Integer(value.substring(4, 6));
						formatter.format("%04d-%02d-%02d", y, m, d);
						birthdate = birthDateSb.toString();

					} else if (value.length() == 8 || value.length() == 12) {
						// YYYYMMDD or YYYYMMDDNNNN
						int y = new Integer(value.substring(0, 4));
						int m = new Integer(value.substring(4, 6));
						int d = new Integer(value.substring(6, 8));
						formatter.format("%04d-%02d-%02d", y, m, d);
						birthdate = birthDateSb.toString();

					} else if (value.length() == 10) {
						// YYMMDD-NNNN
						String year = yy2yyyy(value.substring(0, 2));
						int y = new Integer(year);
						int m = new Integer(value.substring(2, 4));
						int d = new Integer(value.substring(4, 6));
						formatter.format("%04d-%02d-%02d", y, m, d);
						birthdate = birthDateSb.toString();
					} else {
						System.err.println("*** Konstigt personnummer: "
								+ value + " - " + firstname + ", " + lastname);
					}

					debug("Birthdate - formatted: " + birthdate);

				} else if (token.equals(MOBILETOKEN)) {
					// cleanup to format: 0nnnnnnnn
					// i.e. no "-", " "
					mobilePhoneNo = value.trim().replace("-", "").replace(" ", "");
					// Convert to "+46" for all numbers - preserves number when importing into google docs
					// (import to google drops the leading "0")
					// If number doesn't start with leading "0" - don't convert
					if (mobilePhoneNo.startsWith("0")) {
						mobilePhoneNo = "+46" + mobilePhoneNo.substring(1);
						debug("Converted mobilno to: " + mobilePhoneNo);
					}

				} else if (token.equals(STREETTOKEN)) {
					// replace commas with semicolon
					streetAddress = value.trim().replace(",", ";");
				} else if (token.equals(POSTALTOKEN)) {
					// remove whitespace
					postalNumber = value.trim().replace(" ", "");
				} else if (token.equals(CITYTOKEN)) {
					city = value.trim().replace(",", ";");
				} else if (token.equals(EMAILTOKEN)) {
					otherEmail = value.trim();
				} else if (token.equals(MEMBERSHIPTOKEN)) {
					debug("Membership: " + value);
					int startIx = 0;
					int endIx = value.indexOf(" kr");
					membershipFee = new Integer(value.substring(startIx, endIx));
					debug("Fee: " + membershipFee);
					membershipKind = value;
				} else if (token.equals(BOARDTOKEN)) {
					boardsportKind = value; // save full info string

					skateboarder = isMembershipOfToken(SKATEBOARDTOKEN,
							boardsportKind);
					surfer = isMembershipOfToken(SURFTOKEN, boardsportKind);
					snowboarder = isMembershipOfToken(SNOWBOARDTOKEN,
							boardsportKind);
					longboarder = isMembershipOfToken(LONGBOARDTOKEN,
							boardsportKind);

					// parse content of value
					String boardLine = value;
					Scanner boardLineScanner = new Scanner(boardLine);
					boardLineScanner.useDelimiter(",");
					while (boardLineScanner.hasNext()) {
						String bToken = boardLineScanner.next();
						if (bToken.equals(SKATEBOARD_STREET)) {
							skateboard_street = true;
						} else if (bToken.equals(SKATEBOARD_VERT)) {
							skateboard_vert = true;
						} else if (bToken.equals(SKATEBOARD_BACKYARD_POOLS)) {
							skateboard_backyard_pools = true;
						} else if (bToken.equals(SKATEBOARD_MINIRAMP_BOWLRIDER)) {
							skateboard_miniramp_bowlrider = true;
						} else if (bToken.equals(SKATEBOARD_SLALOM)) {
							skateboard_slalom = true;
						} else if (bToken.equals(SKATEBOARD_FLATLAND_FREESTYLE)) {
							skateboard_flatland_freestyle = true;
						} else if (bToken.equals(SKATEBOARD_LONGBOARD_CRUISE)) {
							skateboard_longboard_cruise = true;
						} else if (bToken.equals(SKATEBOARD_DOWNHILL)) {
							skateboard_downhill = true;
						} else if (bToken.equals(SKATEBOARD_HIGH_LONG_JUMP)) {
							skateboard_highlongjump = true;
						} else if (bToken.equals(SNOWBOARD_FREERIDE)) {
							snowboard_freeride = true;
						} else if (bToken.equals(SNOWBOARD_PIPE)) {
							snowboard_pipe = true;
						} else if (bToken.equals(SNOWBOARD_BIGJUMP)) {
							snowboard_bigjump = true;
						} else if (bToken.equals(SNOWBOARD_SNOWCROSS)) {
							snowboard_snowcross = true;
						} else if (bToken.equals(SNOWBOARD_ALPINE)) {
							snowboard_alpine = true;
						} else if (bToken.equals(SURFING_SHORTBOARD)) {
							surfing_shortboard = true;
						} else if (bToken.equals(SURFING_LONGBOARD)) {
							surfing_longboard = true;
						} else if (bToken.equals(SURFING_BODYBOARD)) {
							surfing_bodyboard = true;
						} else if (bToken.equals(SKIMBOARD)) {
							skimboard = true;
						} else if (bToken.equals(SANDBOARD)) {
							sandboard = true;
						} else if (bToken.equals(WAKEBOARD)) {
							wakeboard = true;
						} else if (bToken.equals(WAKESKATE)) {
							wakeskate = true;
						} else if (bToken.equals(KITEBOARD)) {
							kiteboard = true;
						} else if (bToken.equals(WINDSURF)) {
							windsurf = true;
						} else if (bToken.equals(HOVERBOARD)) {
							hoverboard = true;
						}
					}
				} else if (token.equals(REGISTRATIONDATETOKEN)) {
					// trim off hh:mm:ss timezone, then create
					registrationDate = value.substring(0, value.indexOf(" "));
				} else if (token.equals(EXTRATOKEN)) {
					// extra = one or more of longboard, surf/ssa, separated by '+' - eg:
					//Extra: + 50 kr för Swedish Surfing Association medlemskap., + 50 kr för Longboard Stockholm medlemskap (inkl t-shirt!).
					String SSASTRING = "Swedish Surfing Association";
					String LONGBOARDSTRING = "Longboard Stockholm";
					int extraFee = 0;
					int ix = 0;
					while ((ix = value.indexOf("+", ix)) > -1) {
						extraFee += new Integer(value.substring(ix + 2,
								value.indexOf("kr", ix) - 1));
						ix++;
					}
					ssaMember = value.contains(SSASTRING);
					longboardMember = value.contains(LONGBOARDSTRING);
					int extraFeeVerify = (ssaMember ? 50 : 0) + (longboardMember ? 50 : 0);
					
					membershipFee += extraFee;
					debug("Extra fee: " + extraFee + " => membership fee: "
							+ membershipFee);
					if (extraFee != extraFeeVerify) {
						debug("*** Error: extraFee should be: " + extraFeeVerify + " but is: " + extraFee);
						debug("*** Extra line: " + value);
					}
				}
			}
		}

		member = new SubsurfMember(
				firstname, lastname, 
				registrationDate, null, 
				null, membershipFee, 
				mobilePhoneNo, 
				"", otherEmail, 
				streetAddress, postalNumber, city, 
				"", 
				birthdate, 
				genderIsMale, 
				skateboarder, 
				surfer, 
				snowboarder, 
				longboarder, 
				supportingMember, 
				longboardMember,
				ssaMember,
				skateboard_street, 
				skateboard_vert, 
				skateboard_backyard_pools, 
				skateboard_miniramp_bowlrider, 
				skateboard_slalom, 
				skateboard_flatland_freestyle, 
				skateboard_longboard_cruise, 
				skateboard_downhill, 
				skateboard_highlongjump, 
				snowboard_freeride, 
				snowboard_pipe, 
				snowboard_bigjump, 
				snowboard_snowcross, 
				snowboard_alpine, 
				surfing_shortboard, 
				surfing_longboard, 
				surfing_bodyboard, 
				skimboard, 
				sandboard, 
				wakeboard, 
				wakeskate, 
				kiteboard, 
				windsurf, 
				hoverboard, 
				membershipKind, 
				boardsportKind);
		
		return member;
	}

	private static boolean isMembershipOfToken(String token, String text) {
		if (debugging) {
			debug("Checking token: " + token + " in: " + text + " => " + text.contains(token));
		}
		return text.contains(token);
	}

	public static void main(String[] args) throws UnsupportedEncodingException {

		/*
		 * TODO:
		 * 
		 * o Output character format (UTF8?) to get proper encoding of ÅÄÖ o
		 * Cleanup of birthdate formats o Age calculation o more?
		 */
		
		// sanity check
		if (args.length < 1) {
			System.err.println("!!! No arguments - aborting");
			return;
		}

		if (args[0].equals("-d")) {
			debugging = true;
			debug("Enabled debug printouts");
		}
		
		debug("Reading from files: " + args.length);
		// args are filenames - one per new member
		for (String file : args) {
			File newFile = new File(file);
			if (newFile.canRead()) {
				String newMemberText = "";
				try {
					// Scanner scanner = new Scanner(new FileReader(newFile));
					Scanner scanner;
					try {
						scanner = new Scanner(new InputStreamReader(
								new FileInputStream(newFile), "UTF8"));
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						break;
					}
					try {
						boolean foundStart = false;
						String newLine = "";
						while (scanner.hasNext()) {
							String line = scanner.nextLine();
							if (line.startsWith("Förnamn:")) {
								foundStart = true;
							}
							if (foundStart) {
								newMemberText += line + newLine;
								newLine = "\n";
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				debug("=== New member line: " + newMemberText);
				SubsurfMember member = addMember(newMemberText);
				// System.out.println(member.toString());
				PrintStream out = System.out;
				PrintStream out2 = new PrintStream(out, true, "UTF-8");
				out2.println(member.toCSV());
				// System.out.println(member.toCSV());
				// System.out.println("===");
			}
		}
	}

}
