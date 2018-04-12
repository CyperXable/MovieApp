package com.example.daniel.movieapp.Adapters;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daniel.movieapp.Fragments.DetailsFragment;
import com.example.daniel.movieapp.Fragments.SearchFragment;
import com.example.daniel.movieapp.MainActivity;
import com.example.daniel.movieapp.Models.FoundMovies;
import com.example.daniel.movieapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder> {

    String posterPath;
    String internetUrl;

    List<FoundMovies.ResultsBean> foundMoviesItems;
    private int positionMovie;

    public SearchMovieAdapter(List<FoundMovies.ResultsBean> _foundMoviesItems) {
        foundMoviesItems = _foundMoviesItems;
    }

    @Override
    public SearchMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        SearchMovieViewHolder searchMovieViewHolder = new SearchMovieViewHolder(viewItem);
        return searchMovieViewHolder;
    }

    @Override
    public void onBindViewHolder(SearchMovieViewHolder holder, int position) {
        holder.tvTitle.setText(foundMoviesItems.get(position).getTitle());
        holder.tvSubtitle1.setText(foundMoviesItems.get(position).getRelease_date());
        Double result = foundMoviesItems.get(position).getVote_average();
        String stringdouble= Double.toString(result);
        holder.tvSubtitle2.setText("Rating: " + stringdouble);
        positionMovie = position;
        posterPath = foundMoviesItems.get(position).getPoster_path();
        internetUrl = "https://image.tmdb.org/t/p/w500" + posterPath;
        Glide.with(holder.tvImage.getContext()).load(internetUrl).placeholder(R.drawable.media_play).into(holder.tvImage);
  }

    @Override
    public int getItemCount() {
        return foundMoviesItems.size();
    }

    public class SearchMovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        android.support.v4.app.FragmentManager manager;
        DetailsFragment currentFragment;

        @BindView(R.id.cardViewMovieList)
        CardView cardViewMovieList;

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.tvSubtitle1)
        TextView tvSubtitle1;

        @BindView(R.id.tvSubtitle2)
        TextView tvSubtitle2;

        @BindView(R.id.imageView)
        ImageView tvImage;
        SearchMovieViewHolder(View viewItem) {
            super(viewItem);
            ButterKnife.bind(this, viewItem);
            viewItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            int position = getAdapterPosition();

            bundle.putString("nameOfMovie", foundMoviesItems.get(position).getTitle());
            bundle.putString("plotSynopsis", foundMoviesItems.get(position).getOverview());
            bundle.putDouble("userRating", foundMoviesItems.get(position).getVote_average());
            bundle.putString("releaseDate", foundMoviesItems.get(position).getRelease_date());
            bundle.putString("imageView", foundMoviesItems.get(position).getPoster_path());

            currentFragment = new DetailsFragment();
            currentFragment.setArguments(bundle);
            manager = ((MainActivity)view.getContext()).getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.fragment_main, currentFragment).addToBackStack("fragment").commit();
        }
    }
}
