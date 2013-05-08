package se.subsurfers.utils;

import static org.junit.Assert.*;

import org.junit.Test;

//import se.subsurfers.subsurferadmin.model.SubsurfMember;

public class AddMembers {

	@Test
	public void testAddMember() {
		String firstName = "Adam";
		String lastName = "Bertilsson";
		String birthdate = "1965-01-01-0000";
		String mobilnr = "0798654321";
		String gata = "Env�g 12";
		String postnr = "123 45";
		String stad = "Abck�ping";
		String email = "adam.bertilsson@c.de";
		String medlemsskap = "20 kr fr�n 15 �r utan olycksfallsf�rs�kring.";
		String boardsport = "Skateboard - Street., Skateboard - Miniramp/bowlrider., Skateboard - Flatland freestyle., Snowboard - Freeride.";

		String antonText = "F�rnamn: " + firstName + "\n" + "\n"
				+ "Efternamn: "+ lastName+"\n" + "\n"
				+ "F�delsedata (XXXX-XX-XX-XXXX): " + birthdate + "\n"
				+ "Mobilnr (07XXXXXXXX): " + mobilnr + "\n" + "\n"
				+ "Gatuadress: " + gata + "\n" + "\n"
				+ "Postnummer: " +  postnr + "\n" + "\n"
				+ "Postadress: " + stad + "\n" + "\n"
				+ "Email: " + email + "\n" + "\n"
				+ "V�lj medlemskap: " + medlemsskap + "\n" + "\n"
				+ "V�lj br�dsport: " + boardsport + "\n" + "\n"
				;
		SubsurfMember member1 = NewMemberUtil.addMember(antonText);

		assert(member1.getFirstName().equals(firstName));
		assert(member1.isSkateboarder());
		assert(member1.isSnowboarder());
		// fail("Not yet implemented");
	}

}
