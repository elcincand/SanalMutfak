package com.android.sanalmutfak;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImprovementFragment extends Fragment {

private ImageButton madd;
    private ImageButton star;
    private ImageButton logoff;

    public ImprovementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_improvement, container, false);
        if (container != null) {
            container.removeAllViews();
        }
        madd =(ImageButton) view.findViewById(R.id.addbar);


        madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



        return view;
    }

}
