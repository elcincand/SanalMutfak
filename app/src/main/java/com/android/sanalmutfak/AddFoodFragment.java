package com.android.sanalmutfak;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFoodFragment extends Fragment {
private ImageButton madd;
    private Button maddok;
    private ImageButton star;
    private ImageButton logoff;
    EditText mname;
    EditText mprice;
    EditText mskt;
    EditText mut;
    private DatabaseReference mDatabase;


    public AddFoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_food, container, false);

        mname= (EditText) view.findViewById(R.id.foodname);
        mprice = (EditText) view.findViewById(R.id.price);
        mskt= (EditText) view.findViewById(R.id.skt);
        mut = (EditText) view.findViewById(R.id.uretim);

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



    public <T> T getLastElement(final Iterable<T> elements) {
        Iterator<T> itr = elements.iterator();
        T lastElement = null;

        while(itr.hasNext()) {
            lastElement=itr.next();
        }

        return lastElement;
    }


    public void addFood() {
        mDatabase = FirebaseDatabase.getInstance().getReference();


        Query mfoodRef = mDatabase.child("kitchens").child(String.valueOf(LoginFragment.logkitchen)).child("foods");
        mfoodRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    DataSnapshot lastKitchen = getLastElement(dataSnapshot.getChildren());
                    int foodID = 0;
                    if(lastKitchen != null){
                        foodID = Integer.parseInt(lastKitchen.getKey()) + 1;
                    }
                    Log.d("food", String.valueOf(foodID));
                    String fname = mname.getText().toString();
                    double fprice = Double.parseDouble(mprice.getText().toString());
                    String fut = mut.getText().toString();
                    String fskt = mskt.getText().toString();


                    Food food = new Food(fname, fskt, fut, fprice);
                    mDatabase.child("kitchens").child(String.valueOf(LoginFragment.logkitchen))
                            .child("foods").child(String.valueOf(foodID))
                            .setValue(food);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    //Handle possible errors.
                }
            });


    }


}
