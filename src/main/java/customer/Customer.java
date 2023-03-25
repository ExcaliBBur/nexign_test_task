package customer;

import comparator.DateComparator;

import java.util.*;

/**
 * Class to describe the customer
 */
public class Customer {
    private final String tariff;
    private int totalMinutes = 0;
    private boolean isUnlimitedActive = true;
    private ArrayList<Call> calls = new ArrayList<>();

    /**
     * Constructor
     * @param tariff customer tariff
     */
    public Customer(String tariff) {
        this.tariff = tariff;
    }

    /**
     * Method for adding call to list
     * @param callType call type
     * @param startTime start time
     * @param endTime end time
     * @param duration duration
     * @param cost cost
     */
    public void addData(String callType, String startTime, String endTime, Long duration, double cost) {
        calls.add(new Call(callType, startTime, endTime, duration, cost));
        calls.sort(new DateComparator());
    }

    /**
     * Method for getting calls
     * @return current customer list of calls
     */
    public ArrayList<Call> getCalls() {
        return calls;
    }

    /**
     * Method for getting tariff
     * @return current customer tariff
     */
    public String getTariff() {
        return tariff;
    }

    /**
     * Method for getting total spent minutes
     * @return minutes that the client has already spent
     */
    public int getTotalMinutes() {
        return totalMinutes;
    }

    /**
     * Method to calculate cost
     * @param callType call type
     * @param duration duration
     * @return cost
     */
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
