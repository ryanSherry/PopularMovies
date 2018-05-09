package com.rsherry.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    public Movie[] mMovies;
    private Context mContext;

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item,parent,false  );
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bindMovie(mMovies[position]);
    }

    @Override
    public int getItemCount() {
        return mMovies.length;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        //views in movie_list_item

        public ImageView mMoviePoster;

        public MovieViewHolder(View itemView) {
            super(itemView);

            mMoviePoster = itemView.findViewById(R.id.moviePoster);
        }

        public void bindMovie(Movie movie) {

        }
    }
}
