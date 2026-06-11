package com.charter.rewards.model;

import java.util.List;

public class CustomerRewardResponse {

    private Long customerId;
    private String customerName;
    private List<MonthlyPointsSummary> monthlyPointsSummaries;
    private long totalRewards;

    public CustomerRewardResponse() {
    }

    public CustomerRewardResponse(Long customerId,
                                  String customerName,
                                  List<MonthlyPointsSummary> monthlyPointsSummaries,
                                  long totalRewards) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.monthlyPointsSummaries = monthlyPointsSummaries;
        this.totalRewards = totalRewards;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<MonthlyPointsSummary> getMonthlyRewards() {
        return monthlyPointsSummaries;
    }

    public long getTotalRewards() {
        return totalRewards;
    }
}