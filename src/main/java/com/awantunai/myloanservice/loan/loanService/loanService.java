package com.awantunai.myloanservice.loan.loanService;

import java.util.List;
import com.awantunai.myloanservice.loan.entity.LoanTable;

public interface loanService {

	public String validateSession(String arg0);
	
	public List<LoanTable> getAllLoans();
	
	public LoanTable createLoan(LoanTable loanTable);
}
