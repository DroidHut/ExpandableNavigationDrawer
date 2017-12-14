package com.example.shivani.expandablenavigationdrawer;


import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.HashMap;
import java.util.List;

public class ExpandListAdapter extends BaseExpandableListAdapter {

    private List<Content> parentRecord;
    private HashMap<String, List<String>> childRecord;
    private LayoutInflater inflater = null;
    private Activity mContext;

    public ExpandListAdapter(Activity context, List<Content> parentList, HashMap<String, List<String>> childList) {
        this.parentRecord = parentList;
        this.childRecord = childList;
        mContext = context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        return this.childRecord.get(((Content) getGroup(groupPosition)).getTitle()).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        String childConfig = getChild(groupPosition, childPosition);

        ViewHolder holder;
        try {
            if (convertView == null) {
                holder = new ViewHolder();

                convertView = inflater.inflate(R.layout.list_child, null);
                holder.childTitle = (TextView) convertView.findViewById(R.id.lblListItem);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.childTitle.setText(childConfig);

        } catch (Exception e) {
        }
        return convertView;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        Content parentContent = parentRecord.get(groupPosition);

        ViewHolder holder;
        try {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.list_group, null);
                holder = new ViewHolder();

                holder.parentTitle = (TextView) convertView.findViewById(R.id.lblListHeader);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.parentTitle.setText(parentContent.getTitle());

        } catch (Exception e) {
        }
        return convertView;
    }

    public static class ViewHolder {

        private TextView childTitle;
        // Content
        private TextView parentTitle;
    

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(this.childRecord.get(((Content) getGroup(groupPosition)).getTitle()).size()==0)
        {
           return 1;
        }
        return this.childRecord.get(((Content) getGroup(groupPosition)).getTitle()).size();
    }

    @Override
    public Content getGroup(int groupPosition) {
        return this.parentRecord.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.parentRecord.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
