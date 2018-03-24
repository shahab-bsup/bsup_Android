package tk.medlynk.patient.android.Essentials;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 3/24/2018.
 */

public class ForcedDialogBuilder extends AppCompatDialog {
    private Button doneButton;
    private AppCompatEditText other_input;
    private ForcedDialogBuilder.OnOtherDialogListener onOtherDialogListener;

    public ForcedDialogBuilder(Context context) {
        super ( context );
        init();
    }

    private void init() {
        setContentView ( R.layout.forced_other_layout );
        doneButton = findViewById ( R.id.btnDone );
        doneButton.setOnClickListener ( new OnDOneClickListener () );
        doneButton.setEnabled ( false );
        other_input = findViewById ( R.id.other_edit_text );
        other_input.addTextChangedListener ( new ForcedDialogBuilder.OnOtherTextChangeWatcher () );
        setCancelable ( false );
    }

    public ForcedDialogBuilder(Context context, int theme) {
        super ( context, theme );
        init (  );
    }


    public void setOnOtherDialogListener(ForcedDialogBuilder.OnOtherDialogListener onOtherDialogListener) {
        this.onOtherDialogListener = onOtherDialogListener;
    }

    protected ForcedDialogBuilder(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super ( context, cancelable, cancelListener );
        System.out.println ( "ForcedDialogBuilder.ForcedDialogBuilder" );
    }

    @Override
    public void show() {
        super.show ();
        System.out.println ( "ForcedDialogBuilder.show" );
    }

    public String getOtherText(){
        return other_input.getText ().toString ();
    }

    private class OnOtherTextChangeWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if( editable.length () == 0 ){
                doneButton.setEnabled ( false );
                doneButton.setBackgroundResource ( R.drawable.disable_next_question );
            }else{
                doneButton.setEnabled ( true );
                doneButton.setBackgroundResource ( R.drawable.enable_next_question );
            }
        }
    }

    private class OnCancelClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            onOtherDialogListener.onOtherDialogDone ( "" );
            dismiss ();
        }
    }

    private class OnDOneClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            onOtherDialogListener.onOtherDialogDone ( getOtherText () );
            dismiss ();
        }
    }

    public interface OnOtherDialogListener{
        void onOtherDialogDone(String otherText);
    }
}
