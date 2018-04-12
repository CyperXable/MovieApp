package com.example.daniel.movieapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daniel.movieapp.Fragments.HomeFragment;
import com.example.daniel.movieapp.MainActivity;
import com.example.daniel.movieapp.Models.PopularMovies;
import com.example.daniel.movieapp.R;
import com.example.daniel.movieapp.Fragments.HomeFragment.OnListFragmentInteractionListener;
import com.example.daniel.movieapp.dummy.DummyContent.DummyItem;

import java.util.List;

import butterknife.BindView;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PopularMoviesAdapter extends RecyclerView.Adapter<PopularMoviesAdapter.popularMoviesViewHolder> {

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

        holder.textView.setText(popularMovieList.get(position).getTitle());
        String test = popularMovieList.get(position).getPoster_path();
        holder.posterPath = popularMovieList.get(position).getPoster_path();
        holder.imgUrl = "https://image.tmdb.org/t/p/w500" + holder.posterPath;
        Glide.with(holder.imageView.getContext()).load(holder.imgUrl).centerCrop().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return popularMovieList.size();
    }

    class popularMoviesViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        String posterPath;
        String imgUrl;

        public popularMoviesViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.tvTitle);


        }
    }


}
