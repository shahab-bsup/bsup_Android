package tk.medlynk.patient.android.CustomViews;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.neweraandroid.demo.R;

/**
 * Created by Hamid on 7/11/16 AD.
 */

public class SnackController {

    private LayoutInflater mInflater;
    private Snackbar snackbar;
    private TextView textViewTop;
    private String text;

    private static SnackController ourInstance = new SnackController();
    private View snackView;
    private Snackbar.SnackbarLayout layout;

    public static SnackController getInstance() {
        return ourInstance;
    }

    public SnackController init(Context context, String text) {
        snackbar = Snackbar.make(((Activity) context).findViewById(android.R.id.content), "", Snackbar.LENGTH_LONG);
        layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setText ( context.getString ( R.string.no_intenet_connection ) );

//        textView.setVisibility(View.INVISIBLE);

//        mInflater = LayoutInflater.from(context);
//        snackView = mInflater.inflate( R.layout.snack_bar, null);
//        textViewTop = (TextView) snackView.findViewById(R.id.snackTextMessage );
//        textViewTop.setTypeface( Utils.getBoldTypeFace (context));
//        textViewTop.setText(text);
//        textViewTop.setTextColor(Color.WHITE);
//        layout.addView(snackView, 0);
        return this;
    }

    public SnackController init(Context context, int resId, int duration) {
        snackbar = Snackbar.make(((Activity) context).findViewById(android.R.id.content), "", duration);
        layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setText ( context.getString ( resId ) );
        //        textView.setVisibility(View.INVISIBLE);
//
//        mInflater = LayoutInflater.from(context);
//        snackView = mInflater.inflate(R.layout.snack_bar, null);
//        textViewTop = (TextView) snackView.findViewById(R.id.snackTextMessage);
//        textViewTop.setTypeface(Utils.getBoldTypeFace (context));
//        textViewTop.setText(text);
//        textViewTop.setTextColor(Color.WHITE);
//        layout.addView(snackView, 0);
        return this;
    }

    public SnackController init(Context context, String message, int duration) {
        snackbar = Snackbar.make(((Activity) context).findViewById(android.R.id.content), "", duration);
        layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setText ( message );
        //        textView.setVisibility(View.INVISIBLE);
//
//        mInflater = LayoutInflater.from(context);
//        snackView = mInflater.inflate(R.layout.snack_bar, null);
//        textViewTop = (TextView) snackView.findViewById(R.id.snackTextMessage);
//        textViewTop.setTypeface(Utils.getBoldTypeFace (context));
//        textViewTop.setText(text);
//        textViewTop.setTextColor(Color.WHITE);
//        layout.addView(snackView, 0);
        return this;
    }

    public SnackController setAction(int resId, View.OnClickListener listener) {
//        Button action = (Button) snackView.findViewById(R.id.snackbar_action);
//        action.setVisibility(View.VISIBLE);
//        action.setText(text);
//        action.setOnClickListener (listener);
//        layout.invalidate();
        snackbar.setAction ( resId, listener );
        return this;
    }

    public SnackController setAction(String message, View.OnClickListener listener) {
//        Button action = (Button) snackView.findViewById(R.id.snackbar_action);
//        action.setVisibility(View.VISIBLE);
//        action.setText(text);
//        action.setOnClickListener (listener);
//        layout.invalidate();
        snackbar.setAction ( message, listener );
        return this;
    }

    public SnackController setText(String text) {
        this.text = text;
        textViewTop.setText(text);
        return this;
    }

    private SnackController() {

    }

    public void showSnackBar() {
        snackbar.show();
    }

}
