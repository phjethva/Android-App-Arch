package com.phjethva.mvvm_rx.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WondersResponse {

    @SerializedName("status")
    @Expose
    public int status;

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("data")
    @Expose
    public List<DataWonders> dataWonders;

    public static class DataWonders {

        @SerializedName("id")
        @Expose
        public int id;

        @SerializedName("name")
        @Expose
        public String name;

        @SerializedName("country")
        @Expose
        public String country;

        @SerializedName("year")
        @Expose
        public String year;

        @SerializedName("thumbnail-url")
        @Expose
        public String thumbnailUrl;

        @SerializedName("image-url")
        @Expose
        public String imageUrl;

        @SerializedName("location")
        @Expose
        public DataLocations dataLocations;

        public class DataLocations {
            @SerializedName("latitude")
            @Expose
            double latitude;

            @SerializedName("longitude")
            @Expose
            double longitude;

        }

    }

}
