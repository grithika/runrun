package com.lappy.loginapp;

public class RunActivityRetriever {

    private String TimeElapsed;
    private String TotalDistance;
    private String date;
    private String Id;

    public RunActivityRetriever(String elapsed_time, String total_distance, String date, String Id) {
        this.setElapsed_time(elapsed_time);
        this.setTotal_distance(total_distance);
        this.setDate(date);
        this.setId(Id);
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getElapsed_time() {
        return TimeElapsed;
    }

    public void setElapsed_time(String elapsed_time) {
        TimeElapsed = elapsed_time;
    }

    public String getTotal_distance() {
        return TotalDistance;
    }

    public void setTotal_distance(String total_distance) {
        TotalDistance = total_distance;
    }
}
