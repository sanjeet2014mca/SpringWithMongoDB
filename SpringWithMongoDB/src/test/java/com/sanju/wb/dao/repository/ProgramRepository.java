package com.sanju.wb.dao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.sanju.wb.dao.entity.Program;

public interface ProgramRepository extends MongoRepository<Program, Long>{

	 @Query("{programName : ?0}")
	 Program findByProgramName(String programName);

}
