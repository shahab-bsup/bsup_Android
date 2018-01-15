package com.neweraandroid.demo.Model;

/**
 * Created by Shahab on 1/16/2018.
 */

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Errors implements Serializable
{

    @SerializedName("email")
    @Expose
    private List<String> email = null;
    private final static long serialVersionUID = -6759493907826777711L;

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

}
