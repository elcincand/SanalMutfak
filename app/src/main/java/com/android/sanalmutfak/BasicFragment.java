package com.android.sanalmutfak;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BasicFragment extends Fragment {

    private ListView mbozuk;
    private ImageButton madd;
    private ImageButton star;
    private ImageButton logoff;

    public ArrayList<String> bozulacaklarArray = new ArrayList<>();
    public ArrayAdapter adapter;

    public BasicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basic, container, false);


        mbozuk = (ListView) view.findViewById(R.id.bozulmuslar);



/*
        DatabaseReference mbozulacaklarRef = FirebaseDatabase.getInstance().
                getReferenceFromUrl("https://sanalmutfak-d81ad.firebaseio.com/kitchens/"+
                LoginFragment.logkitchen+"/foods/");
        mbozulacaklarRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childSnapShot : dataSnapshot.getChildren()) {

                    String date = (String) childSnapShot.child("foodname").getValue();
                  //  String datetime = (String) childSnapShot.child("price").getValue();
                    String name = (String) childSnapShot.child("skt").getValue();
                    //String name = (String) childSnapShot.child("ut").getValue();

                    adapter = new ArrayAdapter<String>(getActivity().getBaseContext(),
                            android.R.layout.simple_list_item_1, android.R.id.text1, bozulacaklarArray);
                    ListView mbozulacaklar = (ListView) getActivity().findViewById(R.id.bozulacaklar);

                    mbozulacaklar.setAdapter(adapter);

                    //booolEAN
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });



*/





        madd =(ImageButton) view.findViewById(R.id.addbar);

        madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddFoodFragment fragment = new AddFoodFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });




        return view;
    }



/*

        DatabaseReference mbozukRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sanalmutfak-d81ad.firebaseio.com/kitchens/1/ings/0/name");
            mbozukRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String bozuk = dataSnapshot.getValue(String.class);
                    mbozuk.setText(bozuk);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

        });




    }*/


    }

