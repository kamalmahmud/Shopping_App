package com.example.shopping_app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping_app.Activities.ProductActivity;
import com.example.shopping_app.Adapter.homeScreenItemsAdapter;
import com.example.shopping_app.FavoriteImplementation;
import com.example.shopping_app.Interfaces.RecycleViewOnClick;
import com.example.shopping_app.Model.ItemListModel;
import com.example.shopping_app.R;
import com.example.shopping_app.ViewModel.ItemListViewModel;

import java.util.List;

public class SearchScreenList extends Fragment implements RecycleViewOnClick {
    private int mColumnCount = 2;
    private static final String ARG_SEARCH_QUERY = "search_query";
    private String searchQuery;

    private RecyclerView recyclerView;
    private homeScreenItemsAdapter adapter;
    private ItemListViewModel itemListViewModel;

    public SearchScreenList() {
    }

    public static SearchScreenList newInstance(String Category) {
        SearchScreenList fragment = new SearchScreenList();
        Bundle args = new Bundle();
        args.putString(ARG_SEARCH_QUERY, Category);
        fragment.setArguments(args);
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
        // Initialize ViewModel
        itemListViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(ItemListViewModel.class);

        // Inflate layout
        View view = inflater.inflate(R.layout.fragment_homescreenlist, container, false);

        // Setup RecyclerView
        recyclerView = view.findViewById(R.id.homescreenitemlist);
        Context context = view.getContext();

        // Set layout manager based on column count
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        // Observe ItemList data
        itemListViewModel.getItemListDataByCategory(searchQuery).observe(getViewLifecycleOwner(), new Observer<List<ItemListModel>>() {
            @Override
            public void onChanged(List<ItemListModel> itemListModels) {
                adapter = new homeScreenItemsAdapter(itemListModels, new RecycleViewOnClick() {
                    @Override
                    public void onItemClicked(ItemListModel item) {
                        Intent intent = new Intent(getActivity(), ProductActivity.class);
                        intent.putExtra("Item ID", item.getDocumentId());
                        startActivity(intent);
                    }

                    @SuppressLint("SuspiciousIndentation")
                    @Override
                    public void onAddFavoriteClicked(ItemListModel item, Button favoriteBtn) {
                        FavoriteImplementation.FavorateStateSwitch(favoriteBtn,context,item);
                    }
                });

                // Set adapter to RecyclerView
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public void onItemClicked(ItemListModel item) {
    }

    @Override
    public void onAddFavoriteClicked(ItemListModel item, Button favoriteBtn) {
    }
}
