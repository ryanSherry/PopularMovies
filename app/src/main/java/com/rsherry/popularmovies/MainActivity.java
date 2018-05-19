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
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Movie> mMovies = new ArrayList<Movie>();
    private ArrayList<Movie> returnedMovies;
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
            new FetchMovies().execute(NetworkUtils.getUrlString("popular"));
    }

    private void sortByHighestRated() {
            new FetchMovies().execute(NetworkUtils.getUrlString("top_rated"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new MovieAdapter(mMovies,this);
        mRecyclerView.setAdapter(adapter);

        new FetchMovies().execute(NetworkUtils.getUrlString("popular"));


    }



    public class FetchMovies extends AsyncTask<String,Void,String[]> {

        @Override
        protected String[] doInBackground(String... strings) {
            returnedMovies = NetworkUtils.networkCall(strings,returnedMovies);
            return new String[0];
        }

        @Override
        protected void onPostExecute(String[] string) {
                mMovies.clear();
                mMovies.addAll(returnedMovies);
                adapter.notifyDataSetChanged();
        }
    }
    }
