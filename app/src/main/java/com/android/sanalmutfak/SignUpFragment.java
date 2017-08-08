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
import android.widget.TextView;

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
public class SignUpFragment extends Fragment {
    Button sign;
    TextView dontyou;
    EditText mmutfakfield;
    EditText mpasswordfield;
    private DatabaseReference mDatabase;
    int currID;



    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_sign_in, container, false);

        sign = (Button) view.findViewById(R.id.signsign);
        mmutfakfield= (EditText) view.findViewById(R.id.signmutfak);
        mpasswordfield = (EditText) view.findViewById(R.id.signpass);


            sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
                LoginFragment fragment = new LoginFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontainer2, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });


        dontyou = (TextView) view.findViewById(R.id.dontyou);

        dontyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
                LoginFragment fragment = new LoginFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontainer2, fragment);
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


    public void createUser() {
        mDatabase = FirebaseDatabase.getInstance().getReference();


        Query lastQuery = mDatabase.child("kitchens").orderByKey();
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot lastKitchen = getLastElement(dataSnapshot.getChildren());
                currID = 0;
                if(lastKitchen != null){
                    currID = Integer.parseInt(lastKitchen.getKey()) + 1;
                }
                Log.d("kedi", String.valueOf(currID));
                String mutfak = mmutfakfield.getText().toString();
                String pass = mpasswordfield.getText().toString();
                Kitchen kitchen = new Kitchen(mutfak, pass);
                mDatabase.child("kitchens").child(String.valueOf(currID)).setValue(kitchen);

            }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            //Handle possible errors.
        }
    });


    }

}





