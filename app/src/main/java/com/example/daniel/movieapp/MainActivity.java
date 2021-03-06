package com.example.daniel.movieapp;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.daniel.movieapp.Fragments.*;

public class MainActivity extends AppCompatActivity {

    FragmentManager manager = getSupportFragmentManager();
    Fragment currentFragment;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentFragment = new HomeFragment();
        manager.beginTransaction().replace(R.id.fragment_main, currentFragment).commit();

    }

    private MenuItem searchMenuItem;
    public MenuItem getSearchMenuItem() {
        return searchMenuItem;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_button, menu);
        getMenuInflater().inflate(R.menu.profile_button, menu);
        getMenuInflater().inflate(R.menu.search_bar, menu);

        searchMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                MenuItem searchMenuItem = getSearchMenuItem();
                if (searchMenuItem != null) {
                    searchView.onActionViewCollapsed();
                }
                Bundle bundle = new Bundle();
                bundle.putString("query", query);
                currentFragment = new SearchFragment();
                currentFragment.setArguments(bundle);
                manager.beginTransaction().replace(R.id.fragment_main, currentFragment).addToBackStack("fragment").commit();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_home) {
            currentFragment = new HomeFragment();
            manager.beginTransaction().replace(R.id.fragment_main, currentFragment).addToBackStack("fragment").commit();
            return true;
        }

        if (id == R.id.action_profile) {
            currentFragment = new ProfileFragment();
            manager.beginTransaction().replace(R.id.fragment_main, currentFragment).addToBackStack("fragment").commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.onActionViewCollapsed();
        } else {
            super.onBackPressed();
        }
    }
}
