package com.viswanth.covid19Tracker.pojo;

public class Datapojo {


    private String state;
    private String country;
    private String county;
    private int infected_people;

    public int getChangeFromPreviousDay() {
        return changeFromPreviousDay;
    }

    public void setChangeFromPreviousDay(int changeFromPreviousDay) {
        this.changeFromPreviousDay = changeFromPreviousDay;
    }

    private int changeFromPreviousDay;
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setInfected_people(int infected_people) {
        this.infected_people = infected_people;
    }

    public String getCountry() {
        return country;
    }

    public int getInfected_people() {
        return infected_people;
    }

    @Override
    public String toString() {
        return "Datapojo{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", county='" + county + '\'' +
                ", infected_people=" + infected_people +
                ", changeFromPreviousDay=" + changeFromPreviousDay +
                '}';
    }
}
