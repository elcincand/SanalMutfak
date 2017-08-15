package com.android.sanalmutfak;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by elcin on 8/10/17.
 */

public class ListAdapterBasic extends ArrayAdapter<DataModelBasic> implements View.OnClickListener{
    private BasicFragment fragment;
    private ArrayList<DataModelBasic> dataSetBasic;
    Context mContextBasic;
    DatabaseReference mbasicRef;
    DatabaseReference mshopRef;


    // View lookup cache
    private static class ViewHolderBasic{
        TextView txtName;
        TextView txtTarih;
        TextView txtCountDown;
        ImageButton remove;
        ImageButton addshop;



    }

    public ListAdapterBasic(ArrayList<DataModelBasic> data, Context context, BasicFragment fragment) {
        super(context, R.layout.itemlistrow, data);
        this.dataSetBasic = data;
        this.mContextBasic=context;
        this.fragment = fragment;
    }

    @Override
    public void onClick(View v) {

        final int position=(Integer) v.getTag();
        Object object= getItem(position);
        DataModelBasic dataModel=(DataModelBasic)object;

        switch (v.getId())
        {
            case R.id.item_remove:
                mshopRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sanalmutfak-d81ad.firebaseio.com/kitchens/"
                        + LoginFragment.logkitchen + "/shoplist");

                AlertDialog.Builder adb = new AlertDialog.Builder(getContext());
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete this item?");
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        fragment.removeItemBasic(position);
                    }
                });
                adb.show();

                break;

            case R.id.item_addshop:

                AlertDialog.Builder adb2 = new AlertDialog.Builder(getContext());
                adb2.setTitle("Add to shopping list?");
                adb2.setMessage("Are you sure you want to add this item to the shopping list?");
                adb2.setNegativeButton("Cancel", null);
                adb2.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        fragment.addShopListBasic(position);
                    }
                });
                adb2.show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataModelBasic dataModelbasic = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolderBasic viewHolderBasic; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolderBasic = new ViewHolderBasic();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.itemlistrow, parent, false);
            viewHolderBasic.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolderBasic.txtTarih = (TextView) convertView.findViewById(R.id.tuketim);
            //viewHolderBasic.txtCountDown = (TextView) convertView.findViewById(R.id.countDown);
            viewHolderBasic.remove = (ImageButton) convertView.findViewById(R.id.item_remove);
            viewHolderBasic.addshop = (ImageButton) convertView.findViewById(R.id.item_addshop);

            result=convertView;

            convertView.setTag(viewHolderBasic);

        } else {
            viewHolderBasic = (ViewHolderBasic) convertView.getTag();
            result=convertView;
        }


        //Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        // result.startAnimation(animation);
        lastPosition = position;

        viewHolderBasic.txtName.setText(dataModelbasic.getProductBasic());
        viewHolderBasic.txtTarih.setText(dataModelbasic.getTuketimBasic());
       // viewHolderBasic.txtCountDown.setText(dataModelbasic.getCounterBasic());
        viewHolderBasic.remove.setOnClickListener(this);
        viewHolderBasic.remove.setTag(position);
        viewHolderBasic.addshop.setOnClickListener(this);
        viewHolderBasic.addshop.setTag(position);
        // Return the completed view to render on screen



        return convertView;
    }

}
