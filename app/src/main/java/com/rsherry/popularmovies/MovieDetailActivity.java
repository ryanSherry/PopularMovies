package com.rsherry.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {

    @BindView(R.id.detailMoviePoster) ImageView mMoviePoster;
    @BindView(R.id.movieTitle) TextView mTitle;
    @BindView(R.id.releaseDate) TextView mReleaseDate;
    @BindView(R.id.plotSynopsis) TextView mPlotSynopsis;
    @BindView(R.id.ratingBar) RatingBar mRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);



        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("MOVIE");

        Uri uri = Uri.parse(movie.getMoviePoster());
        Picasso.get().load(uri).into(mMoviePoster);

        populateUI(movie);

    }

    private void populateUI(Movie movie) {

        mTitle.setText(movie.getTitle());
        mReleaseDate.setText(movie.getReleaseDate());
        mPlotSynopsis.setText(movie.getPlotSynopsis());
        mRating.setRating((float) movie.getVoteAverage());
    }
}
