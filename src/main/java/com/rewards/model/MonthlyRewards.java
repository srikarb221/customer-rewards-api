package com.rewards.model;

public class MonthlyRewards {
    private Integer customerId;
    private String customerName;
    private String month;
    private Integer amount;
    private Integer rewards;

    
	public MonthlyRewards(Integer customerId, String customerName, String month, Integer amount, Integer rewards) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.month = month;
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
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
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
	@Override
	public String toString() {
		return "MonthlyRewards [customerId=" + customerId + ", customerName=" + customerName + ", month=" + month
				+ ", amount=" + amount + ", rewards=" + rewards + "]";
	}
    
}
