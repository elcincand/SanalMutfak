package com.android.sanalmutfak;

/**
 * Created by elcin on 8/10/17.
 */

public class DataModelBasic {

    String bproduct;
    String btuketim;
    String bcounter;

    public DataModelBasic(String productbasic, String tuketimbasic, String counterbasic) {
        this.bproduct=productbasic;
        this.btuketim=tuketimbasic;
        this.bcounter=counterbasic;

    }

    public String getProductBasic() {
        return bproduct;
    }

    public String getTuketimBasic() {
        return btuketim;
    }

    public String getCounterBasic() {
        return bcounter;
    }



}
