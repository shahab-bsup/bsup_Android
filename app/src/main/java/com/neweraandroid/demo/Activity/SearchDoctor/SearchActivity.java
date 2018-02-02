package com.neweraandroid.demo.Activity.SearchDoctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.neweraandroid.demo.Activity.Login.OnGetCurrentUserInfoListener;
import com.neweraandroid.demo.Constants;
import com.neweraandroid.demo.CustomViews.SnackController;
import com.neweraandroid.demo.Essentials.SharedPreferenceManager;
import com.neweraandroid.demo.Model.CurrentUserResponse;
import com.neweraandroid.demo.Networking.MedlynkRequests;
import com.neweraandroid.demo.R;

public class SearchActivity extends AppCompatActivity implements OnGetCurrentUserInfoListener
, View.OnClickListener{

    View parent_view;
    SearchActivityViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_doctor);
        parent_view = findViewById ( R.id.parent_search_activity );
        viewHolder = new SearchActivityViewHolder ( parent_view );
        MedlynkRequests.getCurrentUserInfo ( this, this );
    }

    @Override
    public void onGetCurrentUserInfoSuccess(CurrentUserResponse currentUserResponse) {
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager ( SearchActivity.this );
        currentUserResponse.setCurrentUserInfo ( currentUserResponse.getCurrentUserInfo () );
        sharedPreferenceManager.setCurrentUser ( currentUserResponse.getCurrentUserInfo () );

    }

    @Override
    public void onGetCurrentUserInfoFailure() {
        //TODO add some code if needed!!!
    }

    @Override
    public void onGetCurrentUserInfoFailure(Constants.EXCEPTION_TYPE exception_type) {
        if( exception_type == Constants.EXCEPTION_TYPE.RETROFIT_EXCEPTION ){
            SnackController.getInstance ().
                    init ( SearchActivity.this, R.string.somethig_went_wrong, Snackbar.LENGTH_LONG )
                    .setAction ( R.string.try_again, SearchActivity.this )
                    .showSnackBar ();
        } else if( exception_type == Constants.EXCEPTION_TYPE.SOCKET_TIMEOUT_EXCEPTION ){
            SnackController.getInstance ().
                    init ( SearchActivity.this, R.string.timeout_exception, Snackbar.LENGTH_LONG )
                    .setAction ( R.string.try_again, SearchActivity.this )
                    .showSnackBar ();
        }
    }

    @Override
    public void onClick(View view) {

    }
}
