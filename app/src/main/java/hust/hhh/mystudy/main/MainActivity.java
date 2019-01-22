package hust.hhh.mystudy.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hust.hhh.mystudy.R;
import hust.hhh.mystudy.main.card.Card;
import hust.hhh.mystudy.main.card.NetCard;
import hust.hhh.mystudy.main.card.UICard;

/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity {

    private List<Card> mCards;

    private RecyclerView mRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycleView = findViewById(R.id.main_recycle_view);
        initData();

        //布局样式.默认垂直布局
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        //分割线
        MainItemDecoration decoration = new MainItemDecoration(this, LinearLayoutManager.VERTICAL);

        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.addItemDecoration(decoration);
        mRecycleView.setAdapter(new MainRecycleAdapter(this, mCards));
    }

    private void initData() {
        mCards = new ArrayList<>();

        ArrayList<Item> items0 = new ArrayList<>();
        items0.add(new Item("数据1", MainActivity.class));
        items0.add(new Item("数据2", MainActivity.class));
        items0.add(new Item("数据3", MainActivity.class));
        items0.add(new Item("数据4", MainActivity.class));
        items0.add(new Item("数据5", MainActivity.class));
        Card card0 = new UICard(this);
        card0.setItems(items0);
        mCards.add(card0);

        ArrayList<Item> items1 = new ArrayList<>();
        items1.add(new Item("1",MainActivity.class));
        items1.add(new Item("2",MainActivity.class));
        items1.add(new Item("3",MainActivity.class));
        items1.add(new Item("4",MainActivity.class));
        items1.add(new Item("5",MainActivity.class));
        items1.add(new Item("6",MainActivity.class));
        items1.add(new Item("7",MainActivity.class));
        items1.add(new Item("8",MainActivity.class));
        items1.add(new Item("9",MainActivity.class));
        items1.add(new Item("10",MainActivity.class));
        items1.add(new Item("11",MainActivity.class));
        Card card1 = new UICard(this);
        card1.setItems(items1);
        mCards.add(card1);

        ArrayList<String> items2 = new ArrayList<>();
        items2.add("hello word");
        items2.add("you are pretty boy");
        items2.add("I need to be around you");
        items2.add("No one else can love you like I do");
        items2.add("Just to peep in on you");
        items2.add("Creepin' up on you");
        items2.add("I've been hanging 'round all the places you haunt");
        items2.add("Do I have to breathe without you?");
        Card card2 = new NetCard(this);
        card2.setItems(items2);
        mCards.add(card2);

        ArrayList<String> items3 = new ArrayList<>();
        items3.add("也许是玫瑰的香味");
        items3.add("让我尝到了爱的滋味");
        items3.add("卸下自己的防备");
        items3.add("也给了你伤害我的机会");
        items3.add("也许是我伪装不够完美");
        items3.add("让你轻易看出爱的纯粹");
        items3.add("我还在这里执迷不悔");
        items3.add("而你已决定要远走高飞");
        Card card3 = new NetCard(this);
        card3.setItems(items3);
        mCards.add(card3);
    }
}
