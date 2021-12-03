package com.example.gymmanagementadmin.model;

public class DietChartInfo {
    private String bodyType,dietChartName,dietChartTime;

    public DietChartInfo() {
    }

    public DietChartInfo(String bodyType, String dietChartName, String dietChartTime) {
        this.bodyType = bodyType;
        this.dietChartName = dietChartName;
        this.dietChartTime = dietChartTime;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getDietChartName() {
        return dietChartName;
    }

    public void setDietChartName(String dietChartName) {
        this.dietChartName = dietChartName;
    }

    public String getDietChartTime() {
        return dietChartTime;
    }

    public void setDietChartTime(String dietChartTime) {
        this.dietChartTime = dietChartTime;
    }
}
