package com.sanju.wb.dao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sanju.wb.dao.entity.Party;

public interface PartyRepository extends MongoRepository<Party, Long>{

}
