package utilities;

import customer.Customer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

public class DataManager {
    private final HashMap<String, Customer> customerHashMap = new HashMap<>();
    private final DateFormatter dateFormatter = new DateFormatter();

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

    public void generateReports() throws FileNotFoundException {
        PrintStream out;
        Customer customer;
        for (String phoneNumber : customerHashMap.keySet()) {
            out = new PrintStream(new FileOutputStream("reports/" + phoneNumber + ".txt"));
            System.setOut(out);
            customer = customerHashMap.get(phoneNumber);
            customer.printData(phoneNumber);
        }
    }
}
