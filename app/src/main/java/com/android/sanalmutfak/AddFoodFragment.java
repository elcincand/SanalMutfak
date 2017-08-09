package com.android.sanalmutfak;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFoodFragment extends Fragment {
private ImageButton madd;
    private Button maddok;

    EditText mname;
    EditText mprice;
    EditText mskt;
    EditText mut;
    private DatabaseReference mDatabase;
    Calendar myCalendar = Calendar.getInstance();


    public AddFoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }

        final View view = inflater.inflate(R.layout.fragment_add_food, container, false);

        mname= (EditText) view.findViewById(R.id.foodname);
        mprice = (EditText) view.findViewById(R.id.price);
        mskt= (EditText) view.findViewById(R.id.skt);
        mut = (EditText) view.findViewById(R.id.uretim);



        final DatePickerDialog.OnDateSetListener dateskt = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateskt();
            }

        };


        mskt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), dateskt, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final DatePickerDialog.OnDateSetListener dateut = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateut();
            }

        };

        mut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), dateut, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

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
        maddok = (Button) view.findViewById(R.id.addok);
               maddok.setOnClickListener(new View.OnClickListener() {
           @Override
             public void onClick(View v) {
                addFood();
                BasicFragment fragment = new BasicFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, fragment);
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




    private void updateskt() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        mskt.setText(sdf.format(myCalendar.getTime()));


    }

    private void updateut() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        mut.setText(sdf.format(myCalendar.getTime()));


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
                String fprice = mprice.getText().toString();
                String fut = mut.getText().toString();
                String fskt = mskt.getText().toString();

            if(fname == null || fprice == null || fut == null || fskt == null){

                Toast.makeText(getActivity(), "Empty lot!",
                        Toast.LENGTH_LONG).show();
            }else {
                Food food = new Food(fname, fskt, fut, fprice);
                mDatabase.child("kitchens").child(String.valueOf(LoginFragment.logkitchen))
                        .child("foods").child(String.valueOf(foodID))
                        .setValue(food);
            }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Handle possible errors.
            }
        });


    }

}
