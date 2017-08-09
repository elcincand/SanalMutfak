package com.android.sanalmutfak;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileBlankFragment extends Fragment {

private ImageButton madd;

    public ProfileBlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        View view = inflater.inflate(R.layout.fragment_profile_blank, container, false);
        madd = (ImageButton) view.findViewById(R.id.foodAddButton);


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

    return view;
    }
}
