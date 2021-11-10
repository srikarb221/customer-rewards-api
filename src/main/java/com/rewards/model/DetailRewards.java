package com.rewards.model;

import java.time.LocalDate;

public class DetailRewards {
    private LocalDate date;
    private Integer amount;
    private Integer rewards;
    
    
	public DetailRewards(LocalDate date, Integer amount, Integer rewards) {
		super();
		this.date = date;
		this.amount = amount;
		this.rewards = rewards;
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

	@Override
	public String toString() {
		return "DetailRewards [date=" + date + ", amount=" + amount + ", rewards=" + rewards + "]";
	}
	
	
}
