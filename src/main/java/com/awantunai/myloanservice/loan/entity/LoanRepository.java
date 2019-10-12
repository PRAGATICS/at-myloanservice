package com.awantunai.myloanservice.loan.entity;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

	@Repository
	public interface LoanRepository extends MongoRepository<Loan, String> {

		
}
