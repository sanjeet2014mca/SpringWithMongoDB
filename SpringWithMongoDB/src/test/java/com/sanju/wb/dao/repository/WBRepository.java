package com.sanju.wb.dao.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.mongodb.WriteResult;
import com.sanju.wb.dao.entity.DataFields;
import com.sanju.wb.dao.entity.MessageStructure;
import com.sanju.wb.dao.entity.Participant;
import com.sanju.wb.dao.entity.Party;
import com.sanju.wb.dao.entity.Program;
import com.sanju.wb.dao.entity.User;
import com.sanju.wb.dao.entity.UserEntity;
import com.sanju.wb.dao.entity.UserTest;
import com.sanju.wb.utils.ResponseWrapper;

import lombok.Data;

@Repository
@Data public class WBRepository {

	@Autowired
	MongoOperations mongoOperations;

	@Autowired
	WBQueryRepository wbQueryOperations;

	@Autowired
	CascadingMongoEventListener cascadingEventListener;

	public ResponseWrapper upsertProgram(Program program){
		ResponseWrapper wrapper = new ResponseWrapper();
		try {
			if(wbQueryOperations.isProgramExist(program.getProgramName())){

				Program pgrm = mongoOperations.findOne(new Query().addCriteria(Criteria.where("programName").is(program.getProgramName())), Program.class);
				if(pgrm != null){
					pgrm.setBusinessLead(program.getBusinessLead());
					pgrm.setBusinessPurpose(program.getBusinessPurpose());
					pgrm.setCrManager(program.getCrManager());
					pgrm.setExpectedNoofTxns(program.getExpectedNoofTxns());
					pgrm.setExpectedNoofUsers(program.getExpectedNoofUsers());
					pgrm.setProgramProposedAdmin(program.getProgramProposedAdmin());
					pgrm.setProjectManager(program.getProjectManager());
					//pgrm.setProgramName(program.getProgramName());
					mongoOperations.save(pgrm);
				}else{
					mongoOperations.save(program);
				}
			}else{
				mongoOperations.save(program);
			}

		} catch (Exception e) {
			wrapper.errorCode = 1002;
			wrapper.errorMessage = program.getProgramName() + "Duplicate Not Allowed";
		}
		return wrapper;
	}

	public ResponseWrapper upsertParticipant(Participant participant){
		ResponseWrapper wrapper = new ResponseWrapper();
		try {
			if(wbQueryOperations.isProgramExist(participant.getProgramName())){
				if(wbQueryOperations.isProgramAndParticipantExist(participant.getProgramName(),participant.getParticipantName())){
					Participant prticipant = mongoOperations.findOne(new Query().addCriteria(Criteria.where("programName").is(participant.getProgramName())), Participant.class);
					prticipant.setEntityDetails(participant.getEntityDetails());
					prticipant.setParticipantId(participant.getParticipantId());
					prticipant.setParticipantName(participant.getParticipantName());
					mongoOperations.save(prticipant);
					wrapper.errorCode= 0;
				}else{
					mongoOperations.save(participant);
					wrapper.errorCode= 0;
				}
			}else{
				wrapper.errorCode = 1001;
				wrapper.errorMessage = participant.getProgramName() + " program doesnt exist";
			}
		} catch (Exception e) {
			wrapper.errorCode= 1000;
			wrapper.errorMessage = participant.getParticipantName() + " Duplicate Not Allowed";
		}
		return wrapper;
	}

	public ResponseWrapper upsertParty(Party party){
		ResponseWrapper wrapper = new ResponseWrapper();
		try {
			if(wbQueryOperations.isProgramExist(party.getProgram())){
				if(wbQueryOperations.isProgramAndParticipantExist(party.getProgram(),party.getParticipantName())){
					Party prty = mongoOperations.findOne(new Query().addCriteria(Criteria.where("program").is(party.getProgram()).and("participantName").is(party.getParticipantName()).and("partyName").is(party.getPartyName())), Party.class);
					if(prty != null){
						prty.setParticipantName(party.getParticipantName());
						prty.setPartyAdminID(party.getPartyAdminID());
						prty.setPartyDetails(party.getPartyDetails());
						prty.setPartyID(party.getPartyID());
						prty.setPartyName(party.getPartyName());
						mongoOperations.save(prty);
						wrapper.errorCode = 0;
					}else{
						mongoOperations.save(party);
						wrapper.errorCode = 0;
					}
				}else{
					wrapper.errorCode = 1000;
					wrapper.errorMessage = party.getProgram() +" and "+ party.getParticipantName() + " doesnt exist";
				}
			}else{
				wrapper.errorCode = 1000;
				wrapper.errorMessage = party.getProgram() + " doesnt exist";
			}
		} catch (Exception e) {
			wrapper.errorCode = 1000;
			wrapper.errorMessage = party.getPartyName() + " Duplicate Entry Not Allowed";
		}
		return wrapper;
	}

	public ResponseWrapper upsertStructure(MessageStructure msgStructure) {
		ResponseWrapper wrapper = new ResponseWrapper();
		try {
			if(!wbQueryOperations.isStructureExist(msgStructure.getPrograme(), msgStructure.getProcessID())){
				cascadingEventListener.onBeforeConvert(msgStructure);
				mongoOperations.save(msgStructure);
				wrapper.errorCode = 0;
			}else{
				MessageStructure msg = wbQueryOperations.getStructureByProgramAndProcessID(msgStructure.getPrograme(), msgStructure.getProcessID());
				System.out.println(new Gson().toJson(msg));
				List<DataFields> dfList = msg.getDataFields();
				if(dfList.size() > 0){
				}
				wrapper.errorCode = 1000;
				wrapper.errorMessage = msgStructure.getProcessID() + " Already Exist";
			}

		} catch (Exception e) {
			e.printStackTrace();
			wrapper.errorCode = 1000;
			wrapper.errorMessage = msgStructure.getProcessID() + " Duplicate Entry Not Allowed";
		}
		return wrapper;
	}

	public ResponseWrapper upsertUser(User user) {
		ResponseWrapper wrapper = new ResponseWrapper();
		try {
			if(!wbQueryOperations.isUserExist(user.getPrograme(), user.getUserEmailId())){
				mongoOperations.save(user);
				wrapper.errorCode = 0;
			}else{
				User userdetails = wbQueryOperations.getUserDetailsByProgramAndUserEmail(user.getPrograme(), user.getUserEmailId());
				System.out.println(new Gson().toJson(userdetails));
				List<DataFields> dfList = userdetails.getDataFields();
				if(dfList!=null){
				}
				wrapper.errorCode = 1000;
				wrapper.errorMessage = "Email Id:"+user.getUserEmailId()+",Program Name:"+user.getPrograme()+ " Already Exist";
			}

		} catch (Exception e) {
			e.printStackTrace();
			wrapper.errorCode = 1000;
			wrapper.errorMessage = "Email Id:"+user.getUserEmailId()+",Program Name:"+user.getPrograme()+ " Duplicate Entry Not Allowed";
		}
		return wrapper;
	}
	public ResponseWrapper upsertUserUpdate(User user) {
		ResponseWrapper wrapper = new ResponseWrapper();
		try {

			if(wbQueryOperations.isProgramExist(user.getPrograme())){
				if(wbQueryOperations.isPartyExist(user.getPrograme())){
					if(wbQueryOperations.isParticipantExist(user.getPrograme())){
						//update
						User userOld = mongoOperations.findOne(new Query().addCriteria(Criteria.where("programe").is(user.getPrograme()).and("userEmailId").is(user.getUserEmailId())), User.class);
						Query updateQuery = new Query();
						updateQuery.addCriteria(Criteria.where("_Id").exists(true).orOperator(Criteria.where("_Id").is(userOld.get_Id()),Criteria.where("_Id").is(userOld.get_Id())));
						Update newDocument = new Update();
						newDocument.set("participantName", user.getParticipantName());
						newDocument.set("partyName",user.getPartyName());
						newDocument.set("partyID", user.getPartyID());
						newDocument.set("partyAdminID", user.getPartyAdminID());
						newDocument.set("programe", user.getPrograme());
						newDocument.set("userEmailId", user.getUserEmailId());
						newDocument.set("idNUmber", user.getIdNUmber());
						newDocument.set("idType", user.getIdType());
						newDocument.set("_Id", userOld.get_Id());
						WriteResult u=mongoOperations.updateFirst(updateQuery, newDocument, User.class);
						System.out.println(u.isUpdateOfExisting()+""+u.getN());
						if(u.getN()>=1){
							wrapper.errorCode = 2000;
							wrapper.errorMessage = "Successfuly Updated User Details";
						}
					}else{
						User updateUserDetails = wbQueryOperations.getUserDetailsByProgramAndUserEmail(user.getPrograme(), user.getUserEmailId());
						System.out.println(new Gson().toJson(updateUserDetails));
						wrapper.errorCode = 1001;
						wrapper.errorMessage = "Program Name:-"+user.getPrograme()+ " Not Exist in Participant";
					}
				}else{
					User updateUserDetails = wbQueryOperations.getUserDetailsByProgramAndUserEmail(user.getPrograme(), user.getUserEmailId());
					System.out.println(new Gson().toJson(updateUserDetails));
					wrapper.errorCode = 1002;
					wrapper.errorMessage = "Program Name:-"+user.getPrograme()+ " Not Exist in Party";
				}
			}else{
				User updateUserDetails = wbQueryOperations.getUserDetailsByProgramAndUserEmail(user.getPrograme(), user.getUserEmailId());
				System.out.println(new Gson().toJson(updateUserDetails));
				wrapper.errorCode = 1003;
				wrapper.errorMessage = "Program Name:-"+user.getPrograme()+ " Not Exist Program ";
			}

		} catch (Exception e) {
			e.printStackTrace();
			wrapper.errorCode = 1004;
			wrapper.errorMessage = "Email Id:"+user.getUserEmailId()+",Program Name:"+user.getPrograme()+ " Value Not Found";
		}
		return wrapper;
	}
	public ResponseWrapper upsertUserUpdateTest(UserTest user) {
		ResponseWrapper wrapper = new ResponseWrapper();
		try {

			UserTest userOld = mongoOperations.findOne(new Query().addCriteria(Criteria.where("programe").is(user.getPrograme()).and("userEmailId").is(user.getUserEmailId())), UserTest.class);
			Query updateQuery = new Query();
			updateQuery.addCriteria(Criteria.where("_Id").exists(true).orOperator(Criteria.where("_Id").is(userOld.get_Id()),Criteria.where("_Id").is(userOld.get_Id())));
			Update newDocument = new Update();
			newDocument.set("participantName", user.getParticipantName());
			newDocument.set("partyName",user.getPartyName());
			newDocument.set("partyID", user.getPartyID());
			newDocument.set("partyAdminID", user.getPartyAdminID());
			newDocument.set("programe", user.getPrograme());
			newDocument.set("userEmailId", user.getUserEmailId());
			newDocument.set("idNUmber", user.getIdNUmber());
			newDocument.set("idType", user.getIdType());
			newDocument.set("_Id", userOld.get_Id());
			WriteResult u=mongoOperations.updateFirst(updateQuery, newDocument, UserTest.class);
			System.out.println(u.isUpdateOfExisting()+""+u.getN());
			if(u.getN()>=1){
				wrapper.errorCode = 2000;
				wrapper.errorMessage = "Successfuly Updated User Details";
			}

		} catch (Exception e) {
			e.printStackTrace();
			wrapper.errorCode = 1004;
			wrapper.errorMessage = "Email Id:"+user.getUserEmailId()+",Program Name:"+user.getPrograme()+ " Value Not Found";
		}
		return wrapper;
	}

	public ResponseWrapper removetUserUpdateTest(UserTest user) {
		ResponseWrapper wrapper = new ResponseWrapper();
		try {
			//User userOld = mongoOperations.findOne(new Query().addCriteria(Criteria.where("userEmailId").is(user.getUserEmailId())), User.class);
			UserTest userOld = mongoOperations.findAndRemove(new Query().addCriteria(Criteria.where("userEmailId").is(user.getUserEmailId())), UserTest.class);
			System.out.println(userOld.toString());
			wrapper.errorCode = 2000;
			wrapper.errorMessage = "Successfuly Removed User Details";

		} catch (Exception e) {
			e.printStackTrace();
			wrapper.errorCode = 1004;
			wrapper.errorMessage = "Email Id:"+user.getUserEmailId()+",Program Name:"+user.getPrograme()+ " Value Not Found";
		}
		return wrapper;
	}
	public ResponseWrapper upsertUserTest(UserTest user) {
		ResponseWrapper wrapper = new ResponseWrapper();
		try {
			mongoOperations.save(user);
			wrapper.errorCode = 0;
			wrapper.errorMessage = "Successfuly inserted in db";
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.errorCode = 2002;
			wrapper.errorMessage = "Internal Server Error";
		}
		return wrapper;
	}
	public List<UserTest> findAllUserTest(){
		ResponseWrapper wrapper = new ResponseWrapper();
		List<UserTest> listUser=null;
		try {
			listUser = mongoOperations.findAll(UserTest.class);
			if (listUser!=null){
				wrapper.errorCode = 0;
				wrapper.errorMessage = "See List Of User:";
				return listUser;
			}				
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.errorCode = 2025;
			wrapper.errorMessage = "User Not Found";
		}		
		return listUser;
	}


	public ResponseWrapper createUserRegister(UserEntity userEntity){
		ResponseWrapper wrapper = new ResponseWrapper();
		try {
			if(wbQueryOperations.isUserRegisterExist(userEntity.getEmail(),userEntity.getMobile().toString())){
				wrapper.errorCode = 1002;
				wrapper.errorMessage = "Mobile, "+userEntity.getMobile()+"Email, "+userEntity.getEmail() + "Duplicate Not Allowed";
			}else{
				mongoOperations.save(userEntity);
				wrapper.errorCode = 2000;
				wrapper.errorMessage = "Successfuly Inserted Data";
			}
		} catch (Exception e) {
			wrapper.errorCode = 1002;
			wrapper.errorMessage = wrapper.errorMessage = "Mobile, "+userEntity.getMobile()+"Email, "+userEntity.getEmail() + "Duplicate Not Allowed";
		}
		return wrapper;
	}
	public UserEntity passwordValidate(UserEntity userEntity){
		ResponseWrapper wrapper = new ResponseWrapper();
		UserEntity usrEntity =null;
		try {
			usrEntity = mongoOperations.findOne(new Query().addCriteria(Criteria.where("email").is(userEntity.getEmail())), UserEntity.class);
			if(usrEntity!=null){
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				boolean matches = encoder.matches(userEntity.getPassword(), usrEntity.getPassword());
				if(matches){
					wrapper.errorCode = 1000;
					wrapper.errorMessage = "Success";}
			}else{
				wrapper.errorCode = 2005;
				wrapper.errorMessage = "Data Not Exists";
			}
		} catch (Exception e) {
			wrapper.errorCode = 1002;
			wrapper.errorMessage = wrapper.errorMessage = "Email, "+userEntity.getEmail() + "Internal Error";
		}
		return usrEntity;
	}
	public boolean passwordUpdate(UserEntity userEntity){
		ResponseWrapper wrapper = new ResponseWrapper();
		UserEntity usrEntity =null;
		boolean passUpdate=false;
		try {
			usrEntity = mongoOperations.findOne(new Query().addCriteria(Criteria.where("email").is(userEntity.getEmail()).and("mobile").is(userEntity.getMobile())),UserEntity.class);
			if(usrEntity!=null){
				Query updateQuery = new Query();
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder.encode(userEntity.getPassword());
				updateQuery.addCriteria(Criteria.where("_Id").exists(true).orOperator(Criteria.where("_Id").is(usrEntity.get_Id()),Criteria.where("_Id").is(usrEntity.get_Id())));
				Update newDocument = new Update();
				newDocument.set("mobile", usrEntity.getMobile());
				newDocument.set("email", usrEntity.getEmail());
				newDocument.set("password", hashedPassword);
				newDocument.set("_Id", usrEntity.get_Id());
				WriteResult u=mongoOperations.updateFirst(updateQuery, newDocument, UserEntity.class);
				System.out.println(u.isUpdateOfExisting()+""+u.getN());
				if(u.getN()>=1){
					passUpdate=true;
					wrapper.errorCode = 2000;
					wrapper.errorMessage = "Successfuly Updated User Details";
				}
				else{
					wrapper.errorCode = 2005;
					wrapper.errorMessage = "Data Not Exists";
				}
			}else{
				wrapper.errorCode = 2005;
				wrapper.errorMessage = "Data Not Exists";
			}

		} catch (Exception e) {
			wrapper.errorCode = 1002;
			wrapper.errorMessage = wrapper.errorMessage = "Email, "+userEntity.getEmail() + "Internal Error";
		}
		return passUpdate;
	}

	public boolean deleteUser(UserEntity userEntity){
		ResponseWrapper wrapper = new ResponseWrapper();
		UserEntity usrEntity =null;
		boolean passUpdate=false;
		try {
			usrEntity = mongoOperations.findOne(new Query().addCriteria(Criteria.where("email").is(userEntity.getEmail()).and("mobile").is(userEntity.getMobile())),UserEntity.class);
			if (usrEntity!=null){
				passUpdate=true;
				usrEntity = mongoOperations.findAndRemove(new Query().addCriteria(Criteria.where("email").is(userEntity.getEmail()).and("mobile").is(userEntity.getMobile())),UserEntity.class);
				System.out.println("User Email Id: "+userEntity.getEmail()+" Deleted Successfuly");
				wrapper.errorCode = 2000;
				wrapper.errorMessage = "Successfuly Removed User Details";
			}
			else {
				wrapper.errorCode = 2005;
				wrapper.errorMessage = "User Doesn't Exists";
		
				
			}
		} catch (Exception e) {
			wrapper.errorCode = 1002;
			wrapper.errorMessage = wrapper.errorMessage = "Email, "+userEntity.getEmail() + "Internal Error";
		}
		return passUpdate;
	}






}
