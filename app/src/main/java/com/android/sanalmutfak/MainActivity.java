package com.android.sanalmutfak;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ListView mDrawerList;
    NavigationView navigationView;
    private String[] mdrawerArray;
    FragmentTransaction fragmentTransaction;
    Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.setDrawerListener(mToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new BasicFragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Sanal Mutfak");

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.myprofile:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.add(R.id.main_container, new ProfileBlankFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("My Profile");
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.myinventory:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.add(R.id.main_container, new BasicFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("My Inventory");
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();

                        break;
                    case R.id.myimprovement:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.add(R.id.main_container, new ImprovementFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("My Savings");
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();

                        break;
                    case R.id.myshoplist:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.add(R.id.main_container, new ShoppingFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("My Shopping List");
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_notification:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.add(R.id.main_container, new ShoppingFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("My Shopping List");
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_search:

                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_logout:
                        Intent i = new Intent (getApplicationContext(), AuthanticationActivity.class);
                        startActivity(i);
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_settings:

                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;

                }


                return true;
            }
        });

    }


    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mToggle.syncState();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)) {
        return true;

        }
        return super.onOptionsItemSelected(item);
    }

}



