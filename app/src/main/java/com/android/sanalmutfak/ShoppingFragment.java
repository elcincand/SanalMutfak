package com.android.sanalmutfak;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends Fragment {
    private ImageButton madd;
    private ListView mshoplist;

    ArrayList<DataModelShopping> dataModelsShop;
    private static ListAdapterShopping adapterShopping;

    public ShoppingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);

        mshoplist= (ListView) view.findViewById(R.id.shopplist);


        madd =(ImageButton) view.findViewById(R.id.foodAddButton);
        madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddFoodFragment fragment = new AddFoodFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        displayShop();
        return view;
    }





    public void displayShop() {
        dataModelsShop = new ArrayList<>();
        DatabaseReference mshopRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sanalmutfak-d81ad.firebaseio.com/kitchens/"
                + LoginFragment.logkitchen + "/shoplist");

        adapterShopping = new ListAdapterShopping(dataModelsShop, getActivity());
        mshoplist.setAdapter(adapterShopping);
        mshoplist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(getActivity());
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete the item?");
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        removeItem();
                    }
                });
                adb.show();
            }
        });


        mshopRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childSnapShot : dataSnapshot.getChildren()) {
                    String sproduct = (String) childSnapShot.child("name").getValue();

                    dataModelsShop.add(new DataModelShopping(sproduct));

                    Log.d("kedi", String.valueOf(dataModelsShop));
                    adapterShopping.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    public void removeItem() {

        final DatabaseReference mshopremRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sanalmutfak-d81ad.firebaseio.com/kitchens/"
                + LoginFragment.logkitchen + "/shoplist/");

        mshoplist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataModelShopping toRemove = adapterShopping.getItem(position);
                adapterShopping.remove(toRemove);
                adapterShopping.notifyDataSetChanged();
                mshopremRef.child(String.valueOf(position)).removeValue();

            }

            });
        }
}
