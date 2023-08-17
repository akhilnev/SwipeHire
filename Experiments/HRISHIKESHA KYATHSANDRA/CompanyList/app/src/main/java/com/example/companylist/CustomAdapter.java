package com.example.companylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

    Context c;
    String[] companyList;
    int[] companyImages;
    LayoutInflater inflater;

    public CustomAdapter(Context ctx, String[] companyList, int[] companyImages){
        this.c = ctx;
        this.companyList = companyList;
        this.companyImages = companyImages;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return companyList.length;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_custom_list_view, null);
        TextView tv = view.findViewById(R.id.textview);
        ImageView iv = view.findViewById(R.id.imageIcons);
        tv.setText(companyList[i]);
        iv.setImageResource(companyImages[i]);
        return view;
    }
}
