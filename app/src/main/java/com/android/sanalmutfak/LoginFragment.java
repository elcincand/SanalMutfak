package com.android.sanalmutfak;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

   Button log;
    EditText mkitchenlog;
    TextView doyou;
    public static String logkitchen;
    EditText mpasswordlog;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);

        log = (Button) view.findViewById(R.id.loglog);

        mkitchenlog = (EditText) view.findViewById(R.id.kitchenlog);
        mpasswordlog= (EditText) view.findViewById(R.id.passlog);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             loginUser();
            }
        });

        doyou = (TextView) view.findViewById(R.id.doyou);


        doyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpFragment fragment = new SignUpFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontainer2, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

        });

        return view;
    }

    public void loginUser() {


        DatabaseReference mlogRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sanalmutfak-d81ad.firebaseio.com/kitchens");
        mlogRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                logkitchen = mkitchenlog.getText().toString();
                String logpass = mpasswordlog.getText().toString();


                    if (dataSnapshot.child(logkitchen).exists()) {

                        if (dataSnapshot.child(logkitchen).child("kitchenpass").getValue().toString().equals(logpass)) {
                            Intent i = new Intent(getActivity(), MainActivity.class);
                            startActivity(i);
                        }else {

                            Toast.makeText(getActivity(), "Wrong password!",
                                    Toast.LENGTH_LONG).show();
                        }
                }else{
                        Toast.makeText(getActivity(), "This user does not exist!",
                                Toast.LENGTH_LONG).show();


               }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


    }
}
