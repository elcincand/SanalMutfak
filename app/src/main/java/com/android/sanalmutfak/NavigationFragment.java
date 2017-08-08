package com.android.sanalmutfak;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends Fragment {
private ImageButton madd;
    private ImageButton star;
    private ImageButton logoff;
    private Button myprofile;
    private Button myinven;
    private Button myimprov;
    private Button myshopping;

    public NavigationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_navigation, container, false);

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

        star = (ImageButton) view.findViewById(R.id.menu);

        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavigationFragment fragment = new NavigationFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        myprofile = (Button) view.findViewById(R.id.myprofile);
        myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProfileBlankFragment fragment = new ProfileBlankFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        myinven= (Button) view.findViewById(R.id.myinventory);
        myinven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BasicFragment fragment = new BasicFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        myimprov = (Button) view.findViewById(R.id.myimprovement);
        myimprov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImprovementFragment fragment = new ImprovementFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        myshopping = (Button) view.findViewById(R.id.myshopp);
        myshopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShoppingFragment fragment = new ShoppingFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        logoff = (ImageButton) view.findViewById(R.id.logout);
        logoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeningFragment fragment = new OpeningFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

}
