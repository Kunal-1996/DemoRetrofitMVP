package com.example.demoretrofitmvp.presenter;

import android.util.Log;

import com.example.demoretrofitmvp.contract.IMovieModel;
import com.example.demoretrofitmvp.contract.IMovieView;
import com.example.demoretrofitmvp.model.Movie;
import com.example.demoretrofitmvp.model.MovieListResponse;
import com.example.demoretrofitmvp.network.IMovieApi;
import com.example.demoretrofitmvp.network.MovieApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * RequestDataFrom Server function used in API Calling
 */
public class MovieListPresenter implements IMovieModel,IMovieView{

    private IMovieView IMovieView;
    private IMovieModel IMovieModel;
    private final String TAG = "MovieListModel";
    private int pageNo = 1;


    public MovieListPresenter(IMovieView movieListIMovieView) {

        this.IMovieView = movieListIMovieView;
    }


    @Override
    public void requestDataFromServer() {

        if(IMovieView != null) {
            IMovieView.showProgress();
        }
        IMovieApi apiService = MovieApi.getClient().create(IMovieApi.class);

        Call<MovieListResponse> call = apiService.getPopularMovies(MovieApi.API_KEY, pageNo);

        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                List<Movie> movies = response.body().getResults();
                Log.e(TAG, "Number of movies received: "+ movies.size());
                IMovieView.onFinished(movies);
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                IMovieView.onFailure(t);

            }
        });

    }


    @Override
    public void onFinished(List<Movie> movieArrayList) {

        IMovieView.onFinished(movieArrayList);

        if(IMovieView != null) {
            IMovieView.hideProgress();
        }

    }

    @Override
    public void onFailure(Throwable t) {

        IMovieView.onFailure(t);

        if(IMovieView != null) {
            IMovieView.hideProgress();
        }

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }




}