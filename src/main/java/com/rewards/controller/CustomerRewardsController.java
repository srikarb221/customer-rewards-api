package com.rewards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rewards.exception.CustomerRewardsException;
import com.rewards.model.DetailSummary;
import com.rewards.model.MonthlyRewards;
import com.rewards.service.CustomerRewardsService;

@RestController
@RequestMapping(path="/rewards")
public class CustomerRewardsController {
	
	@Autowired
	private CustomerRewardsService rewardsService;
	
	@GetMapping()
	ResponseEntity<List<MonthlyRewards>> getMonthlySummaryData() {
		
		List<MonthlyRewards> response = null;
		
		try {
			response = rewardsService.getCustomerMonthlySummaryData();
		} catch (CustomerRewardsException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
		
		return new ResponseEntity<List<MonthlyRewards>>(response, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/{customerId}/{month}")
	ResponseEntity<DetailSummary> getMonthlyDetailData(@PathVariable int customerId, @PathVariable String month) {
		
		DetailSummary response = null;
		
		try {
			response = rewardsService.getCustomerMonthlyDetailData(customerId, month);
		} catch (CustomerRewardsException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
		return new ResponseEntity<DetailSummary>(response, HttpStatus.OK);
		
	}
	
}
