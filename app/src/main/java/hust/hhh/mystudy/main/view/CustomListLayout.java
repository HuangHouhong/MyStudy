package hust.hhh.mystudy.main.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * 自定义无法滑动但可以点击的列表，类似ListView丧失了滑动功能
 */
public class CustomListLayout extends LinearLayout implements CustomListAdapter.DataSetObserver {

    private OnItemClickListener mOnItemClickListener;
    private CustomListAdapter mAdapter;

    // 该自定义ListView中
    private List<View> mViewCache;

    public CustomListLayout(Context context) {
        super(context);
        init();
    }

    public CustomListLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomListLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CustomListLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    /**
     * 初始化全局变量
     */
    private void init() {
        mViewCache = new ArrayList<>();
    }

    public void setAdapter(@NonNull CustomListAdapter adapter) {
        mAdapter = adapter;
        //这里调用该方法，是为了间接调用onChange方法，刷新UI
        mAdapter.registerObserver(this);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 如果是在setAdapter后面调用该方法，需要手动的为每个条目设置点击监听
     */
    public void setOnItemClickListener(@NonNull OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;

        for (int i = 0; i < getChildCount(); i++) {
            setClickListener2Item(getChildAt(i), i);
        }
    }

    private void setClickListener2Item(View view, final int position) {
        //如果setOnItemClickListener比setAdapter后调用，mOnItemClickListener可能为空
        if (mOnItemClickListener != null) {
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(position);
                }
            });
        }
    }

    @Override
    public void onChange() {
        int oldChildCount = getChildCount();
        int newChildCount = mAdapter.getCount();

        //缓存的View过多，需要清除
        if (newChildCount < oldChildCount) {
            removeViews(newChildCount, oldChildCount);
        }

        // 优先使用缓存中的View.
        for (int i = 0; i < newChildCount; i++) {
            View itemView;
            if (i < mViewCache.size()) {
                itemView = mViewCache.get(i);
            } else {
                itemView = mAdapter.getView(i, null);
                mViewCache.add(itemView);
            }

            //为每个子View设置监听
            setClickListener2Item(itemView,i);

            //添加子View到当前布局中.如果已经在当前布局中，不需要添加，否则报错
            if (itemView.getParent() == null) {
                addView(itemView);
            }
        }
    }

    /**
     * 监听内部条目接口
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
