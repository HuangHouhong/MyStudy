package hust.hhh.mystudy.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hust.hhh.mystudy.R;

/**
 * Created by 98595 on 2019/1/7.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private Context mContext;
    private List<String> mDatas;
    private int resourceId;

    public MainAdapter(Context context, List<String> datas, int resourceId) {
        this.mContext = context;
        this.mDatas = datas;
        this.resourceId = resourceId;
    }

    public void updateDatas(List<String>  datas){
        mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       // 注意inflate后面必须要由三个参数，且第三个为false,否则Item中将无法matchparent
        MainViewHolder mainViewHolder = new MainViewHolder(LayoutInflater.from(mContext)
                .inflate(resourceId, parent,false));
        return mainViewHolder;
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        String text = mDatas.get(position);
        holder.textView.setText(text);
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (mDatas != null) {
            size = mDatas.size();
        }
        return size;
    }

    class MainViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MainViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview);
        }
    }
}
