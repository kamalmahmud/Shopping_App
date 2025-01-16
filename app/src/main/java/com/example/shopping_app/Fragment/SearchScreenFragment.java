package com.example.shopping_app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shopping_app.Activities.ProductActivity;
import com.example.shopping_app.Adapter.homeScreenItemsAdapter;
import com.example.shopping_app.FavoriteImplementation;
import com.example.shopping_app.Interfaces.RecycleViewOnClick;
import com.example.shopping_app.Model.ItemListModel;
import com.example.shopping_app.R;
import com.example.shopping_app.ViewModel.ItemListViewModel;

import java.util.List;


public class SearchScreenFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SEARCH_QUERY = "search_query";
    private String searchQuery;
    ItemListViewModel itemListViewModel;
    RecyclerView recyclerView;
    private homeScreenItemsAdapter adapter;
    Integer mColumnCount=2;


    public SearchScreenFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SearchScreenFragment newInstance(String Category) {
        SearchScreenFragment fragment = new SearchScreenFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SEARCH_QUERY, Category);
        fragment.setArguments(args);
        Log.d("SearchFragment","newInstance");
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            searchQuery = getArguments().getString(ARG_SEARCH_QUERY);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("SearchFragment",searchQuery);
        // Initialize ViewModel


        // Inflate layout
        View view = inflater.inflate(R.layout.fragment_search_screen, container, false);
        SearchScreenList searchListFragment = SearchScreenList.newInstance(searchQuery);

        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.searchItemList, searchListFragment)
                .addToBackStack(null)
                .commit();



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_screen, container, false);
    }
}