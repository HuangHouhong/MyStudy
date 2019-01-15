package hust.hhh.mystudy.main.ui;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import hust.hhh.mystudy.main.view.CustomList;

/**
 * Created by 98595 on 2019/1/15.
 */

public class UICardItemClickListener implements CustomList.OnItemClickListener {

    private Context mContext;
    private List mItems;

    public UICardItemClickListener(Context context, List items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(mContext, "点击了条目：" + position + " :  " + mItems.get(position),
                Toast.LENGTH_SHORT).show();
    }
}
