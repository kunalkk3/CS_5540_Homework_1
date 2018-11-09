package com.example.rkjc.news_app_2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private String newsApiSearchResults;
    private RecyclerView mRecyclerView;
    private NewsRecyclerViewAdapter mAdapter;
    private ArrayList<NewsItem> newsItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.news_recyclerview);
        mAdapter = new NewsRecyclerViewAdapter(this, newsItems);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        NewsQueryTask task = new NewsQueryTask();
        task.execute();
    }
    /*@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SEARCH_QUERY_RESULTS, newsApiSearchResults);

    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            NewsQueryTask task = new NewsQueryTask();
            task.execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    public void populateRecyclerView(String searchResults){
        Log.d("mycode", searchResults);
        newsItems = JsonUtils.parseNews(searchResults);
        mAdapter.mNews.addAll(newsItems);
        mAdapter.notifyDataSetChanged();
    }


    class NewsQueryTask extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... voids) {
            Log.d(TAG, "calling doInBackground");
            try {
                Log.d(TAG, "begin network call");
                newsApiSearchResults = NetworkUtils.getResponseFromHttpUrl(new URL(NetworkUtils.buildURL().toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d(TAG, newsApiSearchResults);
            return newsApiSearchResults;
        }

        @Override
        protected void onPostExecute(String results) {
            super.onPostExecute(results);
            Log.d(TAG, "calling onPostExecute");
            Log.d(TAG, "gor  the results from newsapi");
            populateRecyclerView(results);

        }
    }

}
