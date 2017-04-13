package com.lakshmi.walkthromvp.list;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.lakshmi.walkthromvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mgs1899 on 4/13/2017.
 */

public class ListViewActivity extends Activity {

    @BindView(R.id.listview)
    ListView listview;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listactivity);
        ButterKnife.bind(this);
    }
}
