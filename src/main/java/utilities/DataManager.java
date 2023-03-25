package utilities;

import comparator.CustomComparator;
import customer.Call;
import customer.Customer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;


/**
 * Class to manage data
 */
public class DataManager {
    private final HashMap<String, Customer> customerHashMap = new HashMap<>();
    private final DateFormatter dateFormatter = new DateFormatter();

    /**
     * Method for parsing data from input line and save in map
     * @param line input line
     */
    public void readData(String line) {
        String[] data = line.split(",");
        String callType = data[0].trim();
        String phoneNumber = data[1].trim();
        String startTime = data[2].trim();
        String endTime = data[3].trim();
        String tariff = data[4].trim();
        Customer customer;

        if (!customerHashMap.containsKey(phoneNumber)) {
            customer = new Customer(tariff);
        } else {
            customer = customerHashMap.get(phoneNumber);
        }
        long duration = dateFormatter.formatToDate(endTime).getTime() -
                dateFormatter.formatToDate(startTime).getTime();

        customer.addData(callType, dateFormatter.formatToString(startTime),
                dateFormatter.formatToString(endTime), duration, 0);
        customerHashMap.put(phoneNumber, customer);
    }

    /**
     * Method for generating reports in reports/ directory
     * Also this method calculate cost for one call
     * @throws FileNotFoundException if reports/ directory not found
     */
    public void generateReports() throws FileNotFoundException {
        PrintWriter out;
        Customer customer;
        for (String phoneNumber : customerHashMap.keySet()) {
            out = new PrintWriter(new FileOutputStream("reports/" + phoneNumber + ".txt"));
            customer = customerHashMap.get(phoneNumber);
            List<Call> calls = customer.getCalls();
            String tariff = customer.getTariff();

            for (Call call : calls) {
                long duration = call.getDurationInMs();
                call.setCost(customer.calculateCost(call.getCallType(), duration));
            }
            calls.sort(new CustomComparator());

            out.println("Tariff index: " + tariff);
            printLine(out);
            out.println("\nReport for phone number " + phoneNumber + ":");
            printLine(out);
            out.printf("\n|%-10s|%-21s|%-21s|%-11s|%-9s|\n",
                    "Call type", "     Start time", "      End time", "  Duration", "   Cost");
            printLine(out);


            double totalCost = 0;

            for (Call call : calls) {
                long duration = call.getDurationInMs();
                long diffSeconds = duration / 1000 % 60;
                long diffMinutes = duration / (60 * 1000) % 60;
                long diffHours = duration / (60 * 60 * 1000);
                out.printf("\n|%-10s|%-21s|%-21s| %02d:%02d:%02d %1s|%-9s|", "    " + call.getCallType(),
                        " " + call.getStartTime(), " " + call.getEndTime()
                        , diffHours, diffMinutes, diffSeconds, "", "   " + call.getCost());
                totalCost += call.getCost();
            }

            out.println();
            printLine(out);

            if (tariff.equals("06")) totalCost += 100;

            out.printf("\n|%52s: |%20s |\n", "Total Cost", totalCost + " rubles");
            printLine(out);
            out.flush();
        }
    }

    /**
     * Method for printing separator line
     * @param out file to print
     */
    private void printLine(PrintWriter out) {
        int bound = 78;
        for (int i = 0; i < bound; i++) out.print("-");
    }
}
