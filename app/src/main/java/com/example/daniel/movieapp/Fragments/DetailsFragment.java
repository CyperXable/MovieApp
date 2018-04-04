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

    public static String BASE_URL = "https://image.tmdb.org";
    public static String API_KEY = "5efee822a961bc8a4ce567b867dfa166";
    public static String QUERY;
w500


    TextView nameOfMovie, plotSynopsis, userRating, releaseDate;
    ImageView imageView;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QUERY = getArguments().getString("posterPath");
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
