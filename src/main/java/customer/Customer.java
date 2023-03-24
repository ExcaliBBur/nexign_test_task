package customer;

import comparator.CustomComparator;

import java.util.*;

public class Customer {
    private final String tariff;
    private int totalMinutes = 0;
    private boolean isUnlimitedActive = true;
    ArrayList<Call> calls = new ArrayList<>();

    public Customer(String tariff) {
        this.tariff = tariff;
    }

    public void addData(String callType, String startTime, String endTime, Long duration, double cost) {
        calls.add(new Call(callType, startTime, endTime, duration, cost));
        calls.sort(new CustomComparator());
    }

    public void printData(String phoneNumber) {
        int bound = 78;
        System.out.println("Tariff index: " + tariff);
        for (int i = 0; i < bound; i++) System.out.print("-");
        System.out.println("\nReport for phone number " + phoneNumber + ":");
        for (int i = 0; i < bound; i++) System.out.print("-");
        System.out.printf("\n|%-10s|%-21s|%-21s|%-11s|%-9s|\n",
                "Call type", "     Start time", "      End time", "  Duration", "   Cost");
        for (int i = 0; i < bound; i++) System.out.print("-");

        double totalCost = 0;

        for (Call call : calls) {
            long duration = call.getDurationInMs();
            call.setCost(calculateCost(call.getCallType(), duration));
            long diffSeconds = duration / 1000 % 60;
            long diffMinutes = duration / (60 * 1000) % 60;
            long diffHours = duration / (60 * 60 * 1000);
            System.out.printf("\n|%-10s|%-21s|%-21s| %02d:%02d:%02d %1s|%-9s|", "    " + call.getCallType(),
                    " " + call.getStartTime(), " " + call.getEndTime()
                    , diffHours, diffMinutes, diffSeconds, "", "   " + call.getCost());
            totalCost += call.getCost();
        }

        System.out.println();
        for (int i = 0; i < bound; i++) System.out.print("-");

        // Для безлимитного тарифа минимальная цена - 100
        if (tariff.equals("06")) totalCost += 100;

        System.out.printf("\n|%52s: |%20s |\n", "Total Cost", totalCost + " rubles");
        for (int i = 0; i < bound; i++) System.out.print("-");
    }

    public double calculateCost(String callType, long duration) {
        long diffMinutes = duration / (60 * 1000);
        double cost = 0;
        switch (tariff) {
            case "06":
                while (diffMinutes > 0) {
                    if (isUnlimitedActive) {
                        diffMinutes--;
                        totalMinutes++;
                        if (totalMinutes >= 300) isUnlimitedActive = false;
                    } else {
                        cost += diffMinutes;
                        diffMinutes = 0;
                    }
                }
                break;
            case "03":
                cost += diffMinutes * 1.5;
                break;
            case "11":
                if (callType.equals("01")) {
                    while (diffMinutes > 0) {
                        if (totalMinutes < 100) {
                            cost += 0.5;
                            diffMinutes--;
                            totalMinutes++;
                        } else {
                            cost += diffMinutes * 1.5;
                            diffMinutes = 0;
                        }
                    }
                }
                break;
        }
        return cost;
    }
}
