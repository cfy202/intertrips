package com.wenjing.entity;

import java.io.Serializable;

public class Iata implements Serializable {
    private String iatacode;

    private String citycode;

    private String cityname;

    private String countrycode;

    private String countryname;

    private String statecode;

    private String statename;

    private String nbrofairports;

    public String getIatacode() {
        return iatacode;
    }

    public void setIatacode(String iatacode) {
        this.iatacode = iatacode;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getStatecode() {
        return statecode;
    }

    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    public String getNbrofairports() {
        return nbrofairports;
    }

    public void setNbrofairports(String nbrofairports) {
        this.nbrofairports = nbrofairports;
    }
}