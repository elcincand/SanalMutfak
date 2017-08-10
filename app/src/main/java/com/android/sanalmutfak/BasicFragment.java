package com.android.sanalmutfak;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
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
public class BasicFragment extends Fragment {

    private ImageButton madd;
    private ListView mfood;
    private ListView mbozuk;
    ArrayList<DataModelBasic> dataModelsBasic;
    private static ListAdapterBasic adapterbasic;
    DatabaseReference mbasicRef;

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
        dataModelsBasic = new ArrayList<>();
        mbasicRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sanalmutfak-d81ad.firebaseio.com/kitchens/"
                + LoginFragment.logkitchen + "/foods");

        adapterbasic = new ListAdapterBasic(dataModelsBasic, getActivity());
        mfood.setAdapter(adapterbasic);
        mfood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(getActivity());
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + position);
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        removeItemBasic();
                    }
                });
                adb.show();
            }
        });

        mbasicRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childSnapShot : dataSnapshot.getChildren()) {
                    String bname = (String) childSnapShot.child("foodname").getValue();
                    String bskt = (String) childSnapShot.child("skt").getValue();
                    String but = (String) childSnapShot.child("ut").getValue();


                    dataModelsBasic.add(new DataModelBasic(bname, bskt, but));
                    adapterbasic.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    public void removeItemBasic() {

        mfood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataModelBasic toRemovebasic = adapterbasic.getItem(position);
                adapterbasic.remove(toRemovebasic);
                mbasicRef.child(String.valueOf(position)).removeValue();
                adapterbasic.notifyDataSetChanged();
            }

        });
    }



    public void updateFood(){

    }

}




