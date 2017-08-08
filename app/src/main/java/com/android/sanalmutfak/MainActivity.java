package com.android.sanalmutfak;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                BasicFragment fragment = new BasicFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();



    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.navigation_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.myprofile:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.main_container, new ProfileBlankFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("My Profile");
                item.setChecked(true);
                break;
            case R.id.myimprovement:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.main_container, new ImprovementFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("My Savings");
                item.setChecked(true);
                break;
            case R.id.myinventory:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.main_container, new BasicFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("My Inventory");
                item.setChecked(true);
                break;
            case R.id.myshopp:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.main_container, new ShoppingFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("My Shopping List");
                item.setChecked(true);
            case R.id.logout:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.main_container, new OpeningFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("My Shopping List");
                item.setChecked(true);


            default:
                return super.onOptionsItemSelected(item);


        }return false;
    }

}

