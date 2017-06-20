	package com.sanju.wb.dao.entity;

	import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
	@Document(collection = "UserTest")
	 public class UserTest {
		@Id
		private String _Id;
		private String participantName;
		private String partyName;
		private String partyID;
		private String partyAdminID;
		private String programe;
		private String userEmailId;
		private String idNUmber;
		private String idType;
		public String getPrograme() {
			return programe;
		}
		public void setPrograme(String programe) {
			this.programe = programe;
		}
		@DBRef
		@CascadeSave
		private List<DataFields> dataFields;
		public List<DataFields> getDataFields() {
			return dataFields;
		}
		public void setDataFields(List<DataFields> dataFields) {
			this.dataFields = dataFields;
		}
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
		
		public String getUserEmailId() {
			return userEmailId;
		}
		public void setUserEmailId(String userEmailId) {
			this.userEmailId = userEmailId;
		}
		public String getIdNUmber() {
			return idNUmber;
		}
		public void setIdNUmber(String idNUmber) {
			this.idNUmber = idNUmber;
		}
		public String getIdType() {
			return idType;
		}
		public void setIdType(String idType) {
			this.idType = idType;
		}
		
		

	}
