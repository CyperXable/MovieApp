package com.example.daniel.movieapp.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daniel.movieapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsFragment extends Fragment {


    String internetUrl = "https://image.tmdb.org/t/p/w500/" + getArguments().getString("posterPoth");

    TextView nameOfMovie, plotSynopsis, userRating, releaseDate;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.imageView)
    ImageView imageView;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_details, container, false);

        Glide.with(getContext()).load(internetUrl).into(imageView);
        title.setText(getArguments().getString("nameOfMovie"));
        ButterKnife.bind(this, v);
        return v;
    }

}
