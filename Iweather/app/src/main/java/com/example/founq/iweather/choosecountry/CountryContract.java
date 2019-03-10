package com.example.founq.iweather.choosecountry;

public interface CountryContract {


    interface CountryModelInterface {
        String getCountry(int code, int citycode);
    }

    interface CountryPresenterInterface {
        void getModel(int code,int citycode);
    }

    interface CountryViewInterface {
        void show();
    }

}
