package com.example.daniel.movieapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daniel.movieapp.R;

import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsFragment extends Fragment {

    String posterPath;
    String internetUrl;

    @BindView(R.id.plotSynopsis)
    TextView plotSynopsis;
    @BindView(R.id.userRating)
    TextView userRating;
    @BindView(R.id.releaseDate)
    TextView releaseDate;
    @BindView(R.id.title)
    TextView movieTitle;
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
        ButterKnife.bind(this, v);

        posterPath = getArguments().getString("imageView");
        internetUrl = "https://image.tmdb.org/t/p/w500" + posterPath;
        Glide.with(getContext()).load(internetUrl).into(imageView);

        movieTitle.setText(getArguments().getString("nameOfMovie"));
        plotSynopsis.setText(getArguments().getString("plotSynopsis"));
        Double result = getArguments().getDouble("userRating");
        String stringdouble= Double.toString(result);
        userRating.setText(stringdouble);
        releaseDate.setText(getArguments().getString("releaseDate"));

        return v;
    }

}
