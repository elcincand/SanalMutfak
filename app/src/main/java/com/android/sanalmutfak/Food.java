package com.android.sanalmutfak;

/**
 * Created by elcin on 8/7/17.
 */

public class Food {

        public String foodname;
        public String price;
        public String skt;
        public String ut;

        public Food(String foodname, String skt, String ut, String price ) {
            this.foodname = foodname;
            this.price =price;
            this.skt = skt;
            this.ut =ut;

        }



        public String getFoodname() {
            return foodname;
        }
        public String getPrice() {
            return price;
        }
        public String getSkt() {
            return skt;
        }
        public String getUt() {
            return ut;
        }

    }


