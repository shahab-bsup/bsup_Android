package tk.medlynk.patient.android.Essentials;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 3/16/2018.
 */

public class DialogueBuilder extends AppCompatDialog {

    private Button doneButton;
    private Button cancelButton;
    private AppCompatEditText other_input;
    private OnOtherDialogListener onOtherDialogListener;
    private TextView promptTextView;

    public DialogueBuilder(Context context,
                           String dialog_type, String initialOtherText) {
        super ( context );
        init(dialog_type, initialOtherText);
    }

    private void init(String dialogue_type, String initialOtherText) {
        setContentView ( R.layout.dialogue_layout);
        promptTextView = findViewById(R.id.promptText);
        if( dialogue_type.equals("other") ){
            promptTextView.setText("Type other in the box");
        }else if ( dialogue_type.equals("blood_pressure") ){
            promptTextView.setText("Type blood pressure in the box");
        }
        doneButton = findViewById ( R.id.btnDone );
        doneButton.setOnClickListener ( new OnDOneClickListener() );
        doneButton.setEnabled ( false );
        cancelButton = findViewById ( R.id.btnCancel );
        cancelButton.setOnClickListener ( new OnCancelClickListener() );
        other_input = findViewById ( R.id.other_edit_text );
        other_input.setText ( initialOtherText );
        other_input.addTextChangedListener ( new OnOtherTextChangeWatcher() );
    }

//    public DialogueBuilder(Context context, int theme) {
//        super ( context, theme );
//        init ( "selected_state", initialOtherText );
//    }


    public void setOnDialogListener(OnOtherDialogListener onOtherDialogListener) {
        this.onOtherDialogListener = onOtherDialogListener;
    }

    protected DialogueBuilder(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super ( context, cancelable, cancelListener );
    }

    @Override
    public void show() {
        super.show ();
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
