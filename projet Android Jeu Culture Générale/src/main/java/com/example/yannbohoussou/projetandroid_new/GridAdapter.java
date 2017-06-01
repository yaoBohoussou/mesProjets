package com.example.yannbohoussou.projetandroid_new;

/**
 * Created by Yann BOHOUSSOU on 20/05/2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GridAdapter extends BaseAdapter {
    private Context context;
    private String[] letters=new String[12];
    public GridAdapter(Context context,String str) {
        // TODO Auto-generated constructor stub
        super();
        this.context=context;
        letters=str.split("(?!^)");       	//regex-expression, called a negative lookahead
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 12;
    }

    @Override
    public String getItem(int position) {
        // TODO Auto-generated method stub
        return letters[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.lettre_layout,parent,false);
        ImageView img=(ImageView) convertView.findViewById(R.id.lettre);
        img.setImageResource(context.getResources().getIdentifier(letters[position],"drawable",context.getPackageName()));
        img.setTag(letters[position]);
        return convertView;

    }

}
