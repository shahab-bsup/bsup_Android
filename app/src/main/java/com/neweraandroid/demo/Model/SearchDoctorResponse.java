package com.neweraandroid.demo.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Shahab on 1/19/2018.
 */

public class SearchDoctorResponse implements Serializable {

    @SerializedName("data")
    @Expose
    private SearchDoctorResponse searchDoctorResponse;
    private final static long serialVersionUID = -2420979164143381626L;

    public SearchDoctorResponse getSearchDoctorResponse() {
        return searchDoctorResponse;
    }

    public void setSearchDoctorResponse(SearchDoctorResponse searchDoctorResponse) {
        this.searchDoctorResponse = searchDoctorResponse;
    }
}
