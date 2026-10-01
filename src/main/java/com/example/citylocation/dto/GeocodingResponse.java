package com.example.citylocation.dto;

public class GeocodingResponse {
    private String lat;
    private String lon;
    private String display_name;

    //latitude getter and setter
    public String getLat(){return lat;}
    public void setLat(String lat){this.lat=lat;}

    //longitude getter and setter
    public String getLon(){return lon;}
    public void setLon(String lon){this.lon=lon;}

    //location name
    public String getDisplay_name(){return display_name;}
    public void setDisplay_name(String display_name){this.display_name=display_name;}
}
