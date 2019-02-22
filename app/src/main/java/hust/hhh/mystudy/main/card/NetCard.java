package hust.hhh.mystudy.main.card;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hust.hhh.mystudy.R;
import hust.hhh.mystudy.main.view.CustomListLayout;
import hust.hhh.mystudy.main.view.CustomListAdapter;

/**
 * Created by 98595 on 2019/1/15.
 */

public class NetCard extends Card {

    public NetCard(Context context) {
        super(context);
    }

    @Override
    public int getCardType() {
        return CardType.TYPE_NET;
    }

    @Override
    public String getCardTitle() {
        return "各种网络框架样例";
    }

    @Nullable
    @Override
    public int getLayoutId() {
        return R.layout.activity_main_card_net;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(getLayoutId(), parent,false);
        return new NetCardViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!(holder instanceof NetCardViewHolder)) {
            return;
        }
        NetCardViewHolder uiHolder = (NetCardViewHolder) holder;

        uiHolder.backHint.setText(getCardTitle());

        NetCardCustomListAdapter adapter = new NetCardCustomListAdapter();
        uiHolder.customListLayout.setAdapter(adapter);

        NetCardItemClickListener listener = new NetCardItemClickListener(getItems());
        uiHolder.customListLayout.setOnItemClickListener(listener);
    }

    class NetCardViewHolder extends CardViewHolder {

        TextView backHint;
        CustomListLayout customListLayout;

        public NetCardViewHolder(View view) {
            super(view);
            backHint = view.findViewById(R.id.background_hint);
            customListLayout = view.findViewById(R.id.customlist);
        }
    }

    class NetCardItemClickListener implements CustomListLayout.OnItemClickListener {

        private List mItems;

        public NetCardItemClickListener(List items) {
            mItems = items;
        }

        @Override
        public void onItemClick(int position) {
            Toast.makeText(mContext, "点击了条目：" + position + " :  " + mItems.get(position),
                    Toast.LENGTH_SHORT).show();
        }
    }

    class NetCardCustomListAdapter extends CustomListAdapter {

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
                view = LayoutInflater.from(mContext).inflate(R.layout.activity_main_card_net_item, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }
            holder.textView.setText(getItem(position).toString());
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
