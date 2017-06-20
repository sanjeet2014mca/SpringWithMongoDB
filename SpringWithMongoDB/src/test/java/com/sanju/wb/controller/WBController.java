package com.sanju.wb.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sanju.wb.dao.entity.UserTest;
import com.sanju.wb.service.WBUtilityService;
import com.sanju.wb.utils.ResponseWrapper;


@RestController
public class WBController {

	@Autowired
	WBUtilityService wbUtility;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/createProgram")
	public @ResponseBody ResponseWrapper createProgram(HttpServletRequest request,HttpServletResponse response){

		//String data = request.getParameter("data");
		JSONObject obj = new JSONObject();
		obj.put("programProposedAdmin", "admin@ba.com");
		obj.put("programName", "Test");
		obj.put("businessPurpose", "Test");
		obj.put("businessLead", "A");
		obj.put("projectManager", "A");
		obj.put("crManager", "Veera");
		obj.put("expectedNoofUsers", 50);
		obj.put("expectedNoofTxns", 150);

		ResponseWrapper result = wbUtility.createProgram(obj.toJSONString());
		System.out.println(result);
		return result;	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/createParticipant")
	public @ResponseBody ResponseWrapper createParticipant(HttpServletRequest request,HttpServletResponse response){

		//String data = request.getParameter("data");
		JSONObject entityDetails = new JSONObject();

		JSONObject obj = new JSONObject();
		obj.put("participantName", "Test");
		obj.put("participantId", "PRTPNT001");
		obj.put("programName", "Test");
		obj.put("entityDetails", entityDetails.toJSONString());


		ResponseWrapper result = wbUtility.createParticipant(obj.toJSONString());
		System.out.println(result.toString());
		return result;	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/createParty")
	public @ResponseBody ResponseWrapper createParty(HttpServletRequest request,HttpServletResponse response){

		//String data = request.getParameter("data");
		JSONObject partyDetails = new JSONObject();

		JSONObject obj = new JSONObject();
		obj.put("participantName", "Anitha@pn.com");
		obj.put("partyName", "Test");
		obj.put("partyID", "PRTY001");
		obj.put("partyAdminID", "abhishek@prty.com");
		obj.put("program", "Test");
		obj.put("partyDetails", partyDetails.toJSONString());

		ResponseWrapper result = wbUtility.createParty(obj.toJSONString());
		System.out.println(result);
		return result;	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/createStructure")
	public @ResponseBody ResponseWrapper createStructure(HttpServletRequest request,HttpServletResponse response){

		//String data = request.getParameter("data");

		JSONObject df = new JSONObject();
		df.put("index", 0);
		df.put("referenceMessageId", "First Name");
		df.put("isList", false);
		df.put("label", "firstName");
		df.put("fieldClass", "String");
		df.put("helpText", "First Name");
		//df.put("ObjectId", "1");

		JSONObject df1 = new JSONObject();
		df1.put("index",1);
		df1.put("referenceMessageId", "Last Name");
		df1.put("isList", false);
		df1.put("label", "lastName");
		df1.put("fieldClass", "String");
		df1.put("helpText", "Last Name");
		//df.put("ObjectId", "2");


		JSONArray list = new JSONArray();
		list.add(df);
		list.add(df1);

		JSONObject obj = new JSONObject();
		obj.put("programe", "Test");
		obj.put("processID", "AB_TEST");
		obj.put("processName", "AB_TEST");
		obj.put("bindrule", "none");
		obj.put("fromParticipant", "anitha@pn.com");
		obj.put("toParticipant", "abhishek@pn.com");
		obj.put("jsInputValidationScript", "Script");
		obj.put("jsReferenceRulesScript", "Script");
		obj.put("dataFields",list );

		ResponseWrapper result = wbUtility.createStructure(obj.toJSONString());
		System.out.println(result);
		return result;	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/createUser")
	public @ResponseBody ResponseWrapper createUser(HttpServletRequest request,HttpServletResponse response){
		//String data = request.getParameter("data");
		JSONObject partyDetails = new JSONObject();
		JSONObject obj = new JSONObject();
		obj.put("participantName", "Anitha@pn.com");
		obj.put("partyName", "abhishek@prty.com");
		obj.put("partyID", "PRTY001");
		obj.put("partyAdminID", "abhishek@prty.com");
		obj.put("programe", "Test");
		obj.put("userEmailId", "sanju.r@mongo.com");
		obj.put("idNUmber", "idNumber");
		obj.put("idType", "idType");
		obj.put("userDetails", partyDetails.toJSONString());
		ResponseWrapper result = wbUtility.createUser(obj.toJSONString());
		System.out.println(result.toString());
		return result;	
	}	

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateUser")
	public @ResponseBody ResponseWrapper updateUser(HttpServletRequest request,HttpServletResponse response){
		//String data = request.getParameter("data");
		JSONObject userUpdateDetails = new JSONObject();
		JSONObject obj = new JSONObject();
		obj.put("participantName", "Test@.com");
		obj.put("partyName", "Test@.com");
		obj.put("partyID", "Test");
		obj.put("partyAdminID", "Test@.com");
		obj.put("programe", "Test");
		obj.put("userEmailId", "sanju.r@mongo.com");
		obj.put("idNUmber", "Test@.com");
		obj.put("idType", "idTestType");
		obj.put("userupdateDetails", userUpdateDetails.toJSONString());
		ResponseWrapper result = wbUtility.updateUser(obj.toJSONString());
		System.out.println(result.toString());
		return result;	
	}	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/userCreateTest")
	public @ResponseBody ResponseWrapper userTest(@CookieValue(value = "hitCounter", defaultValue = "0") Long hitCounter,HttpServletResponse response){
		//String data = request.getParameter("data");
		hitCounter++;
		// create cookie and set it in response
		Cookie cookie = new Cookie("hitCounter", hitCounter.toString());
		response.addCookie(cookie);
		JSONObject partyDetails = new JSONObject();
		JSONObject obj = new JSONObject();
		obj.put("participantName", "Test@gmail.com"+String.valueOf(hitCounter));
		obj.put("partyName", "abhishek@prty.com");
		obj.put("partyID", "PRTY001");
		obj.put("partyAdminID", "abhishek@prty.com");
		obj.put("programe", "Test");
		obj.put("userEmailId", "sanju.r@mongo.com"+String.valueOf(hitCounter));
		obj.put("idNUmber", "idNumber");
		obj.put("idType", "idType");
		obj.put("userDetails", partyDetails.toJSONString());
		ResponseWrapper result = wbUtility.createUserTest(obj.toJSONString());
		System.out.println(result.toString());
		return result;	
	}	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateUserTest")
	public @ResponseBody ResponseWrapper updateUserTest(@CookieValue(value = "hitCounter", defaultValue = "0") Long hitCounter,HttpServletRequest request,HttpServletResponse response){
		//String data = request.getParameter("data");
		// create cookie and set it in response
		Cookie cookie = new Cookie("hitCounter", null);
		hitCounter++;
		response.addCookie(cookie);
		JSONObject userUpdateDetails = new JSONObject();
		JSONObject obj = new JSONObject();
		obj.put("participantName", "Test@.com"+String.valueOf(hitCounter));
		obj.put("partyName", "TestUpdate@.com");
		obj.put("partyID", "TestUpdate");
		obj.put("partyAdminID", "TestUpdate@.com");
		obj.put("programe", "Test");
		obj.put("userEmailId", "sanju.r@mongo.com"+String.valueOf(hitCounter));
		obj.put("idNUmber", "TestUpdate@.com");
		obj.put("idType", "TestUpdate");
		obj.put("userupdateDetails", userUpdateDetails.toJSONString());
		ResponseWrapper result = wbUtility.updateUserTest(obj.toJSONString());
		System.out.println(result.toString());
		return result;	
	}	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/removeUserTest")
	public @ResponseBody ResponseWrapper removeUserTest(@CookieValue(value = "hitCounter", defaultValue = "0") Long hitCounter,HttpServletRequest request,HttpServletResponse response){
		//String data = request.getParameter("data");
		// create cookie and set it in response
		Cookie cookie = new Cookie("hitCounter", null);
		hitCounter++;
		response.addCookie(cookie);
		JSONObject obj = new JSONObject();
		obj.put("userEmailId", "sanju.r@mongo.com"+String.valueOf(hitCounter));
		ResponseWrapper result = wbUtility.removeUserTest(obj.toJSONString());
		System.out.println(result.toString());
		return result;	
	}	
	@RequestMapping(value = "/findAllUserTest")
	public @ResponseBody List<UserTest> findAllUserTest(HttpServletRequest request,HttpServletResponse response){
		List<UserTest> listUser=null;
		try{
			listUser =wbUtility.findAllUserTest();
			if(listUser.size()>=1){
				System.out.println("Number of user = " + listUser.size());
				return listUser;
			}
		}catch(NullPointerException e){
			e.printStackTrace();
		}
		return listUser;	
	}	





}
