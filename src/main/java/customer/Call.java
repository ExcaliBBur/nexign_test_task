package customer;


/**
 * Class to describe call information
 */
public class Call {

    private String callType;
    private String startTime;
    private String endTime;
    private Long durationInMs;
    private Double cost;

    /**
     * Method for getting call type
     * @return call type
     */
    public String getCallType() {
        return callType;
    }

    /**
     * Method for getting start time
     * @return call start time
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Method for getting end time
     * @return call end time
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Method for getting duration in milliseconds
     * @return duration in milliseconds
     */
    public Long getDurationInMs() {
        return durationInMs;
    }

    /**
     * Method for getting cost
     * @return cost
     */
    public Double getCost() {
        return cost;
    }

    /**
     * Method for setting cost
     * @param cost cost
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }

    /**
     * Method for setting call type
     * @param callType call type
     */
    public void setCallType(String callType) {
        this.callType = callType;
    }

    /**
     * Method for setting start time
     * @param startTime call start time
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Method for setting end time
     * @param endTime call end time
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Method for setting duration
     * @param durationInMs duration in milliseconds
     */
    public void setDurationInMs(Long durationInMs) {
        this.durationInMs = durationInMs;
    }

    /**
     * Constructor
     * @param callType call type
     * @param startTime call start time
     * @param endTime call end time
     * @param durationInMs call duration in milliseconds
     * @param cost call cost
     */
    public Call(String callType, String startTime, String endTime, Long durationInMs, Double cost) {
        this.callType = callType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationInMs = durationInMs;
        this.cost = cost;
    }

}
