package com.example.daniel.movieapp.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.daniel.movieapp.Adapters.SearchMovieAdapter;
import com.example.daniel.movieapp.ApiInterface;
import com.example.daniel.movieapp.MainActivity;
import com.example.daniel.movieapp.Models.FoundMovies;
import com.example.daniel.movieapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {

    public static String BASE_URL = "https://api.themoviedb.org";
    public static String API_KEY = "5efee822a961bc8a4ce567b867dfa166";
    public static String QUERY = "batman";
    public static int PAGE = 1;
    private String queryValue;


    @BindView(R.id.pbLoading)
    ProgressBar mLoadingProgress;

    @BindView(R.id.listViewMovieList)
    RecyclerView listViewMovieList;

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

        QUERY = queryValue;

        Call<FoundMovies> call = myInterface.foundMovies(API_KEY, QUERY, PAGE);

        call.enqueue(new Callback<FoundMovies>() {
            // similar to android async but Retrofit handles it all without inner class
            @Override
            public void onResponse(Call<FoundMovies> call, Response<FoundMovies> response) {
                FoundMovies rootResults = response.body();

                SearchMovieAdapter adapter = new SearchMovieAdapter(rootResults.getResults());
                StaggeredGridLayoutManager mStaggeredGridManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                listViewMovieList.setLayoutManager(mStaggeredGridManager);
                listViewMovieList.setAdapter(adapter);
                mLoadingProgress.setVisibility(View.INVISIBLE);
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
        ButterKnife.bind(this, v);
        return v;
    }


    @Override
    public void onPause() {
        //Do nothing
        super.onPause();
    }
}
