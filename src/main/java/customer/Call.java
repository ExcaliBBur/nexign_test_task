package customer;


public class Call {

    private String callType;
    private String startTime;
    private String endTime;
    private Long durationInMs;
    private Double cost;

    public String getCallType() {
        return callType;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Long getDurationInMs() {
        return durationInMs;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDurationInMs(Long durationInMs) {
        this.durationInMs = durationInMs;
    }

    public Call(String callType, String startTime, String endTime, Long durationInMs, Double cost) {
        this.callType = callType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationInMs = durationInMs;
        this.cost = cost;
    }

}
