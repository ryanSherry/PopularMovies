package com.rsherry.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private Movie[] mMovies;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMovies = new Movie[6];
        mMovies[0] = GetMovieObject.getMovieObject();
        mMovies[1] = GetMovieObject.getMovieObject();
        mMovies[2] = GetMovieObject.getMovieObject();
        mMovies[3] = GetMovieObject.getMovieObject();
        mMovies[4] = GetMovieObject.getMovieObject();
        mMovies[5] = GetMovieObject.getMovieObject();

        MovieAdapter adapter = new MovieAdapter(mMovies);
        mRecyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(layoutManager);
    }
}
