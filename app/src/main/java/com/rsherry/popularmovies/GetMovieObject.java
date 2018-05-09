package com.rsherry.popularmovies;

public class GetMovieObject {

    private Movie getMovieObject() {
    //Test variables for test movie object
    String mTitle = "Avengers: Infinity War";
    String mReleaseDate = "2018-04-25";
    String mMoviePoster = "http://image.tmdb.org/t/p/w185//bOGkgRGdhrBYJSLpXaxhXVstddV.jpg";
    double mVoteAverage = 8.6;
    String mPlotSynopsis = "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.";


        Movie testMovie = new Movie(mTitle, mReleaseDate, mMoviePoster, mVoteAverage, mPlotSynopsis);
        return testMovie;
    }

}
