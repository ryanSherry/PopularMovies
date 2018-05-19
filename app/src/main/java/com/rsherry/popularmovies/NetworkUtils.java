package com.rsherry.popularmovies;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class NetworkUtils {

    public static String getUrlString(String query) {

        //queries are popular and top_rated

        String apiKey = "api_key" + "=" + ApiKey.getApiKey();

        return "https://api.themoviedb.org/3/movie/" + query + "?" + apiKey;
    }

    public static ArrayList<Movie> networkCall(String[] strings, ArrayList<Movie> returnedMovies){
        URL url = null;
        try {
            url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner scanner = new Scanner(new InputStreamReader(in));
            scanner.useDelimiter("\\A");

            String inputString = "";
            if (scanner.hasNext()) {
                inputString = scanner.next();
            }
            StringBuilder builder = new StringBuilder(inputString);

            returnedMovies = new ArrayList<Movie>();
            returnedMovies = convertJsonMoviesToArray(builder.toString());

            urlConnection.disconnect();

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return returnedMovies;
    }

    private static ArrayList<Movie> convertJsonMoviesToArray(String jsonData) throws JSONException {
        JSONObject jsonObject= new JSONObject(jsonData);
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        JSONObject jsonMovie;
        ArrayList<Movie> movies = new ArrayList<Movie>();

        for (int i = 0; i < jsonArray.length(); i++) {

            //Get json movie object from json movie array
            jsonMovie = jsonArray.getJSONObject(i);

            movies.add(i,new Movie(
                    jsonMovie.getString("title"),
                    jsonMovie.getString("release_date"),
                    jsonMovie.getString("poster_path"),
                    jsonMovie.getString("backdrop_path"),
                    jsonMovie.getDouble("vote_average"),
                    jsonMovie.getString("overview")
            ));

            Log.i("Testing 123",movies.get(i).getMoviePoster());
        }
        return movies;
    }
    }

