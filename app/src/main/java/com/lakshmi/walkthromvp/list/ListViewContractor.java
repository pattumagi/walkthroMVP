package com.lakshmi.walkthromvp.list;

import java.util.ArrayList;

/**
 * Created by mgs1899 on 4/13/2017.
 */

public class ListViewContractor {

    interface View{

        void setadapterListView( ArrayList<String> arname);
        void callNextView();

    }

    interface Presenter{

         void callAsyncTask();

        void callPostExecute(String str);

        void callNextView();
    }
}
