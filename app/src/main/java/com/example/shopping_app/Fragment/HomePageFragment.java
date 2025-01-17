package com.example.shopping_app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
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
    EditText SearchBar;
    View CategoryHoodie,CategoryJeans,CategoryShirt,CategoryShoes,CategoryTshirt,CategoryBag;




    public HomePageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //ListFragment
//        FragmentManager fragmentManager=getChildFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.homeScreenlistframe, homescreenlist.class,null).addToBackStack(null).commit();
//        View view = inflater.inflate(R.layout.fragment_home_screen,container,false);
//        SearchBar=view.findViewById(R.id.search_bar);
//        SearchBar.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                Log.d("OnSearch","hh");
//
//                if (event.getAction()==KeyEvent.ACTION_DOWN&&keyCode==KeyEvent.KEYCODE_ENTER){
//                    Log.d("OnSearch","hh");
//
//                    SearchScreenFragment SFragment= SearchScreenFragment().newInstance(SearchBar.getText().toString());
//                    FragmentManager fragmentManager1=getParentFragmentManager();
//                    fragmentManager1.beginTransaction().replace(R.id.framelayout,SFragment).addToBackStack(null).commit();
//                    Log.d("OnSearch","hh");
//                    return true;
//
//                }
//                return false;
//            }
//        });





//        SearchBar.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                Log.d("OnSearch", "Key pressed");
//
//                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
//                    String searchQuery = SearchBar.getText().toString().trim();
//                    if (!searchQuery.isEmpty()) {
//                        Log.d("OnSearch", "Enter pressed with query: " + searchQuery);
//                        SearchScreenFragment sFragment = SearchScreenFragment.newInstance(searchQuery);
//                        FragmentManager fragmentManager1 = getParentFragmentManager();
//                        fragmentManager1.beginTransaction()
//                                .replace(R.id.framelayout, sFragment)
//                                .addToBackStack(null)
//                                .commit();
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });

        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);

        // Find the SearchBar from inflated view
        SearchBar = view.findViewById(R.id.search_bar);

        // Add log to check if SearchBar is found
        Log.d("SearchDebug", "SearchBar found: " + (SearchBar != null));

        // Setup listener with both approaches for testing
        setupSearchListener();
        CategoryListeners(view);

        // ListFragment setup
        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.homeScreenlistframe, new homescreenlist())
                .addToBackStack(null)
                .commit();

        return view;
    }

    private void setupSearchListener() {
        // Try both listeners to see which one works

        // Approach 1: EditorActionListener
        SearchBar.setOnEditorActionListener((v, actionId, event) -> {
            Log.d("SearchDebug", "EditorAction triggered");
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                Log.d("SearchDebug", "Enter key detected");
                performSearch();
                return true;
            }
            return false;
        });

        // Approach 2: KeyListener
        SearchBar.setOnKeyListener((v, keyCode, event) -> {
            Log.d("SearchDebug", "Key pressed: " + keyCode);
            if (event.getAction() == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER) {
                Log.d("SearchDebug", "Enter key detected");
                performSearch();
                return true;
            }
            return false;
        });
    }

    private void performSearch() {
        String searchQuery = SearchBar.getText().toString().trim();
        Log.d("SearchDebug", "Search query: " + searchQuery);

        if (!searchQuery.isEmpty()) {
            try {
                SearchScreenFragment searchFragment = SearchScreenFragment.newInstance(searchQuery);

                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.framelayout, searchFragment)
                        .addToBackStack(null)
                        .commit();

                Log.d("SearchDebug", "Fragment transaction completed");
            } catch (Exception e) {
                Log.e("SearchDebug", "Error during search: ", e);
            }
        }
    }
    private void CategoryListeners(View view){
        CategoryHoodie=view.findViewById(R.id.hoodiecatagory);
        CategoryBag=view.findViewById(R.id.bagcatagory);
        CategoryJeans=view.findViewById(R.id.jeanscatagory);
        CategoryShirt=view.findViewById(R.id.shirtscatagory);
        CategoryShoes=view.findViewById(R.id.shoescatagory);
        CategoryTshirt=view.findViewById(R.id.tshirtcatagory);


        CategoryShoes.setOnClickListener(v -> handleListCategory("shoes"));
        CategoryShirt.setOnClickListener(v -> handleListCategory("shirt"));
        CategoryJeans.setOnClickListener(v -> handleListCategory("jean"));
        CategoryBag.setOnClickListener(v -> handleListCategory("bag"));
        CategoryHoodie.setOnClickListener(v -> handleListCategory("hoodie"));
        CategoryTshirt.setOnClickListener(v -> handleListCategory("tshirt"));

    }
    private void handleListCategory(String category){
        FragmentManager fragmentManager = getChildFragmentManager();
        homescreenlist categorlist= new homescreenlist().newInstance(category);
        fragmentManager.beginTransaction()
                .replace(R.id.homeScreenlistframe, categorlist)
                .addToBackStack(null)
                .commit();
    }
    }





