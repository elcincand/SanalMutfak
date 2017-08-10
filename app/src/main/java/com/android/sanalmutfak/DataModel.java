package com.android.sanalmutfak;

/**
 * Created by elcin on 8/10/17.
 */

public class DataModel {

        String product;
        String tuketim;
        String counter;
        String feature;

        public DataModel(String product, String tuketim, String counter, String feature ) {
            this.product=product;
            this.tuketim=tuketim;
            this.counter=counter;
            this.feature=feature;

        }

        public String getProduct() {
            return product;
        }

        public String getTuketim() {
            return tuketim;
        }

        public String getCounter() {
            return counter;
        }

        public String getFeature() {
            return feature;
        }


}
