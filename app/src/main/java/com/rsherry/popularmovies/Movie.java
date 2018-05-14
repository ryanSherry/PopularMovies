package com.rsherry.popularmovies;

public class Movie {

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
}
