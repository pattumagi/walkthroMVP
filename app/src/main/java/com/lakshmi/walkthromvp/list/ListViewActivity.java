package com.lakshmi.walkthromvp.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.lakshmi.walkthromvp.R;
import com.lakshmi.walkthromvp.tutorial.TutorialActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mgs1899 on 4/13/2017.
 */

public class ListViewActivity extends Activity implements ListViewContractor.View {

    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.btn_widgets)
    Button btn_widgets;

    ListViewPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listactivity);
        ButterKnife.bind(this);

        presenter = new ListViewPresenter(this);
        presenter.callAsyncTask();

        btn_widgets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.callNextView();
            }
        });

    }

    @Override
    public void setadapterListView(ArrayList<String> arname) {

        ArrayAdapter aa = new ArrayAdapter(ListViewActivity.this, android.R.layout.simple_list_item_1, arname);
        listview.setAdapter(aa);

    }

    @Override
    public void callNextView() {
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }


}
