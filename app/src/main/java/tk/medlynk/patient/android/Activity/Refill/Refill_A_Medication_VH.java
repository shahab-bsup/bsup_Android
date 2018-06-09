package tk.medlynk.patient.android.Activity.Refill;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.neweraandroid.demo.R;

public class Refill_A_Medication_VH extends ViewHolder {
    private final ImageView back_button;
    private Refill_A_Medication_ClickListener clickListener;
    private final Toolbar toolbar;
    private final TextView toolbar_text;

    public interface Refill_A_Medication_ClickListener {
        void onBackButtonClicked();
    }

    public Refill_A_Medication_VH(View itemView) {
        super(itemView);
        toolbar =  itemView.findViewById(R.id.refill_toolbar_layout);
        toolbar_text =  toolbar.findViewById(R.id.toolbar_title);
        toolbar_text.setText(itemView.getContext ().getResources().getString(R.string.refill_toolbar_text));
        back_button = this.toolbar.findViewById(R.id.imgBackButton);
        back_button.setOnClickListener(new OnBackButtonClickListener ());
    }

    class OnBackButtonClickListener implements OnClickListener {

        public void onClick(View view) {
            clickListener.onBackButtonClicked();
        }
    }

    public void setClickListener(Refill_A_Medication_ClickListener clickListener) {
        this.clickListener = clickListener;
    }
}