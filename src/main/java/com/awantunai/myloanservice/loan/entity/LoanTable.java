package com.awantunai.myloanservice.loan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;




@Entity
@Table(name = "temp_loans")
public class LoanTable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sno")
	private int sno;
	
	
	@Column(name = "Id")
	private String id;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "duedate")
	private Date duedate;
	
	@Column(name = "paiddate")
	private Date paiddate;
	
	@Transient
	private int pdp;

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDueDate() {
		return this.duedate;
	}

	public void setDueDate(Date duedate) {
		this.duedate = duedate;
	}

	public Date getPaidDate() {
		return this.paiddate;
	}

	public void setPaidDate(Date paiddate) {
		this.paiddate = paiddate;
	}
	
	public int getPdp() {
		return this.pdp;
	}

	public void setPdpValue(int pdp) {
		this.pdp = pdp;
	}
	
}
