package com.demo.it.listviewindex;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by admin on 2016/11/23.
 */

public class ContactAdapter extends BaseAdapter {
    private ArrayList<Person> mContactLst;
    private Context mContext;

    public ContactAdapter(ArrayList<Person> contactLst, MainActivity activity) {
        mContactLst = contactLst;
        mContext = activity;
    }

    @Override
    public int getCount() {
        return mContactLst.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.contact_item, null);
            holder = new ViewHolder();
            holder.mTitle = (TextView) convertView.findViewById(R.id.title);
            holder.mDetail = (TextView) convertView.findViewById(R.id.detail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTitle.setText(mContactLst.get(position).getHeaderWord());
        holder.mDetail.setText(mContactLst.get(position).getName());
        if (position == 0) {
            holder.mTitle.setVisibility(View.VISIBLE);
        } else {
            if (mContactLst.get(position).getPinyin().contains(mContactLst.get(position - 1).getHeaderWord())) {
                holder.mTitle.setVisibility(View.GONE);
            } else {
                holder.mTitle.setVisibility(View.VISIBLE);
            }
        }

        return convertView;
    }

    public class ViewHolder {
        private TextView mTitle;
        private TextView mDetail;
    }
}
