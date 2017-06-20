package com.sanju.wb.dao.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "dataFields")
public class DataFields {
	
	@Id
	private String _Id;
	private String fieldClass;
	//private String referenceMessageId;
	//private MessageStructure referenceMessage;
	private boolean isList;
	private String label;
	private String helpText;
	private int index;
	
}
