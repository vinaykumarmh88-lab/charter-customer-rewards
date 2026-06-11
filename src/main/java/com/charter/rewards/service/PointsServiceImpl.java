package com.charter.rewards.service;

import com.charter.rewards.exception.InvalidTransactionException;
import com.charter.rewards.model.CustomerRewardResponse;
import com.charter.rewards.model.MonthlyPointsSummary;
import com.charter.rewards.model.PurchaseRecord;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PointsServiceImpl implements PointsService {

    @Override
    public List<CustomerRewardResponse> calculateRewards() {

        List<PurchaseRecord> purchaseRecords = getTransactions();

        Map<Long, List<PurchaseRecord>> customerMap =
                purchaseRecords.stream()
                        .collect(Collectors.groupingBy(PurchaseRecord::getCustomerId));

        List<CustomerRewardResponse> response = new ArrayList<>();

        for (Map.Entry<Long, List<PurchaseRecord>> entry : customerMap.entrySet()) {

            Long customerId = entry.getKey();
            List<PurchaseRecord> customerPurchaseRecords = entry.getValue();

            Map<String, Long> monthlyRewards =
                    customerPurchaseRecords.stream()
                            .collect(Collectors.groupingBy(
                                    t -> Month.of(
                                                    t.getTransactionDate().getMonthValue())
                                            .name(),
                                    Collectors.summingLong(
                                            t -> calculatePoints(
                                                    t.getAmount()))));

            List<MonthlyPointsSummary> monthList = new ArrayList<>();

            long total = 0;

            for (Map.Entry<String, Long> reward : monthlyRewards.entrySet()) {
                monthList.add(
                        new MonthlyPointsSummary(
                                reward.getKey(),
                                reward.getValue()));

                total += reward.getValue();
            }

            response.add(
                    new CustomerRewardResponse(
                            customerId,
                            customerPurchaseRecords.get(0).getCustomerName(),
                            monthList,
                            total));
        }

        return response;
    }

    private long calculatePoints(Double amount) {

        if (amount < 0) {
            throw new InvalidTransactionException(
                    "Amount cannot be negative");
        }

        long points = 0;

        if (amount > 100) {
            points += (long) ((amount - 100) * 2);
            points += 50;
        } else if (amount > 50) {
            points += (long) (amount - 50);
        }

        return points;
    }

    private List<PurchaseRecord> getTransactions() {

        return Arrays.asList(
                new PurchaseRecord(1L, 101L, "Vinay Kumar",
                        120.0, java.time.LocalDate.now().minusMonths(2)),

                new PurchaseRecord(2L, 101L, "Vinay Kumar",
                        75.0, java.time.LocalDate.now().minusMonths(2)),

                new PurchaseRecord(3L, 101L, "Vinay Kumar",
                        200.0, java.time.LocalDate.now().minusMonths(1)),

                new PurchaseRecord(4L, 102L, "Karthik",
                        90.0, java.time.LocalDate.now().minusMonths(1)),

                new PurchaseRecord(5L, 102L, "Karthik",
                        130.0, java.time.LocalDate.now())
        );
    }
}