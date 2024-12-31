package com.example.shopping_app.NotUsedYet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shopping_app.Fragment.FavoritesFragment;
import com.example.shopping_app.R;
import com.google.android.material.button.MaterialButton;

public class EmptyFavoritesFragment extends Fragment {

    public EmptyFavoritesFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_empty_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton backButton = view.findViewById(R.id.btn_back);
        MaterialButton exploreButton = view.findViewById(R.id.btn_explore_now);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) getActivity().onBackPressed();
            }
        });

        exploreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    // Navigate to a fragment or activity where user can explore products
                    // For demo, we’ll navigate to the FavoritesFragment with items
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.emptyfavorite, new FavoritesFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }
}

