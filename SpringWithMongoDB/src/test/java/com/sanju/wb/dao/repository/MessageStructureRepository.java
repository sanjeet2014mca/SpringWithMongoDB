package com.sanju.wb.dao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sanju.wb.dao.entity.MessageStructure;

public interface MessageStructureRepository extends MongoRepository<MessageStructure, Long>{

}
