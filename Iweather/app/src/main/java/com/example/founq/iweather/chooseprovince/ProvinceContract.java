package com.example.founq.iweather.chooseprovince;

public interface ProvinceContract {

    interface ProvinceModelInterface {
        String getProvince();
    }

    interface ProvincePresenterInterface {
        void getModel();
    }

    interface ProvinceViewInterface {
        void show();
    }

}
