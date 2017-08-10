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

public class ListAdapterShopping extends ArrayAdapter<DataModelShopping>   {

        private ArrayList<DataModelShopping> dataSetShop;
        Context mContextShop;

        // View lookup cache
        private static class ViewHolderShop {
            TextView txtNameshop;
            ImageView removeshop;
        }

        public ListAdapterShopping(ArrayList<DataModelShopping> data, Context context) {
            super(context, R.layout.shoppinglistrow, data);
            this.dataSetShop = data;
            this.mContextShop=context;

        }

        private int lastPosition = -1;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            DataModelShopping dataModelShopping = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            ViewHolderShop viewHolderShop; // view lookup cache stored in tag

            final View result;

            if (convertView == null) {

                viewHolderShop = new ViewHolderShop();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.shoppinglistrow, parent, false);
                viewHolderShop.txtNameshop = (TextView) convertView.findViewById(R.id.nameshop);
                viewHolderShop.removeshop = (ImageView) convertView.findViewById(R.id.removeshop);

                result=convertView;

                convertView.setTag(viewHolderShop);

            } else {
                viewHolderShop = (ViewHolderShop) convertView.getTag();
                result=convertView;
            }

            //Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
            // result.startAnimation(animation);
            lastPosition = position;

            viewHolderShop.txtNameshop.setText(dataModelShopping.getProductShop());
            //viewHolderShop.update.setOnClickListener(this);
            viewHolderShop.removeshop.setTag(position);
            // Return the completed view to render on screen
            return convertView;
        }
    }




