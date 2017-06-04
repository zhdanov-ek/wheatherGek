package com.example.gek.weahtergek.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by gek on 03.06.17.
 */

public class Country extends RealmObject{
    @SerializedName("ID")
    @Expose
    private String id;

    @SerializedName("LocalizedName")
    @Expose
    private String localizedName;

    @SerializedName("EnglishName")
    @Expose
    private String englishName;

    public Country() {
    }

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

