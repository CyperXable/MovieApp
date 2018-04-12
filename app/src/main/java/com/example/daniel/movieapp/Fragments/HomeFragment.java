package com.example.daniel.movieapp.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.daniel.movieapp.Adapters.PopularMoviesAdapter;
import com.example.daniel.movieapp.ApiInterface;
import com.example.daniel.movieapp.Models.PopularMovies;
import com.example.daniel.movieapp.R;
import com.example.daniel.movieapp.dummy.DummyContent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {

    public static String BASE_URL = "https://api.themoviedb.org";
    public static String CATEGORY = "popular";
    public static String API_KEY = "5efee822a961bc8a4ce567b867dfa166";
    public static String LANGUAGE = "en-US";
    public static int PAGE = 1;

    private TextView myTextView;

    @BindView(R.id.pbLoading)
    ProgressBar mLoadingProgress;

    @BindView(R.id.list_view_movies_popular)
    RecyclerView popular_movies;

    PopularMoviesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);

        Call<PopularMovies> call = myInterface.listOfMovies(CATEGORY, API_KEY, LANGUAGE, PAGE);

        call.enqueue(new Callback<PopularMovies>() {
            // similar to android async but Retrofit handles it all without inner class
            @Override
            public void onResponse(Call<PopularMovies> call, Response<PopularMovies> response) {
                PopularMovies rootResults = response.body();

                adapter = new PopularMoviesAdapter(rootResults.getResults());
                List<PopularMovies.ResultsBean> listOfMovies = rootResults.getResults();
                PopularMovies.ResultsBean firstMovie = listOfMovies.get(0);

//                popular_movies.setHasFixedSize(true);
                 StaggeredGridLayoutManager mStaggeredGridManager = new StaggeredGridLayoutManager(2, 1);
//                GridLayoutManager layoutManager = new GridLayoutManager();
                popular_movies.setLayoutManager(
                        mStaggeredGridManager
//                            layoutManager
                );
                adapter = new PopularMoviesAdapter(listOfMovies);
                popular_movies.setAdapter(adapter);
              //  myTextView.setText(firstMovie.getTitle());
                mLoadingProgress.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<PopularMovies> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);
        // myTextView = (TextView)v.findViewById(R.id.my_tv);
        mLoadingProgress = (ProgressBar) v.findViewById(R.id.pbLoading);
        popular_movies = (RecyclerView) v.findViewById(R.id.list_view_movies_popular);
        return v;
    }


    @Override
    public void onPause() {
        //Do nothing
        super.onPause();
    }
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyContent.DummyItem item);
    }

}