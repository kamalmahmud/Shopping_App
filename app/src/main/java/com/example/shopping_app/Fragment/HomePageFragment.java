package com.example.shopping_app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shopping_app.Adapter.homeScreenItemsAdapter;
import com.example.shopping_app.Interfaces.RecycleViewOnClick;
import com.example.shopping_app.ProductDetailActivity;
import com.example.shopping_app.R;
import com.example.shopping_app.ViewModel.ItemListViewModel;

public class HomePageFragment extends Fragment  {



    private RecyclerView recyclerView;
    private homeScreenItemsAdapter adapter;
    private ItemListViewModel itemListViewModel;




    public HomePageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(),"OnCreate",Toast.LENGTH_SHORT).show();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getActivity(),"onCreateView",Toast.LENGTH_SHORT).show();
        FragmentManager fragmentManager=getChildFragmentManager();


            fragmentManager.beginTransaction().replace(R.id.homeScreenlistframe, homescreenlist.class,null).addToBackStack(null).commit();




        return inflater.inflate(R.layout.fragment_home_screen, container, false);
    }




}