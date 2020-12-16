package com.boss.homeworkout.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.boss.homeworkout.R;

public class ListViewAdapter extends ArrayAdapter<String> {

    String[] types;
    int[] images;
    Context mContext;

    public ListViewAdapter(Context context, String[] workoutType, int[] workoutImage) {
        super(context, R.layout.listview_item);

        this.types = workoutType;
        this.images = workoutImage;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return types.length;
    }


    /*convertView - predstavlja pojedini red unutar liste (sadrži layout u kojem se nalazi lista),
    position - referenca pomocu koje mozemo pristupiti vrijednosti unutar listview
    */
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
    /*cestim pozivanjem findviewbyid() tijekom skrolanja ListView-a usporavaju se performanse pa
     koristimo ViewHolder*/
        ViewHolder mViewHolder = new ViewHolder();
        if(convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.listview_item, parent, false);

            mViewHolder.mImage = (ImageView) convertView.findViewById(R.id.imageView);
            mViewHolder.mType = (TextView) convertView.findViewById(R.id.textView);

            convertView.setTag(mViewHolder);
        }else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.mImage.setImageResource(images[position]);
        mViewHolder.mType.setText(types[position]);

        return convertView;
    }

    static class ViewHolder {
        ImageView mImage;
        TextView mType;
    }
}
