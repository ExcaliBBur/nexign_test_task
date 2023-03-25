package comparator;

import customer.Call;

import java.util.Comparator;

/**
 * Custom comparator
 */
public class DateComparator implements Comparator<Call> {
    /**
     * The comparison is by calendar order, then by duration, then by cost
     * @param call1 first call to compare
     * @param call2 second call to compare
     * @return call coming first in calendar order
     * If the call start time is the same, then the comparison is based on other parameters
     */
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
