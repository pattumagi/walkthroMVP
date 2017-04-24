package com.lakshmi.walkthromvp.recyclerexample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mgs1899 on 4/24/2017.
 */

public class RecyclerContract {
    interface View{


        void setadapterRecycler(List<recyclerPOJO> arname);
    }
    interface Presenter{


        void callPostExecute(String str);
    }
}
