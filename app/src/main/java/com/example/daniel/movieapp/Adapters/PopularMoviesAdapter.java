package com.example.daniel.movieapp.Adapters;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daniel.movieapp.Fragments.DetailsFragment;
import com.example.daniel.movieapp.MainActivity;
import com.example.daniel.movieapp.Models.PopularMovies;
import com.example.daniel.movieapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularMoviesAdapter extends RecyclerView.Adapter<PopularMoviesAdapter.popularMoviesViewHolder> {

    String posterPath;
    String internetUrl;

    List<PopularMovies.ResultsBean> popularMovieList;
    public PopularMoviesAdapter(List<PopularMovies.ResultsBean> _popularMovieList) {
        popularMovieList = _popularMovieList;
    }

    @Override
    public popularMoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popularmovie_list_item, parent, false);
        popularMoviesViewHolder popularMoviesViewHolder = new popularMoviesViewHolder(view);
        return popularMoviesViewHolder;
    }

    @Override
    public void onBindViewHolder(popularMoviesViewHolder holder, int position) {

        holder.tvTitle.setText(popularMovieList.get(position).getTitle());
        String test = popularMovieList.get(position).getPoster_path();
        posterPath = popularMovieList.get(position).getPoster_path();
        internetUrl = "https://image.tmdb.org/t/p/w500" + posterPath;
        Glide.with(holder.tvImage.getContext()).load(internetUrl).centerCrop().placeholder(R.drawable.media_play).into(holder.tvImage);
    }

    @Override
    public int getItemCount() {
        return popularMovieList.size();
    }

    public class popularMoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        FragmentManager manager;
        DetailsFragment currentFragment;

        @BindView(R.id.cardViewMovieList)
        CardView cardViewMovieList;

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.imageView)
        ImageView tvImage;


        popularMoviesViewHolder(View viewItem) {
            super(viewItem);
            ButterKnife.bind(this, viewItem);
            viewItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            int position = getAdapterPosition();

            bundle.putString("nameOfMovie", popularMovieList.get(position).getTitle());
            bundle.putString("plotSynopsis", popularMovieList.get(position).getOverview());
            bundle.putDouble("userRating", popularMovieList.get(position).getVote_average());
            bundle.putString("releaseDate", popularMovieList.get(position).getRelease_date());
            bundle.putString("imageView", popularMovieList.get(position).getPoster_path());

            currentFragment = new DetailsFragment();
            currentFragment.setArguments(bundle);
            manager = ((MainActivity)view.getContext()).getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.fragment_main, currentFragment).addToBackStack("fragment").commit();
        }
    }


}
