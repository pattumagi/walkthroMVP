package com.lakshmi.walkthromvp.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lakshmi.walkthromvp.R;

/**
 * Created by mgs1899 on 4/20/2017.
 */

public class PageIndicator extends LinearLayout {

    public PageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        LogUtil.info(getClass().getSimpleName(), "CustomView Called");
        setUpView(context);
    }

    public void setUpView(Context context) {
        this.removeAllViews();
        for (int i = 0; i <= AppConstanse.TOTAL_VIEWS_COUNT_FOR_INDICATOR; i++) {
            ImageView imageView = new ImageView(context);
            if (i == AppConstanse.VIEW_INDICATOR_INDEX)
                imageView.setImageResource(R.drawable.ic_indicator_active);
            else
                imageView.setImageResource(R.drawable.ic_indicator_inactive);
            imageView.setPadding(5, 5, 5, 5);
            this.addView(imageView);
        }
    }
}
