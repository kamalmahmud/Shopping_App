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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping_app.NotUsedYet.FavoriteItem;
import com.example.shopping_app.Adapter.FavoritesAdapter;
import com.example.shopping_app.R;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private FavoritesAdapter favoritesAdapter;
    private List<FavoriteItem> favoriteItems = new ArrayList<>();

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

        ImageButton backButton = view.findViewById(R.id.btn_back);
        TextView titleTextView = view.findViewById(R.id.tv_title_favorites);
        RecyclerView favoritesRecyclerView = view.findViewById(R.id.rv_favorites);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) getActivity().onBackPressed();
            }
        });

        // Populate your favorite items list (In a real app, fetch from database or API)
        // Just an example
        favoriteItems.add(new FavoriteItem("Nike Fuel Pack", "$32.00", R.drawable.user_icon_background));
        favoriteItems.add(new FavoriteItem("Nike Show X Rush", "$204", R.drawable.user_icon_background));
        favoriteItems.add(new FavoriteItem("Men's T-Shirt", "$45.00", R.drawable.user_icon_background));
        favoriteItems.add(new FavoriteItem("Men's Skate T-Shirt", "$45.00", R.drawable.user_icon_background));

        // Update title with count
        String titleWithCount = getString(R.string.my_favourites) + " (" + favoriteItems.size() + ")";
        titleTextView.setText(titleWithCount);

        // Set up RecyclerView with GridLayoutManager for a 2-column layout
        favoritesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        favoritesAdapter = new FavoritesAdapter(favoriteItems);
        favoritesRecyclerView.setAdapter(favoritesAdapter);
    }
}
