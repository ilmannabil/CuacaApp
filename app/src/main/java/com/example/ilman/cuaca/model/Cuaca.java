package com.example.ilman.cuaca.model;

/**
 * Created by ilman on 10/02/2018.
 */

public class Cuaca {
    String status;
    Double tempMax;
    Double tempMin;
    String time;

    public Cuaca(String status, Double tempMax, Double tempMin, String time) {
        this.status = status;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

