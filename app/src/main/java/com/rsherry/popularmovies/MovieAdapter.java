package com.rsherry.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    public List<Movie> mMovies;

    public MovieAdapter(List<Movie> movies) {
        mMovies = movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item,parent,false  );
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = mMovies.get(position);

        Uri uri = Uri.parse(movie.getMoviePoster());
        Picasso.get().load(uri).into(holder.mMoviePoster);
    }

    public interface OnItemClickListener {
        public void onClick(View view, int position);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //views in movie_list_item

        public ImageView mMoviePoster;
        private OnItemClickListener mListener;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mMoviePoster = itemView.findViewById(R.id.moviePoster);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            mListener.onClick(v, getAdapterPosition());
        }
    }



}
