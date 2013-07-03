package se.subsurfers.members.rcp.data;

import java.util.Date;

import com.google.gdata.data.spreadsheet.ListEntry;

import se.subsurfers.utils.SubsurfMember;

public class ListEntry2MemberConverter {

	public static SubsurfMember convert(ListEntry entry) {
		
		
		String firstname = "";
		String lastname = "";
		String registrationDate = "";
		Date registrationValidUntil = null;
		Date deRegistered = null;
		int membershipFee = 0;
		String mobilePhoneNo = "";
		String sssEmail = "";
		String otherEmail = "";
		String postalNumber = "";
		String streetAddress = "";
		String city = "";
		String fixedPhone = "";
		String birthdate = "";
		boolean surfing_longboard = false;
		boolean surfing_shortboard = false;
		boolean snowboard_alpine = false;
		boolean snowboard_bigjump = false;
		boolean snowboard_pipe = false;
		boolean skateboard_highjump_longjump = false;
		boolean skateboard_downhill = false;
		boolean ssaMember = false;
		boolean longboardMember = false;
		boolean snowboarder = false;
		boolean genderIsMale = false;
		boolean skateboarder = false;
		boolean surfer = false;
		boolean longboarder = false;
		boolean supportingMember = false;
		boolean skateboard_street = false;
		boolean skateboard_vert = false;
		boolean skateboard_backyardpools = false;
		boolean skateboard_miniramp_bowlrider = false;
		boolean skateboard_slalom = false;
		boolean skateboard_flatland_freestyle = false;
		boolean skateboard_longboard_cruise = false;
		boolean snowboard_freeride = false;
		boolean snowboard_snowcross = false;
		boolean surfing_bodyboard = false;
		boolean skimboard = false;
		boolean sandboard = false;
		boolean wakeboard = false;
		boolean wakeskate = false;
		boolean kiteboard = false;
		boolean windsurf = false;
		boolean hoverboard = false;
		String boardsportInfo = "";
		String membershipInfo = "";
		
		SubsurfMember member = new SubsurfMember(firstname, lastname,
				registrationDate, registrationValidUntil, deRegistered,
				membershipFee, mobilePhoneNo, sssEmail, otherEmail,
				streetAddress, postalNumber, city, fixedPhone, birthdate,
				genderIsMale, skateboarder, surfer, snowboarder, longboarder,
				supportingMember, longboardMember, ssaMember,
				skateboard_street, skateboard_vert, skateboard_backyardpools,
				skateboard_miniramp_bowlrider, skateboard_slalom,
				skateboard_flatland_freestyle, skateboard_longboard_cruise,
				skateboard_downhill, skateboard_highjump_longjump,
				snowboard_freeride, snowboard_pipe, snowboard_bigjump,
				snowboard_snowcross, snowboard_alpine, surfing_shortboard,
				surfing_longboard, surfing_bodyboard, skimboard, sandboard,
				wakeboard, wakeskate, kiteboard, windsurf, hoverboard,
				membershipInfo, boardsportInfo);

		return member;
	}
}
