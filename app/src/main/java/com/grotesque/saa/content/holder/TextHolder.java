package com.grotesque.saa.content.holder;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grotesque.saa.R;
import com.grotesque.saa.common.holder.BaseViewHolder;
import com.grotesque.saa.content.data.ContentItem;
import com.grotesque.saa.util.FontManager;
import com.grotesque.saa.util.StringUtils;

import java.util.List;

/**
 * Created by 경환 on 2016-05-10.
 */
public class TextHolder extends BaseViewHolder<ContentItem> {


    private TextView mText;

    public TextHolder(Context context, View itemView) {
        super(context, itemView);
        this.mText = (TextView) itemView.findViewById(R.id.txt_postview_text);
        this.mText.setTypeface(FontManager.getInstance(getContext()).getTypeface());
        this.mText.setMovementMethod(LinkMovementMethod.getInstance());
    }


    public static TextHolder newInstance(Context context, ViewGroup parent){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_postview_text, parent, false);
        return new TextHolder(parent.getContext(), v);
    }

    @Override
    public void onBindView(ContentItem item) {

    }

    @Override
    public void onBindView(List<ContentItem> item, int position) {
        CharSequence trimmed = StringUtils.trimTrailingWhitespace(Html.fromHtml(item.get(position).getText()));
        mText.setText(trimmed);
    }

    @Override
    public void onBindView(List<ContentItem> item, int position, String mid) {

    }
}
