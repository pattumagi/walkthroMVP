package com.lakshmi.walkthromvp.tutorial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;


import com.lakshmi.walkthromvp.R;
import com.lakshmi.walkthromvp.base.BaseActivity;
import com.lakshmi.walkthromvp.common.AppConstanse;
import com.lakshmi.walkthromvp.common.PageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mgs1899 on 4/19/2017.
 */

public class TutorialActivity extends BaseActivity implements TutorialContract.View{
    @BindView(R.id.introSliderViewPager)
    ViewPager introSliderViewPager;
    @BindView(R.id.btn_next)
    Button nextButton;
    @BindView(R.id.btn_skip)
    Button skipButton;
    @BindView(R.id.pageIndicator)
    PageIndicator pageIndicator;
    TutorialAdapter tutorialAdapter;
    private int[] layouts = new int[]{R.layout.view_tutorial_slide1, R.layout.view_tutorial_slide2, R.layout.view_tutorial_slide3};
    TutorialPresenter tutorialPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widgetsview);
        ButterKnife.bind(this);
        tutorialPresenter=new TutorialPresenter(this);
        tutorialPresenter.setViewPager();

    }




    @Override
    public void setViewPager() {
        tutorialAdapter = new TutorialAdapter(TutorialActivity.this, layouts);
        introSliderViewPager.setAdapter(tutorialAdapter);
        introSliderViewPager.addOnPageChangeListener(viewPagerPageChangeListener);

    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {


            // changing the next button text 'NEXT' / 'GOT IT'
            AppConstanse.VIEW_INDICATOR_INDEX = position;
            pageIndicator.setUpView(TutorialActivity.this);

            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                nextButton.setText(getString(R.string.start));
                skipButton.setVisibility(View.GONE);
            } else {
                // still pages are left
                nextButton.setText(getString(R.string.next));
                skipButton.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };

}
