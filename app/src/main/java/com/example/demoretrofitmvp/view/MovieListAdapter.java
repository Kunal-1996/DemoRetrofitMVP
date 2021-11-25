package com.example.demoretrofitmvp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demoretrofitmvp.R;
import com.example.demoretrofitmvp.model.Movie;
import com.example.demoretrofitmvp.network.MovieApi;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private List<Movie> movieList;
    private Context context;

    public MovieListAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_movie_list, parent, false);

        return new MovieViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        holder.tvMovietitle.setText(movieList.get(position).getTitle());
        holder.tvReleaseDate.setText(movieList.get(position).getReleaseDate());
        holder.tvOverview.setText(movieList.get(position).getOverview());

        Glide.with(context).load(MovieApi.IMAGE_BASE_URL+movieList.get(position).getPosterPath()).into(holder.ivMovie);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    public class MovieViewHolder extends  RecyclerView.ViewHolder {

        ImageView ivMovie;
        TextView tvMovietitle, tvReleaseDate, tvOverview;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            ivMovie = itemView.findViewById(R.id.ivMovie);
            tvMovietitle = itemView.findViewById(R.id.tvTitleMovie);
            tvReleaseDate = itemView.findViewById(R.id.tvReleaseMovie);
            tvOverview = itemView.findViewById(R.id.tvOverviewMovie);

        }
    }


}