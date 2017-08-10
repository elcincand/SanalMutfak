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

public class ListAdapter extends ArrayAdapter<DataModel> implements View.OnClickListener {

        private ArrayList<DataModel> dataSet;
        Context mContext;

    @Override
    public void onClick(View view) {

    }

    // View lookup cache
        private static class ViewHolder {
            TextView txtName;
            TextView txtTarih;
            TextView txtCountDown;
            ImageView update;
        }

        public ListAdapter(ArrayList<DataModel> data, Context context) {
            super(context, R.layout.itemlistrow, data);
            this.dataSet = data;
            this.mContext=context;

        }

     /*   @Override
        public void onClick(View v) {

            int position=(Integer) v.getTag();
            Object object= getItem(position);
            DataModel dataModel=(DataModel)object;

            switch (v.getId())
            {
                case R.id.item_info:
                    UpdateFoodFragment fragment = new UpdateFoodFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    break;
            }
        }*/

        private int lastPosition = -1;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            DataModel dataModel = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            ViewHolder viewHolder; // view lookup cache stored in tag

            final View result;

            if (convertView == null) {

                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.itemlistrow, parent, false);
                viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
                viewHolder.txtTarih = (TextView) convertView.findViewById(R.id.tuketim);
                viewHolder.txtCountDown = (TextView) convertView.findViewById(R.id.countDown);
                viewHolder.update = (ImageView) convertView.findViewById(R.id.item_info);

                result=convertView;

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                result=convertView;
            }

            //Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
           // result.startAnimation(animation);
            lastPosition = position;

            viewHolder.txtName.setText(dataModel.getProduct());
            viewHolder.txtTarih.setText(dataModel.getTuketim());
            viewHolder.txtCountDown.setText(dataModel.getCounter());
            viewHolder.update.setOnClickListener(this);
            viewHolder.update.setTag(position);
            // Return the completed view to render on screen
            return convertView;
        }
    }


