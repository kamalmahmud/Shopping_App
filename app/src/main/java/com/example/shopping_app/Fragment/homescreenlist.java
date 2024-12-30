package com.example.shopping_app.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shopping_app.Adapter.homeScreenItemsAdapter;
import com.example.shopping_app.Interfaces.RecycleViewOnClick;
import com.example.shopping_app.Model.ItemListModel;
import com.example.shopping_app.ProductDetailActivity;
import com.example.shopping_app.R;
import com.example.shopping_app.ViewModel.ItemListViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homescreenlist#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homescreenlist extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



    private int mColumnCount = 2;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private RecyclerView recyclerView;
    private homeScreenItemsAdapter adapter;
    private ItemListViewModel itemListViewModel;

    public homescreenlist() {
        // Required empty public constructor
    }


    public static homescreenlist newInstance(int columnCount) {
        homescreenlist fragment = new homescreenlist();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        itemListViewModel = new ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(ItemListViewModel.class);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);


        }


        View view = inflater.inflate(R.layout.fragment_homescreenlist, container, false);
        recyclerView= view.findViewById(R.id.homescreenitemlist);

        Context context = view.getContext();
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        adapter=new homeScreenItemsAdapter();
            recyclerView.setAdapter(adapter);


        itemListViewModel.getItemListLiveData().observe(getViewLifecycleOwner(), new Observer<List<ItemListModel>>() {
            @Override
            public void onChanged(List<ItemListModel> itemListModels) {
                adapter.setItemListModels(itemListModels);
                adapter.notifyDataSetChanged();



            }
        });
        // Inflate the layout for this fragment
        return view;
    }




}
