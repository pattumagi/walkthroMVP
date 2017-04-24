package com.lakshmi.walkthromvp.tutorial;

/**
 * Created by mgs1899 on 4/20/2017.
 */

public class TutorialPresenter implements TutorialContract.Presenter {
  TutorialContract.View view;


    public TutorialPresenter(TutorialContract.View View) {

     view=View;

    }


    @Override
    public void setViewPager() {
        view.setViewPager();

    }

    @Override
    public void callRecycle() {
        view.callRecyvlerView();
    }




}
