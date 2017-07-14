package com.practice.brvah;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhuyakun on 2017/7/14.
 */

public class MainRvAdapter extends BaseQuickAdapter<ItemInfo, MainRvAdapter.MainRvHolder> {

    public MainRvAdapter() {
        super(R.layout.item_layout, new LinkedList<ItemInfo>());
    }

    @Override
    protected void convert(MainRvHolder helper, ItemInfo item) {
        helper.bindData(item);
    }

    public void clear() {
        getData().clear();
        notifyDataSetChanged();
    }


    public static class MainRvHolder extends BaseViewHolder {
        ItemInfo itemInfo = null;
        @BindView(R.id.itemIv)
        ImageView itemIv;
        @BindView(R.id.itemTv)
        TextView itemTv;

        public MainRvHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            Log.e(TAG, "MainRvHolder:create viewholder");
        }

        public void bindData(ItemInfo data) {
            itemInfo = data;
            itemIv.setImageResource(itemInfo.getImageRes());
            itemTv.setText(itemInfo.getText());
        }
    }
}
