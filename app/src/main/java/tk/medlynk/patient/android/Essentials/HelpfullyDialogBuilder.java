package tk.medlynk.patient.android.Essentials;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.widget.Button;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 3/24/2018.
 */

public class HelpfullyDialogBuilder extends AppCompatDialog implements ViewSelection.OnSingleItemSelectedListener {

    private ViewSelection helpfully_options;
    private Button button_next;
    private Button button_skip;
    int selected_choice = -1;
    private String[] strings;
    private OnHelpfullyDialogListener helpfullyDialogListener;

    public HelpfullyDialogBuilder(Context context) {
        super ( context );
        init(context);
    }

    private void init(Context context) {
        setContentView ( R.layout.helpfully_dialog );
        helpfully_options = findViewById ( R.id.helpfully_ViewSelection );
        helpfully_options.setOnSingleItemSelectedListener ( this );
        button_next = findViewById ( R.id.btnNextQuestion );
        button_next.setOnClickListener ( new OnNextClickListener() );
        button_next.setEnabled ( false );
        button_skip = findViewById ( R.id.btnSkipQuestion );
        button_skip.setOnClickListener ( new OnSkipClickListener() );
        strings = context.getResources ().getStringArray ( R.array.helpfully_strings );
//        for (int i = 0; i < helpfully_options.getNumberOfViews (); i++) {
////            helpfully_options.setTextToButtons ( strings[i], i );
//        }
    }

    public void setOnHelpfullyDialogListener(OnHelpfullyDialogListener helpfullyDialogListener) {
        this.helpfullyDialogListener = helpfullyDialogListener;
    }

    protected HelpfullyDialogBuilder(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super ( context, cancelable, cancelListener );
    }

    @Override
    public void show() {
        super.show ();
    }

    @Override
    public void onSingleItemSelected(View view, int i) {
        System.out.println ( "HelpfullyDialogBuilder.onSingleItemSelected" );
        if( i == -1 ){
            button_next.setEnabled ( false );
            button_next.setBackgroundResource ( R.drawable.disable_next_question );
        }else{
            selected_choice = i;
            button_next.setEnabled ( true );
            button_next.setBackgroundResource ( R.drawable.enable_next_question );
        }
    }


    private class OnNextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "HelpfullyDialogBuilder" );
            System.out.println ( "OnNextClickListener.onClick" );
            helpfullyDialogListener.onDialogDone ( selected_choice + 1 );
            dismiss ();
        }
    }

    private class OnSkipClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "HelpfullyDialogBuilder" );
            System.out.println ( "OnSkipClickListener.onClick" );
            dismiss ();
            helpfullyDialogListener.onDialogDone ( -1 );
        }
    }

    public interface OnHelpfullyDialogListener{
        void onDialogDone(int helpfully_code);
    }

}
