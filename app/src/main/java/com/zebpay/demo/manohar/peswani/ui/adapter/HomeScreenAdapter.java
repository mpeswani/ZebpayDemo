package com.zebpay.demo.manohar.peswani.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zebpay.demo.manohar.peswani.R;
import com.zebpay.demo.manohar.peswani.database.entity.ZebPayFeed;
import com.zebpay.demo.manohar.peswani.util.CircleTransform;
import com.zebpay.demo.manohar.peswani.util.TimeAgo;

import java.util.List;

/**
 * Created by Manohar on 04-08-2017.
 */

public class HomeScreenAdapter extends BaseQuickAdapter<ZebPayFeed, BaseViewHolder> {


    public HomeScreenAdapter(@LayoutRes int layoutResId, @Nullable List<ZebPayFeed> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZebPayFeed item) {
        Glide.with(mContext).load(item.getSourceImageUrl()).
                error(getDrawable(item.getName().substring(0, 1))).
                crossFade().transform(new CircleTransform(mContext)).
                into((ImageView) helper.getView(R.id.image));
        helper.setText(R.id.name, item.getTitle());
        helper.setText(R.id.rollno, item.getDescription());
        helper.setText(R.id.time, TimeAgo.toDuration(item.getTime()));
    }

    private TextDrawable getDrawable(String character) {
        return TextDrawable.builder()
                .buildRound(character, mContext.getResources().getColor(R.color.colorPrimary));
    }
}
