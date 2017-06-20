package com.sanju.wb.dao.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@CompoundIndexes({
    @CompoundIndex(name = "participant", unique = true, def = "{'programName' : 1, 'participantName' : 1}")
})
@Document(collection = "participant")
public class Participant {
	
	@Id
	private String _Id;
	private String participantName;
	private String participantId;
	private String programName;
	private String entityDetails;
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
	public String getParticipantId() {
		return participantId;
	}
	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getEntityDetails() {
		return entityDetails;
	}
	public void setEntityDetails(String entityDetails) {
		this.entityDetails = entityDetails;
	}
	
}
