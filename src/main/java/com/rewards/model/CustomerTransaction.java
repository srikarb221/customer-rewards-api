package com.rewards.model;

import java.time.LocalDate;

public class CustomerTransaction {

    private Integer customerId;
    private String customerName;
    private String date;
    private Integer amount;
    
	public CustomerTransaction(Integer customerId, String customerName, String date, Integer amount) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.date = date;
		this.amount = amount;
	}
	public CustomerTransaction() {}
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
    public String getMonthFromDate(){
        LocalDate ld = LocalDate.parse(this.date);
        return ld.getMonth().toString();
    }

    public LocalDate getLocalDate(){
        return LocalDate.parse(this.date);
    }
    
	@Override
	public String toString() {
		return "CustomerTransaction [customerId=" + customerId + ", customerName=" + customerName + ", date=" + date
				+ ", amount=" + amount + "]";
	}
    
}
