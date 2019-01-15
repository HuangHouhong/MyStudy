package hust.hhh.mystudy.main.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hust.hhh.mystudy.R;
import hust.hhh.mystudy.main.bean.Card;
import hust.hhh.mystudy.main.ui.UICardAdapter;
import hust.hhh.mystudy.main.ui.UICardItemClickListener;

/**
 * Created by 98595 on 2019/1/7.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private Context mContext;
    private List mCards;

    public MainAdapter(Context context, List cards) {
        this.mContext = context;
        this.mCards = cards;
    }

    public void updateDatas(List cards) {
        this.mCards = cards;
        notifyDataSetChanged();
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 注意inflate后面必须要由三个参数，且第三个为false,否则Item中（CardView）将无法match_parent
        MainViewHolder mainViewHolder = new MainViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.activity_main_card, parent, false));
        return mainViewHolder;
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        Card card = getCard(position);
        if (card != null) {
            holder.cardTitle.setText(card.getCardTitle());
            final List items = card.getItems();
            if (items != null) {
                UICardAdapter mainCardCustomListAdapter =
                        new UICardAdapter(mContext, items);
                UICardItemClickListener onItemClickListener =
                        new UICardItemClickListener(mContext, items);
                holder.customList.setAdapter(mainCardCustomListAdapter);
                holder.customList.setOnItemClickListener(onItemClickListener);
            }
        }
    }

    private Card getCard(int position) {
        if (mCards != null) {
            return (Card) mCards.get(position);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        if (mCards == null) {
            return 0;
        }
        return mCards.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {

        TextView cardTitle;
        CustomList customList;

        public MainViewHolder(View view) {
            super(view);
            cardTitle = view.findViewById(R.id.card_title);
            customList = view.findViewById(R.id.customlist);
        }
    }
}
