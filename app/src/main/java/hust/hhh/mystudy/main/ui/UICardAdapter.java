package hust.hhh.mystudy.main.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import hust.hhh.mystudy.R;
import hust.hhh.mystudy.main.view.CustomListAdapter;

/**
 * Created by 98595 on 2019/1/15.
 */

public class UICardAdapter extends CustomListAdapter {

    private Context mContext;
    private List mItems;

    public UICardAdapter(Context context, List items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public Object getItem(@NonNull int position) {
        if (mItems != null) {
            return mItems.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        if (mItems == null) {
            return 0;
        }
        return mItems.size();
    }

    @Override
    public View getView(@NonNull int position, View convertView) {
        View view = null;
        ViewHolder holder = null;
        if (convertView != null) {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_main_card_item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.textView.setText(getItem(position).toString());
        return view;
    }

    class ViewHolder {

        public TextView textView;

        public ViewHolder(View layout) {
            textView = layout.findViewById(R.id.textview);
        }
    }
}
