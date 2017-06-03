package com.example.gek.weahtergek.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gek on 03.06.17.
 */

public class City {
    @SerializedName("Key")
    @Expose
    private String key;

    @SerializedName("LocalizedName")
    @Expose
    private String localizedName;

    @SerializedName("EnglishName")
    @Expose
    private String englishName;

    @SerializedName("Country")
    @Expose
    private Country country;

    public City() {
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getLocalizedName() {
        return localizedName;
    }
    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getEnglishName() {
        return englishName;
    }
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }
}
