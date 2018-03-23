package tk.medlynk.patient.android.Activity.NewSymptom.fragments.Question_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.neweraandroid.demo.R;

/**
 * Created by Shahab on 3/23/2018.
 */

public class TimeLastingAdapter extends BaseAdapter {
    private String[] strings;
    private Context context;

    public TimeLastingAdapter(Context context) {
        this.context = context;
        strings = context.getResources ().getStringArray ( R.array.lasting_choices );
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean isEnabled(int position) {
        if( position == 0 ){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = super.getDropDownView ( position, convertView, parent );
        if( position == 0 ){
            ((TextView)view.findViewById ( R.id.text )).setTextColor ( context.getResources ().getColor ( R.color.colorPrimary ) );
        }

        return view;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from ( context ).inflate ( R.layout.lasting_spinner,
                viewGroup,
                false);
        TextView textView = view.findViewById ( R.id.text );
        textView.setText ( strings[i] );
        return view;
    }
}
