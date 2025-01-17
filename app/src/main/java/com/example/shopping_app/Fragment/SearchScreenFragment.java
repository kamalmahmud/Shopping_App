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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

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
    EditText Searchbar;
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
        View view = inflater.inflate(R.layout.fragment_search_screen, container, false);

        Searchbar=view.findViewById(R.id.search_bar);
        Searchbar.setText(searchQuery);

        Log.d("SearchDebug", "SearchBar found2: " + (Searchbar != null));

        // Inflate layout
        SearchScreenList searchListFragment = SearchScreenList.newInstance(searchQuery);

        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.searchItemList, searchListFragment)
                .addToBackStack(null)
                .commit();


        setupSearchListener();
        return view;
    }
    private void setupSearchListener() {
        Log.d("SearchDebug", "Enter key detectedq");

        Searchbar.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                Log.d("SearchDebug", "Enter key detected");
                performSearch();
                return true;
            }
            return false;
        });

        // Approach 2: KeyListener
        Searchbar.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER) {
                performSearch();
                return true;
            }
            return false;
        });
    }
    private void performSearch() {
        String searchQuery = Searchbar.getText().toString().trim();

        if (!searchQuery.isEmpty()) {
            try {
                SearchScreenList searchListFragment = SearchScreenList.newInstance(searchQuery);

                FragmentManager fragmentManager = getChildFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.searchItemList, searchListFragment)
                        .addToBackStack(null)
                        .commit();

            } catch (Exception e) {
                Log.e("SearchDebug", "Error during search: ", e);
            }
        }
    }
}