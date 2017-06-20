package com.sanju.wb.dao.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "program")
@Data public class Program {
	
	@Id	
	private String _Id;
	private String programProposedAdmin;
	@Indexed(unique = true)
	private String programName;
	private String businessPurpose;
	private String businessLead;
	private String projectManager;
	private String crManager;
	private Integer expectedNoofUsers;
	private Integer expectedNoofTxns;
	private String creator;
	public String get_Id() {
		return _Id;
	}
	public void set_Id(String _Id) {
		this._Id = _Id;
	}
	public String getProgramProposedAdmin() {
		return programProposedAdmin;
	}
	public void setProgramProposedAdmin(String programProposedAdmin) {
		this.programProposedAdmin = programProposedAdmin;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getBusinessPurpose() {
		return businessPurpose;
	}
	public void setBusinessPurpose(String businessPurpose) {
		this.businessPurpose = businessPurpose;
	}
	public String getBusinessLead() {
		return businessLead;
	}
	public void setBusinessLead(String businessLead) {
		this.businessLead = businessLead;
	}
	public String getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}
	public String getCrManager() {
		return crManager;
	}
	public void setCrManager(String crManager) {
		this.crManager = crManager;
	}
	public Integer getExpectedNoofUsers() {
		return expectedNoofUsers;
	}
	public void setExpectedNoofUsers(Integer expectedNoofUsers) {
		this.expectedNoofUsers = expectedNoofUsers;
	}
	public Integer getExpectedNoofTxns() {
		return expectedNoofTxns;
	}
	public void setExpectedNoofTxns(Integer expectedNoofTxns) {
		this.expectedNoofTxns = expectedNoofTxns;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
}
