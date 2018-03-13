package com.example.daniel.movieapp.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daniel.movieapp.Models.FoundMovies;
import com.example.daniel.movieapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder> {

    List<FoundMovies.ResultsBean> foundMoviesItems;

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
    }

    @Override
    public int getItemCount() {
        return foundMoviesItems.size();
    }

    public static class SearchMovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cardViewMovieList)
        CardView cardViewMovieList;

        //@Bind(R.id.imageViewMovieListItem)
        //ImageView imageViewMovieListItem;

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        SearchMovieViewHolder(View viewItem) {
            super(viewItem);

            ButterKnife.bind(this, viewItem);
        }
    }
}
