package hust.hhh.mystudy.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import hust.hhh.mystudy.main.card.Card;

/**
 * Created by 98595 on 2019/1/7.
 * <p>
 * RecycleView适配器
 */
public class MainRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List mCards;

    public MainRecycleAdapter(Context context, List cards) {
        this.mContext = context;
        this.mCards = cards;
    }

    public void updateDatas(List cards) {
        this.mCards = cards;
        notifyDataSetChanged();
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

    @Override
    public int getItemViewType(int position) {
        int cardType = Card.CardType.TYPE_DEFAULT;
        Card card = getCard(position);
        if (card != null) {
            cardType = card.getCardType();
        }

        return generateViewType(position, cardType);
    }

    /**
     * 根据CardType和当前Item所在位置生成ViewType
     *
     * @return 后三位为 {@link Card.CardType},前面数为position
     */
    private int generateViewType(int position, int cardType) {
        return position * 1000 + cardType;
    }

    /**
     * @return 解析ViewType，获取当前Item的position
     */
    private int analysisViewType2Position(int viewType) {
        return viewType / 1000;
    }

    /**
     * 这里将创建ViewHolder的任务交给{@link Card}类去处理
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int position = analysisViewType2Position(viewType);
        Card card = getCard(position);
        RecyclerView.ViewHolder viewHolder = card.createViewHolder(parent);
        return viewHolder;
    }

    /**
     * 绑定数据操作交由{@link Card}去操作
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Card card = getCard(position);
        if (card != null) {
            card.bindViewHolder(holder, position);
        }
    }
}
