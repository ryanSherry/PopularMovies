package com.rsherry.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    public Movie[] mMovies;

    public MovieAdapter(Movie[] movies) {
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
        Movie movie = mMovies[position];

        Uri uri = Uri.parse(movie.getMoviePoster());
        Picasso.get().load(uri).into(holder.mMoviePoster);
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
    }
}
