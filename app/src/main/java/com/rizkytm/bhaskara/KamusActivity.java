package com.rizkytm.bhaskara;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class KamusActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    MenuItem menuSetting;
    Toolbar toolbar;

    BhaskaraDB dbHelper;

    KamusFragment kamusFragment;
    BookmarkFragment bookmarkFragment;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamus);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(TheoryActivity.this, Main2Activity.class);
//                startActivity(intent);
                KamusActivity.super.onBackPressed();
            }
        });

        dbHelper = new BhaskaraDB(this);

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        kamusFragment = new KamusFragment();
        bookmarkFragment = BookmarkFragment.getNewInstance(dbHelper);
        goToFragment(kamusFragment, true);

        kamusFragment.setOnFragmentListener(new FragmentListener() {
            @Override
            public void onItemClick(String value) {
//                String id = Global.getState(KamusActivity.this, "dic_type");
//                Intent intent = getIntent();
//                String jenisKamus = intent.getStringExtra(Main2Activity.EXTRA_KAMUS);
//                if (jenisKamus.equals("indo_sunda")) {
//                    int dicType = R.id.action_ind_sun;
//                    goToFragment(DetailFragment.getNewInstance(value, dbHelper, dicType), false);
//                } else {
//                    int dicType = R.id.action_sun_ind;
//                    goToFragment(DetailFragment.getNewInstance(value, dbHelper, dicType), false);
//                }
                String id = Global.getState(KamusActivity.this, "dic_type");
                int dicType = id == null ? R.id.action_ind_sun:Integer.valueOf(id);
                goToFragment(DetailFragment.getNewInstance(value, dbHelper, dicType), false);
            }
        });
        bookmarkFragment.setOnFragmentListener(new FragmentListener() {
            @Override
            public void onItemClick(String value) {
                String id = Global.getState(KamusActivity.this, "dic_type");
                int dicType = id == null ? R.id.action_ind_sun:Integer.valueOf(id);
                goToFragment(DetailFragment.getNewInstance(value, dbHelper, dicType), false);
            }
        });



        EditText edit_search = findViewById(R.id.edit_search);
        edit_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                kamusFragment.filterValue(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menuSetting = menu.findItem(R.id.action_settings);
//        menuSetting.setVisible(false);
//        this.invalidateOptionsMenu();

        String id = Global.getState(this, "dic_type");
//        if (id != null) {
//            onOptionsItemSelected(menu.findItem(Integer.valueOf(id)));
//        } else {
//            ArrayList<String> source = dbHelper.getWord(R.id.action_ind_sun);
//            kamusFragment.resetDataSource(source);
//        }
        Intent intent = getIntent();
        String jenisKamus = intent.getStringExtra(Main2Activity.EXTRA_KAMUS);
        if (id != null) {

            if (jenisKamus.equals("indo_sunda"))
            {
                ArrayList<String> source = dbHelper.getWord(R.id.action_ind_sun);
                kamusFragment.resetDataSource(source);
                menuSetting.setIcon(getDrawable(R.drawable.i_ke_s));
                onOptionsItemSelected(menu.findItem(Integer.valueOf(R.id.action_ind_sun)));
            } else {
                ArrayList<String> source = dbHelper.getWord(R.id.action_sun_ind);
                kamusFragment.resetDataSource(source);
                menuSetting.setIcon(getDrawable(R.drawable.s_ke_i));
                onOptionsItemSelected(menu.findItem(Integer.valueOf(R.id.action_sun_ind)));
            }
        } else {
            if (jenisKamus.equals("indo_sunda"))
            {
                ArrayList<String> source = dbHelper.getWord(R.id.action_ind_sun);
                kamusFragment.resetDataSource(source);
                menuSetting.setIcon(getDrawable(R.drawable.i_ke_s));
            } else {
                ArrayList<String> source = dbHelper.getWord(R.id.action_sun_ind);
                kamusFragment.resetDataSource(source);
                menuSetting.setIcon(getDrawable(R.drawable.s_ke_i));
            }

        }
        return true;
    }

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        menuSetting = menu.findItem(R.id.action_settings);
//
//        String id = Global.getState(this, "dic_type");
//        if (id != null) {
//            onOptionsItemSelected(menu.findItem(Integer.valueOf(id)));
//        } else {
//            ArrayList<String> source = dbHelper.getWord(R.id.action_ind_sun);
//            kamusFragment.resetDataSource(source);
//        }
//        return true;
//    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(R.id.action_settings == id) return true;

        Global.saveState(this,"dic_type", String.valueOf(id));

        ArrayList<String> source = dbHelper.getWord(id);

        Intent intent = getIntent();
        String jenisKamus = intent.getStringExtra(Main2Activity.EXTRA_KAMUS);
        if (jenisKamus.equals("indo_sunda")) {
            if (id == R.id.action_ind_sun) {
                kamusFragment.resetDataSource(source);
                Global.saveState(this,"dic_type", String.valueOf(id));
                menuSetting.setIcon(getDrawable(R.drawable.i_ke_s));
            } else if (id == R.id.action_sun_ind) {
                kamusFragment.resetDataSource(source);
                Global.saveState(this,"dic_type", String.valueOf(id));
                menuSetting.setIcon(getDrawable(R.drawable.s_ke_i));
            }
        } else {
            if (id == R.id.action_ind_sun) {
                kamusFragment.resetDataSource(source);
//            menuSetting.setIcon(getDrawable(R.drawable.icon_indo));
            } else if (id == R.id.action_sun_ind) {
                kamusFragment.resetDataSource(source);
//            menuSetting.setIcon(getDrawable(R.drawable.icon_sunda));
            }
        }
        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_ind_sun) {
//            kamusFragment.resetDataSource(source);
//            menuSetting.setIcon(getDrawable(R.drawable.ic_library_books_red));
//        } else if (id == R.id.action_sun_ind) {
//            kamusFragment.resetDataSource(source);
//            menuSetting.setIcon(getDrawable(R.drawable.ic_library_books_green));
//        }
//        else if (id == R.id.action_sun_sun) {
//            kamusFragment.resetDataSource(source);
//            menuSetting.setIcon(getDrawable(R.drawable.ic_library_books_blue));
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_bookmark) {
            String activeFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container).getClass().getSimpleName();
            if (!activeFragment.equals(BookmarkFragment.class.getSimpleName())) {
                goToFragment(bookmarkFragment, false);
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void goToFragment(Fragment fragment, boolean isTop) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        if (!isTop)
            fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        String activeFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container).getClass().getSimpleName();
        if (activeFragment.equals(BookmarkFragment.class.getSimpleName())) {
            menuSetting.setVisible(false);
            toolbar.findViewById(R.id.edit_search).setVisibility(View.VISIBLE);
            toolbar.setTitle("Bookmark");
        } else {
            menuSetting.setVisible(true);
            toolbar.findViewById(R.id.edit_search).setVisibility(View.VISIBLE);
            toolbar.setTitle("");
        }
        return true;
    }
}
