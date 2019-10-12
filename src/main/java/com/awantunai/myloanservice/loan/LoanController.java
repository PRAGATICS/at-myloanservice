package com.awantunai.myloanservice.loan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.awantunai.myloanservice.loan.entity.Counter;
import com.awantunai.myloanservice.loan.entity.CounterRepository;
import com.awantunai.myloanservice.loan.entity.Loan;
import com.awantunai.myloanservice.loan.entity.LoanRepository;

/**
 * Created by arief@awantunai.com on 2019-10-08
 */
@RequestMapping("/")
@RestController
public class LoanController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final LoanRepository loanRepository;

	private final CounterRepository counterRepository;

	public LoanController(LoanRepository loanRepository, CounterRepository counterRepository) {
		this.loanRepository = loanRepository;
		this.counterRepository = counterRepository;
	}

	@RequestMapping(value = "/getloans", method = RequestMethod.GET, produces = "application/json")
	public List<Loan> getAllLoans() {
		System.out.println("-------------------------------");
		LOG.info("Getting all users.");
		return loanRepository.findAll();
	}

	@RequestMapping(value = "/createloan", method = RequestMethod.POST, produces = "application/json")
	public Loan createLoan(@RequestBody Loan loan) {
		System.out.println("-------------------------------");
		LOG.info("Create Loan.");
		System.out.println(counterRepository.findAll());
		Optional<Counter> test = counterRepository.findById("anything");
		System.out.println("counterDetails - " + test);
		return loanRepository.save(loan);
	}

	// API for getting loans with pagination with DPD field
	@RequestMapping(value = "/getpaginatedloans/{pageindex}/{pagesize}", method = RequestMethod.GET, produces = "application/json")
	public List<Loan> getPaginatedLoans(@PathVariable int pageindex, @PathVariable int pagesize) throws ParseException {
		System.out.println("-------------inside pagination method------------------");
		// 0 is the page index whereas 3 is the size of each page
		PageRequest firstPageRequest = PageRequest.of(pageindex, pagesize);
		List<Loan> listOfPaginatedLoans = loanRepository.findAll(firstPageRequest).getContent();
		for (int i = 0; i < listOfPaginatedLoans.size(); i++) {
			String todaysdate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			// Parsing the date
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			if (listOfPaginatedLoans.get(i).getDueDate() != null) {
				Date dateBefore = myFormat.parse(listOfPaginatedLoans.get(i).getDueDate().toString());
				Date dateAfter = myFormat.parse(todaysdate);
				long difference = dateAfter.getTime() - dateBefore.getTime();
				float daysBetween = (difference / (1000 * 60 * 60 * 24));
				listOfPaginatedLoans.get(i).setDpd(daysBetween);
			} else {
				listOfPaginatedLoans.get(i).setDpd(0);
			}
		}
		return listOfPaginatedLoans;
	}
}
