package com.example.michaeljeffress.igdbapiexample.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by michaeljeffress on 10/26/16.
 */

public class ReleaseDate {

    @SerializedName("category")
    @Expose
    private Integer category;
    @SerializedName("platform")
    @Expose
    private Integer platform;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("region")
    @Expose
    private Integer region;

    /**
     *
     * @return
     * The category
     */
    public Integer getCategory() {
        return category;
    }

    /**
     *
     * @param category
     * The category
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     *
     * @return
     * The platform
     */
    public Integer getPlatform() {
        return platform;
    }

    /**
     *
     * @param platform
     * The platform
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    /**
     *
     * @return
     * The date
     */
    public Integer getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(Integer date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The region
     */
    public Integer getRegion() {
        return region;
    }

    /**
     *
     * @param region
     * The region
     */
    public void setRegion(Integer region) {
        this.region = region;
    }

}

