package com.awantunai.myloanservice.loan.loanRepo;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.awantunai.myloanservice.loan.entity.LoanTable;

public interface loanRepo extends CrudRepository<LoanTable, Integer> {

	@Query(value = "select * from temp_loans where sno=1",nativeQuery=true)
	List<LoanTable> getObj();
	
	@Query(value = "select * from temp_loans",nativeQuery=true)
	List<LoanTable> getAllLoans();
	
	@Query(value = "select top 1 sno from temp_loans order by sno desc",nativeQuery=true)
	int getLastLoanId();
	
	@Query(value = "select * from temp_loans",nativeQuery=true)
	List<LoanTable> getByPageRequest(Pageable pageRequest);
}
