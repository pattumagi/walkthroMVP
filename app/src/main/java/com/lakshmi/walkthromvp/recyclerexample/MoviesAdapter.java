package com.lakshmi.walkthromvp.recyclerexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lakshmi.walkthromvp.R;

import java.util.List;

/**
 * Created by mgs1899 on 4/24/2017.
 */

public class MoviesAdapter  extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<recyclerPOJO> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
        }
    }


    public MoviesAdapter(List<recyclerPOJO> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.populate_recyclerview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        recyclerPOJO movie = moviesList.get(position);
        holder.title.setText(movie.name);
        holder.genre.setText(movie.email);
        holder.year.setText(movie.id);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}