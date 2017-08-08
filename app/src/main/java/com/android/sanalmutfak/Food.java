package com.android.sanalmutfak;

/**
 * Created by elcin on 8/7/17.
 */

public class Food {

        public String foodname;
        public double price;
        public String skt;
        public String ut;

        public Food(String foodname, String skt, String ut, double price ) {
            this.foodname = foodname;
            this.price =price;
            this.skt = skt;
            this.ut =ut;

        }



        public String getFoodname() {
            return foodname;
        }
        public double getPrice() {
            return price;
        }
        public String getSkt() {
            return skt;
        }
        public String getUt() {
            return ut;
        }

    }


