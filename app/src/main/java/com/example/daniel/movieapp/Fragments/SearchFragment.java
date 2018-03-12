package com.example.daniel.movieapp.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daniel.movieapp.ApiInterface;
import com.example.daniel.movieapp.Models.FoundMovies;
import com.example.daniel.movieapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {

    public static String BASE_URL = "https://api.themoviedb.org";
    public static String CATEGORY = "movie";
    public static String API_KEY = "5efee822a961bc8a4ce567b867dfa166";
    public static String QUERY = "batman";
    public static int PAGE = 1;
    private String queryValue;

    private TextView myTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            queryValue = getArguments().getString("query");
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);
        QUERY=queryValue;
        Call<FoundMovies> call = myInterface.foundMovies(CATEGORY, API_KEY, QUERY, PAGE);

        call.enqueue(new Callback<FoundMovies>() {
            // similar to android async but Retrofit handles it all without inner class
            @Override
            public void onResponse(Call<FoundMovies> call, Response<FoundMovies> response) {
                FoundMovies rootResults = response.body();
                System.out.print(rootResults);
                List<FoundMovies.ResultsBean> foundMovies = rootResults.getResults();
                FoundMovies.ResultsBean firstMovie = foundMovies.get(0);

                myTextView.setText(firstMovie.getTitle());
            }

            @Override
            public void onFailure(Call<FoundMovies> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        myTextView = (TextView)v.findViewById(R.id.my_tv);
        return v;
    }


    @Override
    public void onPause() {
        //Do nothing
        super.onPause();
    }
}
