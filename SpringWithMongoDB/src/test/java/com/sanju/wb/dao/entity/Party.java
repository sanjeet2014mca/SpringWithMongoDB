package com.sanju.wb.dao.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@CompoundIndexes({
    @CompoundIndex(name = "party", unique = true, def = "{'program' : 1, 'participantName' : 1, 'partyName' : 1}")
})
@Document(collection = "party")
public class Party {

	@Id
	private String _Id;
	private String participantName;
	private String partyName;
	private String partyID;
	private String partyAdminID;
	private String program;
	private String partyDetails;
	public String get_Id() {
		return _Id;
	}
	public void set_Id(String _Id) {
		this._Id = _Id;
	}
	public String getParticipantName() {
		return participantName;
	}
	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getPartyID() {
		return partyID;
	}
	public void setPartyID(String partyID) {
		this.partyID = partyID;
	}
	public String getPartyAdminID() {
		return partyAdminID;
	}
	public void setPartyAdminID(String partyAdminID) {
		this.partyAdminID = partyAdminID;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getPartyDetails() {
		return partyDetails;
	}
	public void setPartyDetails(String partyDetails) {
		this.partyDetails = partyDetails;
	}
	
}
