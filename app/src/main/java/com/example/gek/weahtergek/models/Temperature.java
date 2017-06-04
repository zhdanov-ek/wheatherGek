package com.example.gek.weahtergek.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gek on 04.06.17.
 */

public class Temperature {
    @SerializedName("Metric")
    @Expose
    private TempFormat metric;

    @SerializedName("Imperial")
    @Expose
    private TempFormat imperial;

    public TempFormat getMetric() {
        return metric;
    }
    public void setMetric(TempFormat metric) {
        this.metric = metric;
    }

    public TempFormat getImperial() {
        return imperial;
    }
    public void setImperial(TempFormat imperial) {
        this.imperial = imperial;
    }
}
