package hust.hhh.mystudy.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hust.hhh.mystudy.R;

/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity {

    private List<String> mDatas;

    private RecyclerView mRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycleView = findViewById(R.id.main_recycle_view);
        initData();

        //布局样式
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        //分割线
        MainItemDecoration decoration = new MainItemDecoration(this, LinearLayoutManager.VERTICAL);

        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.addItemDecoration(decoration);
        mRecycleView.setAdapter(new MainAdapter(this, mDatas, R.layout.activity_main_card));

    }

    private void initData() {
        mDatas = new ArrayList<>();
        mDatas.add("数据一");
        mDatas.add("数据二");
        mDatas.add("数据二");
        mDatas.add("数据二");
        mDatas.add("数据二");
        mDatas.add("数据二");
        mDatas.add("数据二");
        mDatas.add("数据二");
    }
}
