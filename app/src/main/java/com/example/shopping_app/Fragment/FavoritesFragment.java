package com.example.shopping_app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping_app.NotUsedYet.FavoriteItem;
import com.example.shopping_app.Adapter.FavoritesAdapter;
import com.example.shopping_app.R;
import com.example.shopping_app.db.AppDatabase;
import com.example.shopping_app.db.dao.FavoriteDao;
import com.example.shopping_app.db.entities.FavoriteEntity;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private FavoritesAdapter favoritesAdapter;
    private LiveData<List<FavoriteEntity>> favoriteItems =new MutableLiveData<>();

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView titleTextView = view.findViewById(R.id.tv_title_favorites);
        RecyclerView favoritesRecyclerView = view.findViewById(R.id.rv_favorites);



        // Populate your favorite items list (In a real app, fetch from database or API)
        // Just an example

        AppDatabase db=AppDatabase.getInstance(getContext());
        FavoriteDao favoriteDao=db.favoriteDao();
        favoriteItems=favoriteDao.getAllFavorites();
        // Update title with count
        favoriteItems.observe(getViewLifecycleOwner(), new Observer<List<FavoriteEntity>>() {
            @Override
            public void onChanged(List<FavoriteEntity> favoriteEntities) {
                String titleWithCount = getString(R.string.my_favourites) + " (" + favoriteEntities.size() + ")";
                titleTextView.setText(titleWithCount);
                favoritesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                favoritesAdapter = new FavoritesAdapter(favoriteEntities);
                favoritesRecyclerView.setAdapter(favoritesAdapter);
            }
        });




    }
}
