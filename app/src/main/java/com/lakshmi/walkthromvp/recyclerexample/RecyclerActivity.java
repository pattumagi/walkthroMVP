package com.lakshmi.walkthromvp.recyclerexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lakshmi.walkthromvp.R;
import com.lakshmi.walkthromvp.base.BaseActivity;
import com.lakshmi.walkthromvp.common.AppConstants;
import com.lakshmi.walkthromvp.list.CallAsyncTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mgs1899 on 4/24/2017.
 */

public class RecyclerActivity extends BaseActivity implements RecyclerContract.View
{


    private MoviesAdapter mAdapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    RecyclerViewPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerexample);
        ButterKnife.bind(this);

        presenter=new RecyclerViewPresenter(this);
        presenter.callRecyclerAsync();

        recyclerView.addOnItemTouchListener(
                new RecyclerTouchListener(this, new RecyclerTouchListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click

                        presenter.callOnClickPosition(position);
                    //    Toast.makeText(RecyclerActivity.this,"I am  clicked ",Toast.LENGTH_LONG).show();
                    }
                })
        );

    }


    @Override
    public void setadapterRecycler(final List<recyclerPOJO> arname) {
        mAdapter = new MoviesAdapter(arname);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // for devider
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(mAdapter);


    }


}
