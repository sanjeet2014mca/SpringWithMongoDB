package com.sanju.wb.dao.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@CompoundIndexes({
    @CompoundIndex(name = "MessageStructure", unique = true, def = "{'programe' : 1, 'processID' : 1}")
})
@Document(collection = "MessageStructure")
 public class MessageStructure {
	@Id
	private String _Id;
	private String processID;
	private String processName;
	private String fromParticipant;
	private String toParticipant;
	@DBRef
	@CascadeSave
	private List<DataFields> dataFields;
	private String bindrule;
	private String binding_message_structure_id;
	private String offered_message_structure_id;
	private String message_structure_id;
	private String message_structure_uuid;
	private String key_binding_message_id;
	private String creator;
	private String js_input_validation_script;
	private String js_reference_rules_script;
	private ArrayList<String> allowed_send;
	private ArrayList<String> allowed_receive;
	private ArrayList<String> allowed_publish;
	private String programe;
	private ArrayList<String> allowed_responders;
	public String get_Id() {
		return _Id;
	}
	public void set_Id(String _Id) {
		this._Id = _Id;
	}
	public String getProcessID() {
		return processID;
	}
	public void setProcessID(String processID) {
		this.processID = processID;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getFromParticipant() {
		return fromParticipant;
	}
	public void setFromParticipant(String fromParticipant) {
		this.fromParticipant = fromParticipant;
	}
	public String getToParticipant() {
		return toParticipant;
	}
	public void setToParticipant(String toParticipant) {
		this.toParticipant = toParticipant;
	}
	public List<DataFields> getDataFields() {
		return dataFields;
	}
	public void setDataFields(List<DataFields> dataFields) {
		this.dataFields = dataFields;
	}
	public String getBindrule() {
		return bindrule;
	}
	public void setBindrule(String bindrule) {
		this.bindrule = bindrule;
	}
	public String getBinding_message_structure_id() {
		return binding_message_structure_id;
	}
	public void setBinding_message_structure_id(String binding_message_structure_id) {
		this.binding_message_structure_id = binding_message_structure_id;
	}
	public String getOffered_message_structure_id() {
		return offered_message_structure_id;
	}
	public void setOffered_message_structure_id(String offered_message_structure_id) {
		this.offered_message_structure_id = offered_message_structure_id;
	}
	public String getMessage_structure_id() {
		return message_structure_id;
	}
	public void setMessage_structure_id(String message_structure_id) {
		this.message_structure_id = message_structure_id;
	}
	public String getMessage_structure_uuid() {
		return message_structure_uuid;
	}
	public void setMessage_structure_uuid(String message_structure_uuid) {
		this.message_structure_uuid = message_structure_uuid;
	}
	public String getKey_binding_message_id() {
		return key_binding_message_id;
	}
	public void setKey_binding_message_id(String key_binding_message_id) {
		this.key_binding_message_id = key_binding_message_id;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getJs_input_validation_script() {
		return js_input_validation_script;
	}
	public void setJs_input_validation_script(String js_input_validation_script) {
		this.js_input_validation_script = js_input_validation_script;
	}
	public String getJs_reference_rules_script() {
		return js_reference_rules_script;
	}
	public void setJs_reference_rules_script(String js_reference_rules_script) {
		this.js_reference_rules_script = js_reference_rules_script;
	}
	public ArrayList<String> getAllowed_send() {
		return allowed_send;
	}
	public void setAllowed_send(ArrayList<String> allowed_send) {
		this.allowed_send = allowed_send;
	}
	public ArrayList<String> getAllowed_receive() {
		return allowed_receive;
	}
	public void setAllowed_receive(ArrayList<String> allowed_receive) {
		this.allowed_receive = allowed_receive;
	}
	public ArrayList<String> getAllowed_publish() {
		return allowed_publish;
	}
	public void setAllowed_publish(ArrayList<String> allowed_publish) {
		this.allowed_publish = allowed_publish;
	}
	public String getPrograme() {
		return programe;
	}
	public void setPrograme(String programe) {
		this.programe = programe;
	}
	public ArrayList<String> getAllowed_responders() {
		return allowed_responders;
	}
	public void setAllowed_responders(ArrayList<String> allowed_responders) {
		this.allowed_responders = allowed_responders;
	}


}
