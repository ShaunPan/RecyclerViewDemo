package com.pan.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pan.recyclerviewdemo.R;
import com.pan.recyclerviewdemo.bean.TestModel;

import java.util.Iterator;
import java.util.List;

/*
 * File Name:MainRecyclerViewAdapter
 * Author:Better.Z
 * Date:2015/12/28 22:04
 * Description:
 * Copyright:www.YangFanApp.com
 */
public class MainRecyclerViewAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<TestModel> list;

    public MainRecyclerViewAdapter(Context mContext, List<TestModel> testList) {
        this.mContext = mContext;
        this.list = testList;
        mInflater = LayoutInflater.from(mContext);
    }

    public enum ITEM_TYPE {
        ITEM_TYPE_HEADER,
        ITEM_TYPE_1,
        ITEM_TYPE_2
    }

    /* 创建ViewHolder对象 */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_HEADER.ordinal()) {
            return new ItemTypeHeaderViewHolder(mInflater.inflate(R.layout.item_type_head, parent, false));
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_1.ordinal()) {
            return new ItemType1ViewHolder(mInflater.inflate(R.layout.item_type_1, parent, false));
        } else {
            return new ItemType2ViewHolder(mInflater.inflate(R.layout.item_type_2, parent, false));
        }
    }

    /* 将数据绑定到对应的ViewHolder中 */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemTypeHeaderViewHolder) {
            for (Iterator iterator = list.iterator();iterator.hasNext();) {
                TestModel model = (TestModel) iterator.next();
                if (model.getType() == 0) {
                    ((ItemTypeHeaderViewHolder) holder).typeHeader.setText(model.getTitle());
                    iterator.remove();
                }
            }
        } else if (holder instanceof ItemType1ViewHolder) {
            ((ItemType1ViewHolder) holder).type_1.setText(list.get(position - 1).getTitle());
        } else {
            ((ItemType2ViewHolder) holder).type_2.setText(list.get(position - 1).getTitle());
        }
    }

    /* 返回item的总数 */
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size() + 1;
    }

    /* 返回item的类型 */
    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return ITEM_TYPE.ITEM_TYPE_HEADER.ordinal();
        }

        int type = list.get(position - 1).getType();
        switch (type) {
            case 1:
                return ITEM_TYPE.ITEM_TYPE_1.ordinal();
            default:
                return ITEM_TYPE.ITEM_TYPE_2.ordinal();
        }

    }

    public class ItemTypeHeaderViewHolder extends RecyclerView.ViewHolder {

        TextView typeHeader;

        public ItemTypeHeaderViewHolder(View itemView) {
            super(itemView);
            typeHeader = (TextView) itemView.findViewById(R.id.tv_type_header);
        }

    }

    public class ItemType1ViewHolder extends RecyclerView.ViewHolder {
        TextView type_1;

        public ItemType1ViewHolder(View itemView) {
            super(itemView);
            type_1 = (TextView) itemView.findViewById(R.id.tv_type_1);
        }

    }

    public class ItemType2ViewHolder extends RecyclerView.ViewHolder {
        TextView type_2;

        public ItemType2ViewHolder(View itemView) {
            super(itemView);
            type_2 = (TextView) itemView.findViewById(R.id.tv_type_2);
        }

    }
}
