package customer;

import comparator.DateComparator;

import java.util.*;

public class Customer {
    private final String tariff;
    private int totalMinutes = 0;
    private boolean isUnlimitedActive = true;
    private ArrayList<Call> calls = new ArrayList<>();

    public Customer(String tariff) {
        this.tariff = tariff;
    }

    public void addData(String callType, String startTime, String endTime, Long duration, double cost) {
        calls.add(new Call(callType, startTime, endTime, duration, cost));
        calls.sort(new DateComparator());
    }
    public ArrayList<Call> getCalls() {
        return calls;
    }

    public String getTariff() {
        return tariff;
    }

    public int getTotalMinutes() {
        return totalMinutes;
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
