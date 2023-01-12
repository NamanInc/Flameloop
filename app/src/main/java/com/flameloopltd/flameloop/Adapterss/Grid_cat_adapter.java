package com.flameloopltd.flameloop.Adapterss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.flameloopltd.flameloop.Models.Category_Model;
import com.flameloopltd.flameloop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Grid_cat_adapter extends BaseAdapter {

    LayoutInflater inflater;

    public Grid_cat_adapter(Context context, List<Category_Model> list) {
        this.context = context;
        this.list = list;
    }

    Context context;
    List<Category_Model> list;


    @Override
    public int getCount() {
        return list.size();
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

        if(inflater==null)
        {
            inflater = LayoutInflater.from(context);

        }
        if(view==null)
        {
            view = inflater.inflate(R.layout.category_card,viewGroup,false);
        }

        ImageView ct_image = view.findViewById(R.id.category_image);
        TextView ct_text = view.findViewById(R.id.category_textview);

        Picasso.get().load(list.get(i).CATEGORY_PICTURE).into(ct_image);
        ct_text.setText(list.get(i).CATEGORY_TEXT);

        return view;
    }
}
