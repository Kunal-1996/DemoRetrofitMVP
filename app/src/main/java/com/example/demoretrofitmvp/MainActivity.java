package com.example.demoretrofitmvp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.demoretrofitmvp.contract.IMovieView;
import com.example.demoretrofitmvp.model.Movie;
import com.example.demoretrofitmvp.presenter.MovieListPresenter;
import com.example.demoretrofitmvp.view.MovieListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMovieView {
        private MovieListPresenter moviePresenter;
        private RecyclerView rvMovieList;
        private List<Movie> movieList;
        private MovieListAdapter movieListAdapter;
        private ProgressBar pbLoading;
        private int pageNo = 1;
        private Context context;

        private LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMovieList = findViewById(R.id.rvMovieList);
        pbLoading = findViewById(R.id.pbLoading);

        movieList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        rvMovieList.setLayoutManager(layoutManager);
        rvMovieList.setHasFixedSize(true);

        moviePresenter = new MovieListPresenter(this);
        moviePresenter.requestDataFromServer();

    }

    @Override
    public void showProgress() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoading.setVisibility(View.GONE);
    }


    @Override
    public void onFinished(List<Movie> movies) {
        movieList.addAll(movies);
        movieListAdapter = new MovieListAdapter(movieList, MainActivity.this);
        rvMovieList.setAdapter(movieListAdapter);
    }

    @Override
    public void onFailure(Throwable t) {
        Log.e("ERROR:", t.getMessage());
        Toast.makeText(MainActivity.this, context.getResources().getString(R.string.txt_internet) , Toast.LENGTH_LONG).show();
    }
}
