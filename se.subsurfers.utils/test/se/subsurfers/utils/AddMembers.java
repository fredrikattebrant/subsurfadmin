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
		String gata = "Enväg 12";
		String postnr = "123 45";
		String stad = "Abcköping";
		String email = "adam.bertilsson@c.de";
		String medlemsskap = "20 kr från 15 år utan olycksfallsförsäkring.";
		String boardsport = "Skateboard - Street., Skateboard - Miniramp/bowlrider., Skateboard - Flatland freestyle., Snowboard - Freeride.";

		String antonText = "Förnamn: " + firstName + "\n" + "\n"
				+ "Efternamn: "+ lastName+"\n" + "\n"
				+ "Födelsedata (XXXX-XX-XX-XXXX): " + birthdate + "\n"
				+ "Mobilnr (07XXXXXXXX): " + mobilnr + "\n" + "\n"
				+ "Gatuadress: " + gata + "\n" + "\n"
				+ "Postnummer: " +  postnr + "\n" + "\n"
				+ "Postadress: " + stad + "\n" + "\n"
				+ "Email: " + email + "\n" + "\n"
				+ "Välj medlemskap: " + medlemsskap + "\n" + "\n"
				+ "Välj brädsport: " + boardsport + "\n" + "\n"
				;
		SubsurfMember member1 = NewMemberUtil.addMember(antonText);

		assert(member1.getFirstName().equals(firstName));
		assert(member1.isSkateboarder());
		assert(member1.isSnowboarder());
		// fail("Not yet implemented");
	}

}
