package hust.hhh.mystudy.main.card;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hust.hhh.mystudy.R;
import hust.hhh.mystudy.main.Item;
import hust.hhh.mystudy.main.view.CustomListAdapter;
import hust.hhh.mystudy.main.view.CustomListLayout;

/**
 * Created by 98595 on 2019/1/15.
 */

public class UICard extends Card {

    public UICard(Context context) {
        super(context);
    }

    @Override
    public int getCardType() {
        return CardType.TYPE_UI;
    }

    @Override
    public String getCardTitle() {
        return "各种UI实现";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_card_ui;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {
        //注意inflate后面必须要由三个参数，且第三个为false,否则Item中（CardView）将无法match_parent
        View view = LayoutInflater.from(mContext).inflate(getLayoutId(), parent, false);
        return new UICardViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!(holder instanceof UICardViewHolder)) {
            return;
        }
        UICardViewHolder uiHolder = (UICardViewHolder) holder;

        uiHolder.title.setText(getCardTitle());

        UICardCustomListAdapter adapter = new UICardCustomListAdapter();
        uiHolder.customListLayout.setAdapter(adapter);

        UICardItemClickListener listener = new UICardItemClickListener(getItems());
        uiHolder.customListLayout.setOnItemClickListener(listener);
    }

    class UICardViewHolder extends CardViewHolder {

        TextView title;
        CustomListLayout customListLayout;

        public UICardViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.card_title);
            customListLayout = view.findViewById(R.id.customlist);
        }
    }

    class UICardItemClickListener implements CustomListLayout.OnItemClickListener {

        private List mItems;

        public UICardItemClickListener(List items) {
            mItems = items;
        }

        @Override
        public void onItemClick(int position) {
            Item item = (Item) mItems.get(position);

            Toast.makeText(mContext, "点击了条目：" + position + " :  " + item.getTitle(),
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent();
            intent.setClass(mContext, item.getIntentClass());
            mContext.startActivity(intent);
        }
    }

    class UICardCustomListAdapter extends CustomListAdapter {

        @Override
        public Object getItem(@NonNull int position) {
            List items = getItems();
            if (items != null) {
                return items.get(position);
            }
            return null;
        }

        @Override
        public int getCount() {
            List items = getItems();
            if (items != null) {
                return items.size();
            }
            return 0;
        }

        @Override
        public View getView(@NonNull int position, View convertView) {
            View view = null;
            ViewHolder holder = null;
            if (convertView != null) {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            } else {
                view = LayoutInflater.from(mContext).inflate(R.layout.activity_main_card_ui_item, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }
            Item item = (Item) getItem(position);

            holder.textView.setText(item.getTitle());
            return view;
        }

        class ViewHolder {
            TextView textView;

            public ViewHolder(View view) {
                textView = view.findViewById(R.id.textview);
            }
        }
    }
}
