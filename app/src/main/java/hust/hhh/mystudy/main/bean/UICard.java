package hust.hhh.mystudy.main.bean;

/**
 * Created by 98595 on 2019/1/15.
 */

public class UICard extends Card {

    @Override
    public int getCardType() {
        return CardType.TYPE_UI;
    }

    @Override
    public String getCardTitle() {
        return "各种UI实现";
    }
}
