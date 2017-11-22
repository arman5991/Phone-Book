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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class PhoneBookActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean b = false;
    private boolean b1 = false;
    private Toolbar toolBar;
    private android.widget.SearchView searchView;
    private RecyclerView recyclerView;
    private List<Model> pipleList;
    private List<Model> buiznesList;
    private List<Model> detalList;
    private List<Model> filterProductResult;
    private List<Model> aaa;
    private LinearLayout linearLayout;
    private FrameLayout frameLayout;
    private Button btnPile;
    private Button btnBuiznes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_book);

        elementsFindView();
        intilazateList();

        MyAdapter adapter = new MyAdapter(pipleList);
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

                if(s.length() >= 1) {
                    recyclerView.setVisibility(View.VISIBLE);
                    btnPile.setVisibility(View.VISIBLE);
                    btnBuiznes.setVisibility(View.VISIBLE);
                }else {
                    recyclerView.setVisibility(View.INVISIBLE);
                    btnPile.setVisibility(View.INVISIBLE);
                    btnBuiznes.setVisibility(View.INVISIBLE);
                    b=false;
                    b1 = false;
                }

                if(b) {
                    FilterProduct(s, pipleList);
                } else if(b1) {
                    FilterProduct(s, buiznesList);
                } else {
                    FilterProduct(s, detalList);
                }

                return false;
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private void intilazateList() {
        for (int i = 0; i < 100; i++) {
            Model m = new Model();
            m.setName(i + "babken");
            pipleList.add(m);
        }

        for (int i = 0; i < 100; i++) {

            Model m = new Model();
            m.setName("buiznes");
//            buiznesList.add("startup");
//            buiznesList.add("TAXi");
//            buiznesList.add("SuperMarket");
//            buiznesList.add("adidas");
//            buiznesList.add("nike");
//            buiznesList.add("gg");
//            buiznesList.add("yandex");
//            buiznesList.add("smail");
//            buiznesList.add("microsoft");
            buiznesList.add(m);
        }

        for (int i = 0; i < 100; i++) {
            Model m = new Model();
            m.setName(pipleList.get(i).getName() + buiznesList.get(i).getName());
            detalList.add(m);
        }

        for (int i = 0; i < 100; i++) {
            Model m = new Model();
            m.setName(pipleList.get(i).getName());
            m.setBuiznes(buiznesList.get(i).getName());
            m.setPhone("i" + ("i" + 1) + ("i"+2));
            m.setAdress("gfdhgdhdhdhdshjhdsgfjhsdgfjs");
            aaa.add(m);
        }


    }

    private void elementsFindView() {
        linearLayout = (LinearLayout) findViewById(R.id.layner_layout);
        frameLayout = (FrameLayout) findViewById(R.id.content);
        toolBar = (Toolbar) findViewById(R.id.tool_bar);
        searchView = (SearchView) findViewById(R.id.serach_View);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_View);
        pipleList = new ArrayList<>();
        buiznesList = new ArrayList<>();
        detalList = new ArrayList<>();
        aaa = new ArrayList<>();
        filterProductResult = new ArrayList<>();

        btnPile = (Button) findViewById(R.id.btn_piple);
        btnBuiznes = (Button) findViewById(R.id.btn_biznes);

        btnPile.setOnClickListener(this);
        btnBuiznes.setOnClickListener(this);

    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit();

    }

    private void FilterProduct(String newText, List<Model> list) {

        String pName;
        filterProductResult.clear();
        for (int l = 0; l < list.size(); l++) {
            pName = list.get(l).getName().toLowerCase();
            if (pName.contains(newText.toLowerCase())) {
                filterProductResult.add(list.get(l));
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_piple: {
                b = true;
                b1 = false;
                MyAdapter a = new MyAdapter(pipleList);
                recyclerView.setAdapter(a);
                break;
            }

            case R.id.btn_biznes: {
                b1 = true;
                b = false;
                MyAdapter a = new MyAdapter(buiznesList );
                recyclerView.setAdapter(a);
                break;
            }
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    toolBar.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
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

}
