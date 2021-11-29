package com.example.demoretrofitmvp.network;

import com.example.demoretrofitmvp.model.MovieListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IMovieApi {

    //Get Movie List
    @GET("movie/popular")
    Call<MovieListResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("page") int pageNo);
}

