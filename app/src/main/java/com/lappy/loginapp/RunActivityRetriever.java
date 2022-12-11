package com.lappy.loginapp;

public class RunActivityRetriever {

    private String TimeElapsed;
    private String TotalDistance;
    private String date;
    private String ID;

    public RunActivityRetriever(String timeElapsed, String totalDistance, String date, String id) {
        setTimeElapsed(timeElapsed);
        setTotalDistance(totalDistance);
        this.setDate(date);
        setId(id);
    }

    public String getTimeElapsed() {
        return TimeElapsed;
    }

    public void setTimeElapsed(String timeElapsed) {
        TimeElapsed = timeElapsed;
    }

    public String getTotalDistance() {
        return TotalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        TotalDistance = totalDistance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return ID;
    }

    public void setId(String id) {
        ID = id;
    }
}
