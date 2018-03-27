package tk.medlynk.patient.android.Essentials;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neweraandroid.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by faranegar on 3/27/18.
 */

public class PercentageDialogueBuilder extends AppCompatDialog {

    private RecyclerView recyclerView;
    private OnPercenatgeClickListener onPercenatgeClickListener;

    public PercentageDialogueBuilder(Context context) {
        super(context);
        init(context);
    }

    public void setOnPercenatgeClickListener(OnPercenatgeClickListener onPercenatgeClickListener) {
        this.onPercenatgeClickListener = onPercenatgeClickListener;
    }

    private void init(Context context) {
        setContentView(R.layout.percentages);
        recyclerView = findViewById(R.id.recycler_view_percentages);
        recyclerView.setAdapter(new PercenategAdapter(context));
        setCancelable(false);
    }

    public PercentageDialogueBuilder(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    public interface OnPercenatgeClickListener{
        void onPercentageClicked(int s);
    }

    @Override
    public void show() {
        super.show();
    }

    protected PercentageDialogueBuilder(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
    private class PercenategAdapter extends RecyclerView.Adapter<PercenategAdapter.PercentageViewHolder>{

        private Context context;
        private List<String> strings = new ArrayList<>();

        public PercenategAdapter(Context context) {
            this.context = context;
            for (int i = 1; i <= 100; i++) {
                strings.add(new String("%" + i));
            }
        }

        @Override
        public PercentageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.percentage_row, parent, false);
            return new PercentageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(PercentageViewHolder holder, int position) {
            holder.textView.setText(strings.get(position));
        }

        @Override
        public int getItemCount() {
            return strings.size();
        }

        public class PercentageViewHolder extends RecyclerView.ViewHolder{

            TextView textView;

            public PercentageViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.percentage_text);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onPercenatgeClickListener.onPercentageClicked(getAdapterPosition());
                        dismiss();
                    }
                });
            }
        }
    }
}
