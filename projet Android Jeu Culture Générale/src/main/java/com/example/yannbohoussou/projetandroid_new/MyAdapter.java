package com.example.yannbohoussou.projetandroid_new;

/**
 * Created by Yann BOHOUSSOU on 20/05/2016.
 */
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<String> {
    private int layoutResourceId;
    private List<String> data;
    private Context context;
    private int[] niveaux=new int[8];
    public MyAdapter(Context context, int layoutId, List<String> list,int niveau) {
        super(context, layoutId, list);
        layoutResourceId = layoutId;
        data = list;
        this.context=context;
        for(int i=0;i<8;i++){
            niveaux[i]=niveau%10;
            niveau=niveau/10;
        }
    }
    @Override
    public View getView(int index, View row, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        row = inflater.inflate(layoutResourceId, parent, false);
        ImageView[] img={(ImageView) row.findViewById(R.id.star1),(ImageView) row.findViewById(R.id.star2),(ImageView) row.findViewById(R.id.star3),(ImageView) row.findViewById(R.id.star4),(ImageView) row.findViewById(R.id.star5)};
        for(int i=4;i>=niveaux[index];i--)
            img[i].setVisibility(View.INVISIBLE );

        TextView text = (TextView) row.findViewById(R.id.categ);
        text.setText(data.get(index));
        return row;
    }

}
