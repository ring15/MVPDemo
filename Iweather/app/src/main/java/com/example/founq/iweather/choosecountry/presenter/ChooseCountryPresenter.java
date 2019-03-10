package com.example.founq.iweather.choosecountry.presenter;

import android.os.AsyncTask;

import com.example.founq.iweather.base.BasePresenter;
import com.example.founq.iweather.choosecountry.CountryContract;
import com.example.founq.iweather.choosecountry.model.ChooseCountryModel;
import com.example.founq.iweather.choosecountry.view.ChooseCountryActivity;
import com.example.founq.iweather.data.Utility;

public class ChooseCountryPresenter extends BasePresenter<ChooseCountryActivity> implements CountryContract.CountryPresenterInterface{


    CountryContract.CountryModelInterface chooseCountryModel = new ChooseCountryModel();
    private String strOther;

    @Override
    public void getModel(int code,int citycode) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                strOther = chooseCountryModel.getCountry(code,citycode);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (Utility.handleCountyResponse(strOther, citycode)) {
                    view.get().show();
                }
            }

        }.execute();


    }
}
