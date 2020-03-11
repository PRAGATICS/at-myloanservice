package com.awantunai.myloanservice.loan.loanService.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awantunai.myloanservice.loan.entity.LoanTable;
import com.awantunai.myloanservice.loan.loanRepo.loanRepo;
import com.awantunai.myloanservice.loan.loanService.loanService;

@Service
public class loanServiceImpl implements loanService{

	@Autowired
	loanRepo loanRepo;
	
	@Override
	public String validateSession(String arg0) {
		// TODO Auto-generated method stub
		return arg0+"123";
	}

	@Override
	public List<LoanTable> getAllLoans() {
		// TODO Auto-generated method stub
		return loanRepo.getAllLoans();
	}

	@Override
	public LoanTable createLoan(LoanTable loanTable) {
		// TODO Auto-generated method stub
		loanTable.setId("AT"+generateId());
		loanRepo.save(loanTable);
		return loanTable;
	}
	
	public int generateId() {
		return loanRepo.getLastLoanId() + 1;
	}

}
