package com.rsherry.popularmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RadioButton;

import com.android.volley.toolbox.JsonObjectRequest;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Movie> mMovies = new ArrayList<Movie>();
    private List<Movie> returnedMovies;
    private String movieUrl;
    MovieAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie_sort_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_highest_rated:
                sortByHighestRated();
                break;
            case R.id.sort_popular:
                sortByMostPopular();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sortByMostPopular() {
            new FetchMovies().execute(getUrlString("popular"));
    }

    private void sortByHighestRated() {
            new FetchMovies().execute(getUrlString("top_rated"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new MovieAdapter(mMovies);
        mRecyclerView.setAdapter(adapter);

        new FetchMovies().execute(getUrlString("popular"));

        //Networking using OkHttp



//        mMovies = new Movie[6];
//        mMovies[0] = GetMovieObject.getMovieObject();
//        mMovies[1] = GetMovieObject.getMovieObject();
//        mMovies[2] = GetMovieObject.getMovieObject();
//        mMovies[3] = GetMovieObject.getMovieObject();
//        mMovies[4] = GetMovieObject.getMovieObject();
//        mMovies[5] = GetMovieObject.getMovieObject();


//        adapter = new MovieAdapter(mMovies);
//        mRecyclerView.setAdapter(adapter);

//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
//        mRecyclerView.setLayoutManager(layoutManager);

    }

    List<Movie> convertJsonMoviesToArray (String jsonData) throws JSONException {
        JSONObject jsonObject= new JSONObject(jsonData);
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        JSONObject jsonMovie;
        Movie movie;
        List<Movie> movies = new ArrayList<Movie>();

        for (int i = 0; i < jsonArray.length(); i++) {

            //Get json movie object from json movie array
            jsonMovie = jsonArray.getJSONObject(i);

            movies.add(i,new Movie(
                    jsonMovie.getString("title"),
                    jsonMovie.getString("release_date"),
                    jsonMovie.getString("poster_path"),
                    jsonMovie.getDouble("vote_average"),
                    jsonMovie.getString("overview")
            ));

            Log.i("Testing 123",movies.get(i).getMoviePoster());
        }
        return movies;
    }


//        OkHttpClient okHttpClient = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url(movieUrl)
//                .build();
//
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String json = response.body().string();
//                Log.v(TAG, json);
//
//                if (response.isSuccessful()) { {
//                            try {
//                                mMovies = convertJsonMoviesToArray(json);
//                                adapter = new MovieAdapter(mMovies);
//                                adapter.notifyDataSetChanged();
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        mRecyclerView.setAdapter(adapter);
//                                        layoutManager = new GridLayoutManager(MainActivity.this,2);
//                                        mRecyclerView.setLayoutManager(layoutManager);
//                                    }
//                                });
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//            }
//        });
private String getUrlString(String query) {

        //queries are popular and top_rated

    String apiKey = "api_key" + "=" + ApiKey.getApiKey();

    return "https://api.themoviedb.org/3/movie/" + query + "?" + apiKey;
}

    public class FetchMovies extends AsyncTask<String,Void,String[]> {

        @Override
        protected String[] doInBackground(String... strings) {
            URL url = null;
            try {
                url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;
                while ((inputString = bufferedReader.readLine()) != null) {
                    builder.append(inputString);
                }

                returnedMovies = new ArrayList<Movie>();
                returnedMovies = convertJsonMoviesToArray(builder.toString());

                urlConnection.disconnect();

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] string) {
                mMovies.clear();
                mMovies.addAll(returnedMovies);
                adapter.notifyDataSetChanged();
        }
    }
    }
