package com.android.sanalmutfak;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class BasicFragment extends Fragment {

    private ImageButton madd;
    private ListView mfood;
    private ListView mbozuk;
   // List<Food> basiclist = new ArrayList<>();
  //  ArrayAdapter<String> adapter;
    ArrayList<DataModel> dataModels;
    private static ListAdapter adapter;

    public BasicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View view = inflater.inflate(R.layout.fragment_basic, container, false);

        mfood = (ListView) view.findViewById(R.id.food);
       // mbozuk = (ListView) view.findViewById(R.id.bozuk);
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

        displayFood();

        return view;
    }

    public void displayFood() {
        dataModels= new ArrayList<>();
        DatabaseReference mbasicRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sanalmutfak-d81ad.firebaseio.com/kitchens/"
            + LoginFragment.logkitchen +"/foods");

        adapter= new ListAdapter(dataModels,getActivity());
        mfood.setAdapter(adapter);

        mbasicRef.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            for (DataSnapshot childSnapShot : dataSnapshot.getChildren()) {
                String bname = (String) childSnapShot.child("foodname").getValue();
                String bskt = (String) childSnapShot.child("skt").getValue();
                String but = (String) childSnapShot.child("ut").getValue();
                String bprice = (String) childSnapShot.child("price").getValue();


                dataModels.add(new DataModel(bname, bskt, but, bprice));

                Log.d("kedi", String.valueOf(dataModels));
                adapter.notifyDataSetChanged();
            }


        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    });


}

    /*public void displayFood() {
        DatabaseReference mbasicRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sanalmutfak-d81ad.firebaseio.com/kitchens/"
                + LoginFragment.logkitchen +"/foods");

        // adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, basiclist);
        mfood.setAdapter(adapter);
        mbasicRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childSnapShot : dataSnapshot.getChildren()) {
                    String bname = (String) childSnapShot.child("foodname").getValue();
                    String bskt = (String) childSnapShot.child("skt").getValue();
                    String but = (String) childSnapShot.child("ut").getValue();
                    String bprice = (String) childSnapShot.child("price").getValue();


                    //Food food = new Food(bname, bskt, but, bprice);
                   // basiclist.add(food);
                   // basiclist.add(bskt);
                    Log.d("kedi", String.valueOf(basiclist));
                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
            });


    }*/
    }

