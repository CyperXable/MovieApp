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

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsFragment extends Fragment {

    String posterPath;
    String internetUrl;

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
        posterPath = getArguments().getString("imageView");
        internetUrl = "https://image.tmdb.org/t/p/w500" + posterPath;



        ButterKnife.bind(this, v);
        title.setText(getArguments().getString("nameOfMovie"));
        Glide.with(getContext()).load(internetUrl).into(imageView);
        return v;
    }

}
