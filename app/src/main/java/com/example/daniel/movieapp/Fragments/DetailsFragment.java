package com.example.daniel.movieapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daniel.movieapp.R;

public class DetailsFragment extends Fragment {

    TextView nameOfMovie, plotSynopsis, userRating, releaseDate;
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

        TextView title = v.findViewById(R.id.title);
        title.setText(getArguments().getString("nameOfMovie"));

        return v;
    }

}
