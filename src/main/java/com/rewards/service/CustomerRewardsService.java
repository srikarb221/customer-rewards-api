package com.rewards.service;

import java.util.List;

import com.rewards.exception.CustomerRewardsException;
import com.rewards.model.DetailSummary;
import com.rewards.model.MonthlyRewards;

public interface CustomerRewardsService {
	public List<MonthlyRewards> getCustomerMonthlySummaryData() throws CustomerRewardsException;
	public DetailSummary getCustomerMonthlyDetailData(int customerId, String month) throws CustomerRewardsException;
	public void populateCustomerMonthlyData() throws CustomerRewardsException;
	
}
