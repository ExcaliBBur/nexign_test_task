package comparator;

import customer.Call;

import java.util.Comparator;

public class DateComparator implements Comparator<Call> {
    @Override
    public int compare(Call call1, Call call2) {
        if (!call1.getStartTime().equals(call2.getStartTime()))
            return call1.getStartTime().compareTo(call2.getStartTime());
        else if (!call1.getEndTime().equals(call2.getEndTime()))
            return call1.getEndTime().compareTo(call2.getEndTime());
        else if (!call1.getDurationInMs().equals(call2.getDurationInMs()))
            return call1.getDurationInMs().compareTo(call2.getDurationInMs());
        else
            return call1.getCost().compareTo(call2.getCost());
    }
}
