package com.rewards.model;

import java.time.LocalDate;

public class CustomerRewards {
    private Integer customerId;
    private String customerName;
    private LocalDate date;
    private Integer amount;
    private Integer rewards;
    
    
	public CustomerRewards(Integer customerId, String customerName, LocalDate date, Integer amount, Integer rewards) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.date = date;
		this.amount = amount;
		this.rewards = rewards;
	}
	
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
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getRewards() {
		return rewards;
	}
	public void setRewards(Integer rewards) {
		this.rewards = rewards;
	}
    
    public String getMonth() {
    	return String.valueOf(this.date.getMonthValue());
    }

	@Override
	public String toString() {
		return "CustomerRewards [customerId=" + customerId + ", customerName=" + customerName + ", date=" + date
				+ ", amount=" + amount + ", rewards=" + rewards + "]";
	}
    
    
}
