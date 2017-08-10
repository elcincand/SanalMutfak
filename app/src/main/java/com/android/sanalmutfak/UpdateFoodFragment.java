package com.android.sanalmutfak;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFoodFragment extends Fragment {
    private Button mupdate;
    private Button mremove;
    private Button maddshop;


    public UpdateFoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }

            View view = inflater.inflate(R.layout.fragment_update_food, container, false);
            mremove = (Button) view.findViewById(R.id.remove);
            mupdate = (Button) view.findViewById(R.id.update);
            maddshop = (Button) view.findViewById(R.id.addShoppingList);


        mupdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                BasicFragment fragment = new BasicFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

            mremove.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    BasicFragment fragment = new BasicFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });

        maddshop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ShoppingFragment fragment = new ShoppingFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        return view;
    }


    public void updateFood(){

    }

    public void removeFood(){

    }

    public void addShoppingFood(){

    }

}
