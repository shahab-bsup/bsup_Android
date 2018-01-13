package com.neweraandroid.demo.Activity.SendResetPasswordRequestActivity;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Shahab on 1/14/2018.
 */

public class SendResetPasswordViewHolder extends RecyclerView.ViewHolder {

    public SendResetPasswordViewHolder(View itemView) {
        super ( itemView );
    }

    interface DateSelectedListener{
        void onDateSelected(int year, int month, int day);
    }
}
