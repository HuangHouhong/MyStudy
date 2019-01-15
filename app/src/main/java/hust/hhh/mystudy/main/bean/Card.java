package hust.hhh.mystudy.main.bean;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by 98595 on 2019/1/15.
 */

public abstract class Card {

    //卡片内部条目数据
    private List items;

    public Card() {
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
    public abstract @Nullable String getCardTitle();

    public static class CardType {

        public final static int TYPE_UI =0;
        public final static int TYPE_NET =1;
    }
}
