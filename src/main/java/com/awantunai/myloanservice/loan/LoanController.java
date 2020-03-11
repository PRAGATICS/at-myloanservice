package com.awantunai.myloanservice.loan;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.awantunai.myloanservice.loan.entity.LoanTable;
import com.awantunai.myloanservice.loan.loanRepo.loanRepo;
import com.awantunai.myloanservice.loan.loanService.loanService;


/**
 * Created by arief@awantunai.com on 2019-10-08
 */
@RequestMapping("/loans")
@RestController
public class LoanController {
	
	@Autowired
	loanService service;
	
	@Autowired
	loanRepo loanRepo1;
	
	
	LoanTable temptable;
	
	@PostMapping(value = "/getId")
    public ResponseEntity getId() {
        return ResponseEntity.ok(loanRepo1.getObj());
    }
	
	@PostMapping(value = "/getAllLoans")
    public List<LoanTable> getAllLoans() {
        return service.getAllLoans();
    }
	
	@PostMapping(value = "/createLoan")
    public LoanTable createLoan(@RequestBody LoanTable loanTable) {
		
        return service.createLoan(loanTable);
    }
	
	@RequestMapping(value = "/getPaginatedLoan/{pageIndex}/{pageSize}")
    public List<LoanTable> getPaginatedLoan(@PathVariable int pageIndex,@PathVariable int pageSize) {
		
		Pageable pageAble = PageRequest.of(pageIndex, pageSize);
		List<LoanTable> listOfPaginatedLoans =  loanRepo1.getByPageRequest(pageAble);
		
		LoanTable tempLoanObj = null;
		for(int i=0 ; i<listOfPaginatedLoans.size() ; i++) 
		{
			tempLoanObj = listOfPaginatedLoans.get(i);
			if(tempLoanObj.getDueDate() != null) {
				Date todaysDate = new Date(); 
				if(todaysDate.after(tempLoanObj.getDueDate()) ) {
					int differenceInDays = (int) (todaysDate.getTime() - tempLoanObj.getDueDate().getTime())/(1000*60*60*24);
					
					listOfPaginatedLoans.get(i).setPdpValue(differenceInDays);
					
				}
				else {
					
					listOfPaginatedLoans.get(i).setPdpValue(0);
				}
			}
		}
		
        return listOfPaginatedLoans;
    }
}
