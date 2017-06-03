package com.example.gek.weahtergek.models;

/**
 * Created by gek on 03.06.17.
 */

public class Country {
    private String id;
    private String localizedName;
    private String englishName;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
}

