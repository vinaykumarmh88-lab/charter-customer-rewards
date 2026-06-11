package com.charter.rewards.model;

public class MonthlyPointsSummary {

    private String month;
    private long rewardPoints;

    public MonthlyPointsSummary() {
    }

    public MonthlyPointsSummary(String month, long rewardPoints) {
        this.month = month;
        this.rewardPoints = rewardPoints;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public long getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(long rewardPoints) {
        this.rewardPoints = rewardPoints;
    }
}