package com.sanju.wb.service;

public interface WBValidationService {

	public boolean isProgramExist(String programName);
	public boolean isProgramAndPartipantExist(String programName, String participantName);
}
