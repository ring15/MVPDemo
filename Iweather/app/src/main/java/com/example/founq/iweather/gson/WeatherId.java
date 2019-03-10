package com.example.founq.iweather.gson;

public class WeatherId {

    private String fa;/*天气标识00：晴*/

    private String fb;/*天气标识53：霾 如果fa不等于fb，说明是组合天气*/

    public void setFa(String fa) {
        this.fa = fa;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }


    public String getFa() {
        return fa;
    }

    public String getFb() {
        return fb;
    }


}
