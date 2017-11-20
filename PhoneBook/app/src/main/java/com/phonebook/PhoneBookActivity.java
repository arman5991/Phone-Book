package com.phonebook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class PhoneBookActivity extends AppCompatActivity {

    private boolean b = true;
    private Toolbar toolBar;
//    private SearchView searchView;
    private android.widget.SearchView searchView;
    private RecyclerView recyclerView;
    private List<String> itemList;
    List<String> filterProductResult;
    private LinearLayout linearLayout;
    private FrameLayout frameLayout;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    toolBar.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
//                    replaceFragment(SearchFragment.newInstance());
                    return false;
                case R.id.navigation_my_profil:
                    toolBar.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.VISIBLE);
                    replaceFragment(MyProfileFragment.newInstance());
                    return false;
                case R.id.navigation_vip:
                    toolBar.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.VISIBLE);
                    replaceFragment(VIPFragment.newInstance());
                    return false;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_book);

        linearLayout = (LinearLayout) findViewById(R.id.layner_layout);
        frameLayout = (FrameLayout) findViewById(R.id.content);
         toolBar = (Toolbar) findViewById(R.id.tool_bar);
//        setSupportActionBar(toolBar);
        searchView = (SearchView) findViewById(R.id.serach_View);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_View);
        itemList = new ArrayList<>();
        filterProductResult = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            itemList.add(i + "babken");
            itemList.add("arman");
            itemList.add("taron");
            itemList.add(23 + "taron");
        }

        final MyAdapter adapter = new MyAdapter(itemList);
        RecyclerView.LayoutManager layoutMeneger = new LinearLayoutManager(getApplication());
        recyclerView.setLayoutManager(layoutMeneger);
        recyclerView.setAdapter(adapter);


        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

            }
        });

        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
//
//                adapter.getFilter().filter(s);
                if(s.length() >= 1) {
                    recyclerView.setVisibility(View.VISIBLE);
                }else {
                    recyclerView.setVisibility(View.INVISIBLE);
                }
                FilterProduct(s);
                return false;
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        replaceFragment(SearchFragment.newInstance());
    }

    private void replaceFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit();

    }

    private void FilterProduct(String newText) {

        String pName;
        filterProductResult.clear();
        for (int l = 0; l < itemList.size(); l++) {
            pName = itemList.get(l).toLowerCase();
            if (pName.contains(newText.toLowerCase())) {
                filterProductResult.add(itemList.get(l));
            }
        }
        MyAdapter ad1 = new MyAdapter(filterProductResult);
        recyclerView.setAdapter(ad1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
