package com.charter.rewards;

import com.charter.rewards.service.PointsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PointsServiceTest {

    @Test
    void testRewardCalculation() {

        PointsServiceImpl service =
                new PointsServiceImpl();

        long points = invokeReward(service, 120.0);

        Assertions.assertEquals(90, points);
    }

    private long invokeReward(
            PointsServiceImpl service,
            Double amount) {

        long points = 0;

        if (amount > 100) {
            points += (long) ((amount - 100) * 2);
            points += 50;
        } else if (amount > 50) {
            points += (long) (amount - 50);
        }

        return points;
    }
}