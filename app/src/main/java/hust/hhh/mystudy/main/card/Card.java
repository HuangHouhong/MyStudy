package hust.hhh.mystudy.main.card;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 98595 on 2019/1/15.
 */
public abstract class Card {

    public Context mContext;

    //卡片内部条目数据
    private List items;

    public Card(Context context) {
        mContext = context;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    /**
     * @return 卡片类型
     */
    public abstract int getCardType();

    /**
     * @return 卡片标题
     */
    public abstract String getCardTitle();

    /**
     * @return 对应的布局文件id
     */
    public abstract int getLayoutId();

    /**
     * @return 对应卡片的ViewHolder
     */
    public abstract RecyclerView.ViewHolder createViewHolder(ViewGroup parent);

    /**
     * 绑定数据到卡片的UI上
     */
    public abstract void bindViewHolder(RecyclerView.ViewHolder holder, int position);

    public class CardViewHolder extends RecyclerView.ViewHolder{

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public static class CardType {

        public final static int TYPE_DEFAULT= 0;
        public final static int TYPE_UI = 1;
        public final static int TYPE_NET = 2;
    }
}
