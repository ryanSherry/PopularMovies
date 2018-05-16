package com.rsherry.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable{

    private String mTitle;
    private String mReleaseDate;
    private String mMoviePoster;
    private double mVoteAverage;
    private String mPlotSynopsis;
    public String movieUrl = "http://image.tmdb.org/t/p/w185/";

    public Movie() {

    }

    public Movie (String title, String releaseDate, String moviePoster, double voteAverage, String plotSynopsis) {
        mTitle = title;
        mReleaseDate = releaseDate;
        mMoviePoster = movieUrl + moviePoster;
        mVoteAverage = voteAverage;
        mPlotSynopsis = plotSynopsis;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getMoviePoster() {

        return mMoviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        mMoviePoster = movieUrl + moviePoster;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public String getPlotSynopsis() {
        return mPlotSynopsis;
    }

    public void setPlotSynopsis(String plotSynopsis) {
        mPlotSynopsis = plotSynopsis;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mMoviePoster);
        dest.writeString(mReleaseDate);
        dest.writeString(mMoviePoster);
        dest.writeDouble(mVoteAverage);
        dest.writeString(mPlotSynopsis);

    }

    private Movie (Parcel in) {
        mMoviePoster = in.readString();
        mReleaseDate = in.readString();
        mMoviePoster = in.readString();
        mVoteAverage = in.readDouble();
        mPlotSynopsis = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
