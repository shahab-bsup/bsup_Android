package tk.medlynk.patient.android.Activity.StartQuestionSet;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.neweraandroid.demo.R;

public class StartAppointmentActivity extends AppCompatActivity {

    View parent, toolbar_view;
    StartQuestionSetViewHolder viewHolder;
    TextView toolbar_text;
    ImageView menuManagement,menuHelp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_start_appointment );
        parent = findViewById ( R.id.parent );
        viewHolder = new StartQuestionSetViewHolder ( parent );
        toolbar_view = findViewById ( R.id.toolbar_start_question_set );

        toolbar_text = toolbar_view.findViewById ( R.id.toolbar_title );
        toolbar_text.setText ( "Question Set!" );

        menuManagement=toolbar_view.findViewById(R.id.imgMenuManagement);
        menuManagement.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                MenuBuilder menuBuilder =new MenuBuilder(StartAppointmentActivity.this);
                MenuInflater inflater = new MenuInflater(StartAppointmentActivity.this);
                inflater.inflate(R.menu.menu_management,menuBuilder);

                MenuPopupHelper menuPopupHelper= new MenuPopupHelper(
                 StartAppointmentActivity.this, menuBuilder, v);
                menuPopupHelper.setForceShowIcon(true);
                menuPopupHelper.show();

                menuBuilder.findItem(R.id.action_Profile).setTitle("Reza Taghizadeh");
                menuBuilder.setCallback(new MenuBuilder.Callback() {
                    public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.action_Profile:{
                                Toast.makeText(getApplication(),"Profile",Toast.LENGTH_SHORT).show();
                                break;}
                            case R.id.action_tutorialVideo:{break;}
                            case R.id.action_rateApp:{break;}
                            case R.id.action_appointments:{break;}
                            case R.id.action_signOut:{break;}
                        }
                        return false;
                    }

                    public void onMenuModeChange(MenuBuilder menu) {

                    }
                });
            }
        });

        menuHelp=toolbar_view.findViewById(R.id.imgMenuHelp);
        menuHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu= new PopupMenu(StartAppointmentActivity.this,v);
                popupMenu.inflate(R.menu.menu_help);
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.action_FAQ:{
                                Toast.makeText(getApplication(),"FAQ",Toast.LENGTH_SHORT).show();
                                break;}
                            case R.id.action_help:{break;}
                        }
                        return false;
                    }
                });
            }
        });

      /*  backButton = toolbar_view.findViewById ( R.id.imgBackButton );
        backButton.setVisibility ( View.GONE );
        backButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                onBackPressed ();
            }
        } );*/
    }
}