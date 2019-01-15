package hust.hhh.mystudy.main.bean;

/**
 * Created by 98595 on 2019/1/15.
 */

public class NetCard extends Card {
    @Override
    public int getCardType() {
        return CardType.TYPE_NET;
    }

    @Override
    public String getCardTitle() {
        return "各种网络框架样例";
    }
}
