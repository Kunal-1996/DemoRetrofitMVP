package com.example.demoretrofitmvp.contract;

import com.example.demoretrofitmvp.model.Movie;

import java.util.List;

public interface IMovieView {

    void showProgress();

    void hideProgress();

    void onFinished(List<Movie> movies);

    void onFailure(Throwable t);

}
