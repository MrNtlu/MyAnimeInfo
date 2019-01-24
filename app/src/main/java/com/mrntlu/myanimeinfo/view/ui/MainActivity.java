package com.mrntlu.myanimeinfo.view.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.mrntlu.myanimeinfo.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private final String TAG="testJSON";

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        //TODO OnBackPressed close searchview
        if (count > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        NavigationView navigationView = findViewById(R.id.nav_menu);
        drawerLayout=findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout,FragmentSearchAnime.newInstance()).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                Fragment fragment=null;
                switch (menuItem.getItemId()){
                    case R.id.search_anime:
                        fragment=FragmentSearchAnime.newInstance();
                        break;
                    case R.id.anime_top_list:
                        fragment=FragmentTopAnimeList.newInstance();
                        break;
                    case R.id.anime_by_genre:
                        fragment=FragmentAnimeGenres.newInstance();
                        break;
                    case R.id.search_user:
                        fragment=FragmentSearchUser.newInstance();
                        break;
                    case R.id.anime_schedule:
                        fragment=FragmentAnimeSchedule.newInstance();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                if (fragment!=null) {
                    fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).addToBackStack(null).commit();
                }
                return true;
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
}
