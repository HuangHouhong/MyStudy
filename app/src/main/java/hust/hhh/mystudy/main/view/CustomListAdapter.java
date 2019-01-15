package hust.hhh.mystudy.main.view;

import android.support.annotation.NonNull;
import android.view.View;


/**
 * Created by 98595 on 2019/1/14.
 */

public abstract class CustomListAdapter {

    private DataSetObserver mObserver;

    public void registerObserver(@NonNull DataSetObserver dataSetObserver) {
        mObserver = dataSetObserver;
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        if (mObserver != null)
            mObserver.onChange();
    }

    public abstract Object getItem(@NonNull int position);

    public abstract int getCount();

    public abstract View getView(@NonNull int position, View convertView);

    public interface DataSetObserver {
        void onChange();
    }
}
