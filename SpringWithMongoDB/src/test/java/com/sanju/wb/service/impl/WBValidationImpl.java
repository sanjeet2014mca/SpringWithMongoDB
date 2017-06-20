package com.sanju.wb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanju.wb.dao.entity.Participant;
import com.sanju.wb.dao.entity.Program;
import com.sanju.wb.dao.repository.ParticipantRepository;
import com.sanju.wb.dao.repository.ProgramRepository;
import com.sanju.wb.service.WBValidationService;

@Service("wbValidation")
public class WBValidationImpl implements WBValidationService {

	@Autowired
	ProgramRepository programRepository;
	
	@Autowired
	ParticipantRepository participantRepository;
	

	
	public boolean isProgramExist(String programName){
		Program program = programRepository.findByProgramName(programName);
		return (program != null) ? true : false;
	}
	
	/*public boolean isParticipantExist(String participantName){
		Participant participant = participantRepository.findByParticpantName(participantName);
		return (participant != null) ? true : false;
	}*/
	
	public boolean isProgramAndPartipantExist(String programName, String participantName){
		Participant participant = participantRepository.findByProgramNameAndParticipantName(programName,participantName);
		return (participant != null) ? true : false;
	}
	
}
