package com.bsuperiorsystem.doctor_app.Activity.Login;

import android.databinding.BaseObservable;

/**
 * Created by Shahab on 1/7/2018.
 */

public class BindableString extends BaseObservable {
    private String value;

    public String get(){
        return value!=null ? value : "";
    }
    public void set( String value ){
        if( !this.value.equals(value) ){
            this.value = value;
            notifyChange();
        }
    }
}
