package com.example.founq.iweather.choosecity.presenter;

import android.os.AsyncTask;

import com.example.founq.iweather.base.BasePresenter;
import com.example.founq.iweather.choosecity.CityContract;
import com.example.founq.iweather.choosecity.model.ChooseCityModel;
import com.example.founq.iweather.choosecity.view.ChooseCityActivity;
import com.example.founq.iweather.data.Utility;

import java.lang.ref.WeakReference;

public class ChooseCityPresenter extends BasePresenter<ChooseCityActivity> implements CityContract.CityPresenterInterface {


    CityContract.CityModelInterface chooseCityModel = new ChooseCityModel();

    @Override
    public void getModel(int code) {

        CityAsyncTask mCityAsyncTask = new CityAsyncTask(ChooseCityPresenter.this);
        mCityAsyncTask.execute(code);
    }

    private static class CityAsyncTask extends AsyncTask<Integer, Void, Boolean>{

        private WeakReference<ChooseCityPresenter> mPresenterWeakReference;
        private String strOther;

        public CityAsyncTask(ChooseCityPresenter presenter){
            mPresenterWeakReference = new WeakReference<>(presenter);
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
            strOther = mPresenterWeakReference.get().chooseCityModel.getCity(integers[0]);
            return Utility.handleCityResponse(strOther, integers[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean){
                mPresenterWeakReference.get().view.get().show();
            }
        }
    }
}
