package com.android.sanalmutfak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by elcin on 8/10/17.
 */

public class ListAdapterBasic extends ArrayAdapter<DataModelBasic> {

        private ArrayList<DataModelBasic> dataSetBasic;
        Context mContextBasic;



    // View lookup cache
        private static class ViewHolderBasic {
            TextView txtName;
            TextView txtTarih;
            TextView txtCountDown;
            ImageView update;
        }

        public ListAdapterBasic(ArrayList<DataModelBasic> data, Context context) {
            super(context, R.layout.itemlistrow, data);
            this.dataSetBasic = data;
            this.mContextBasic=context;

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
                viewHolderBasic.txtCountDown = (TextView) convertView.findViewById(R.id.countDown);
                viewHolderBasic.update = (ImageView) convertView.findViewById(R.id.item_info);

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
            viewHolderBasic.txtCountDown.setText(dataModelbasic.getCounterBasic());
            //viewHolderBasic.update.setOnClickListener(this);
            viewHolderBasic.update.setTag(position);
            // Return the completed view to render on screen
            return convertView;
        }
    }


