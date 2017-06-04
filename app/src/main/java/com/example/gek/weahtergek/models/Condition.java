package com.example.gek.weahtergek.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gek on 03.06.17.
 */

public class Condition {
    @SerializedName("WeatherText")
    @Expose
    private String weatherText;

    @SerializedName("WeatherIcon")
    @Expose
    private int weatherIcon;

    @SerializedName("Temperature")
    @Expose
    private Temperature temperature;

    @SerializedName("MobileLink")
    @Expose
    private String mobileLink;

    public String getWeatherText() {
        return weatherText;
    }
    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    public int getWeatherIcon() {
        return weatherIcon;
    }
    public void setWeatherIcon(int weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public Temperature getTemperature() {
        return temperature;
    }
    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public String getMobileLink() {
        return mobileLink;
    }
    public void setMobileLink(String mobileLink) {
        this.mobileLink = mobileLink;
    }
}
