package com.neweraandroid.demo.Activity.Start_a_Refill;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 2/1/2018.
 */

public class Start_A_New_Refill_ViewHolder extends RecyclerView.ViewHolder {

    Button button;
    Start_A_New_Refill_ClickListener clickListener;

    public Start_A_New_Refill_ViewHolder(View itemView) {
        super ( itemView );
        clickListener = new Start_A_New_Refill_ClickListener ( itemView.getContext () );
        button = itemView.findViewById ( R.id.btnStartRefill );
        button.setOnClickListener ( clickListener );
    }
}
