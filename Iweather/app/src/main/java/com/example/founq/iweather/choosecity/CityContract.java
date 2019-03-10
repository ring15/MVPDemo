package com.example.founq.iweather.choosecity;

public interface CityContract {


    interface CityModelInterface {
        String getCity(int code);
    }

    interface CityPresenterInterface {
        void getModel(int code);
    }

    interface CityViewInterface {
        void show();
    }

}
