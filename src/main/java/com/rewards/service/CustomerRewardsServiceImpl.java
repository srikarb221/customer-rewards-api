package com.rewards.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rewards.exception.CustomerRewardsException;
import com.rewards.model.CustomerRewards;
import com.rewards.model.CustomerTransaction;
import com.rewards.model.DetailRewards;
import com.rewards.model.DetailSummary;
import com.rewards.model.MonthlyRewards;


@Service
public class CustomerRewardsServiceImpl implements CustomerRewardsService {
	
	@Value("${transaction.data.file.path}")
	private String transactionDataPath;
	
	@Value("${rewards.for.transaction.over.hundred}")
	private Integer pointsForOverHundred;
	
	@Value("${rewards.for.transaction.over.fifty}")
	private Integer pointsForOverFifty;
	
	
	private Map<String, List<CustomerRewards>> rewardsMap = new HashMap<>();

	
	public Integer calculateRewards(Integer amount) {
		
		int rewardPoints = 0;
		int amountOverHundred = amount - 100;
		int amountOverFifty = amount - 50 - (amountOverHundred > 0 ? amountOverHundred : 0);
		
		if (amount > 100 && amountOverHundred > 0) {
			rewardPoints = amountOverHundred * pointsForOverHundred; 
		} 
		
		if (amount > 50 && amountOverFifty > 0) {
			rewardPoints += amountOverFifty * pointsForOverFifty;
		} 
		
		return rewardPoints;
	}
	
	
	public void populateCustomerMonthlyData() throws CustomerRewardsException {
		
		ObjectMapper mapper = new ObjectMapper();

		try {
			TypeReference<List<CustomerTransaction>> typeRef = new TypeReference<List<CustomerTransaction>>() { };
			List<CustomerTransaction> transactions = mapper.readValue(new File(transactionDataPath), typeRef);
					
			
			rewardsMap = transactions.stream()
					.map((reward) -> new CustomerRewards(reward.getCustomerId(), reward.getCustomerName(), reward.getLocalDate(), reward.getAmount(), this.calculateRewards(reward.getAmount())))
					.collect(
							Collectors.groupingBy(CustomerRewards::getMonth)
					);
		} catch (Exception e) {
		    throw new CustomerRewardsException("Error occured while populating Rewards Data.");
		}
	}
	
	public List<MonthlyRewards> getCustomerMonthlySummaryData() throws CustomerRewardsException {
		
		List<MonthlyRewards> rewardsSummary = new ArrayList<MonthlyRewards>();
		
		try {
			this.populateCustomerMonthlyData();

		
			List<String> months = new ArrayList<String>(rewardsMap.keySet());
			
			months.forEach((monthName) -> {
				Map<Integer, MonthlyRewards> monthlyMap = new HashMap<>();
				
				List<CustomerRewards> custRewards = rewardsMap.get(monthName);
				
				custRewards.forEach((custReward) -> {
					if (monthlyMap.get(custReward.getCustomerId()) != null) {
						MonthlyRewards oldRecord = monthlyMap.get(custReward.getCustomerId());
						MonthlyRewards record = new MonthlyRewards(custReward.getCustomerId(),custReward.getCustomerName(), 
								oldRecord.getMonth(), custReward.getAmount() + oldRecord.getAmount(),
								custReward.getRewards()+oldRecord.getRewards());
						monthlyMap.put(custReward.getCustomerId(), record);
					} else {	
						String monthstr = String.valueOf(custReward.getDate().getMonthValue());
						MonthlyRewards reward = new MonthlyRewards(custReward.getCustomerId(), custReward.getCustomerName(),
								monthstr, custReward.getAmount(), custReward.getRewards());
						monthlyMap.put(custReward.getCustomerId(), reward);
					}
				});
				
				rewardsSummary.addAll(monthlyMap.values().stream().collect(Collectors.toList()));
	
			});
		} catch (Exception e) {
			throw new CustomerRewardsException("Error occured while fetching Monthly Summary Rewards Data.");
		}
		
		return rewardsSummary;
	}
	
	
	public DetailSummary getCustomerMonthlyDetailData(int customerId, String month) throws CustomerRewardsException {
		
		DetailSummary detailSummary = null;
		
		if (rewardsMap.isEmpty()) {
			this.populateCustomerMonthlyData();
		}

		if (rewardsMap.get(month.toUpperCase()) != null) {				
			List<DetailRewards> listDetail = rewardsMap.get(month.toUpperCase()).stream()
					.filter((reward) -> reward.getCustomerId().equals(customerId))
					.map((reward) -> new DetailRewards(reward.getDate(), reward.getAmount(), reward.getRewards()))
					.collect(Collectors.toList());
			
			detailSummary = new DetailSummary(listDetail,
					listDetail.stream().reduce(0, (acc, reward) -> acc + reward.getAmount(), Integer::sum),
					listDetail.stream().reduce(0, (acc, reward) -> acc + reward.getRewards(), Integer::sum)
					);
		} else {
			throw new CustomerRewardsException(String.format("No Data found for Customer %s.", customerId));		
		}
		
		return detailSummary;
		
	}
	
}
