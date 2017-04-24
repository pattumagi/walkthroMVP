package com.lakshmi.walkthromvp.tutorial;

/**
 * Created by mgs1899 on 4/20/2017.
 */

public class TutorialContract {

    interface View{

        void setViewPager();
        void callRecyvlerView();


    }

    interface Presenter {
        void setViewPager();


        void callRecycle();
    }
}
