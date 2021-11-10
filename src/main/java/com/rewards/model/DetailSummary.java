package com.rewards.model;

import java.util.List;

public class DetailSummary {
	
	private List<DetailRewards> detailRewards;
	private Integer totalAmount;
	private Integer totalRewards;
	
	
	public DetailSummary(List<DetailRewards> detailRewards, Integer totalAmount, Integer totalRewards) {
		super();
		this.detailRewards = detailRewards;
		this.totalAmount = totalAmount;
		this.totalRewards = totalRewards;
	}
	
	public List<DetailRewards> getDetailRewards() {
		return detailRewards;
	}
	public void setDetailRewards(List<DetailRewards> detailRewards) {
		this.detailRewards = detailRewards;
	}
	public Integer getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Integer getTotalRewards() {
		return totalRewards;
	}
	public void setTotalRewards(Integer totalRewards) {
		this.totalRewards = totalRewards;
	}

	@Override
	public String toString() {
		return "DetailSummary [detailRewards=" + detailRewards + ", totalAmount=" + totalAmount + ", totalRewards="
				+ totalRewards + "]";
	}
	
	
}
