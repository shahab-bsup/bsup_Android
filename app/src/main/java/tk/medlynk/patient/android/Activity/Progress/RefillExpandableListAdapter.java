package tk.medlynk.patient.android.Activity.Progress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import com.neweraandroid.demo.R;

import java.util.List;

import tk.medlynk.patient.android.CustomViews.CustomTextView;

/**
 * Created by admin on 6/22/2018.
 */

public class RefillExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private List<ProgressInfo> expandableListDetail;

    public RefillExpandableListAdapter(Context context,String refillExpandableListTitle,
                                       List<ProgressInfo> refillExpandableDetail ){
        this.context=context;
        this.expandableListTitle.add(refillExpandableListTitle);
        this.expandableListDetail=refillExpandableDetail;
    }

    @Override
    public int getGroupCount() {
        if (this.expandableListDetail.size()>0)
            return 1;
        else
            return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.expandableListDetail.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.expandableListTitle.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.expandableListDetail.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String groupTitle= this.expandableListTitle.get(groupPosition);
        if (convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= layoutInflater.inflate(R.layout.progress_refill_expandablel_group,null);
        }

        CustomTextView groupTitleTextView=(CustomTextView) convertView.findViewById(R.id.refillTitle);
        groupTitleTextView.setText(groupTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandableListTitle= this.expandableListDetail.get(childPosition).getTitle();
        final String expandableListDetails= this.expandableListDetail.get(childPosition).getDetails();

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.progress_item, null);
        }

        CustomTextView expandableListTitleTextView=(CustomTextView)convertView.findViewById(R.id.text_progressItemTitle);
        expandableListTitleTextView.setText(expandableListTitle);

        CustomTextView expandableListDetailTextView=(CustomTextView)convertView.findViewById(R.id.text_progressItemDetail);
        expandableListDetailTextView.setText(expandableListDetails);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
