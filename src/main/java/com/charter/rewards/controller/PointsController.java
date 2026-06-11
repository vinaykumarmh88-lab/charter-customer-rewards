package com.charter.rewards.controller;

import com.charter.rewards.model.CustomerRewardResponse;
import com.charter.rewards.service.PointsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PointsController {

    private final PointsService pointsService;

    public PointsController(PointsService pointsService) {
        this.pointsService = pointsService;
    }

    @GetMapping("/api/rewards")
    public List<CustomerRewardResponse> getRewards() {
        return pointsService.calculateRewards();
    }
}