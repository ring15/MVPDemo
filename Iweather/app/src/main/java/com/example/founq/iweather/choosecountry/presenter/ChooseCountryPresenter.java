package com.example.founq.iweather.choosecountry.presenter;

import android.os.AsyncTask;

import com.example.founq.iweather.base.BasePresenter;
import com.example.founq.iweather.choosecountry.CountryContract;
import com.example.founq.iweather.choosecountry.model.ChooseCountryModel;
import com.example.founq.iweather.choosecountry.view.ChooseCountryActivity;
import com.example.founq.iweather.data.Utility;

import java.lang.ref.WeakReference;

public class ChooseCountryPresenter extends BasePresenter<ChooseCountryActivity> implements CountryContract.CountryPresenterInterface {


    CountryContract.CountryModelInterface chooseCountryModel = new ChooseCountryModel();

    @Override
    public void getModel(int code, int citycode) {
        CountryAsyncTask mCountryAsyncTask = new CountryAsyncTask(ChooseCountryPresenter.this);
        mCountryAsyncTask.execute(code, citycode);
    }

    private static class CountryAsyncTask extends AsyncTask<Integer, Void, Boolean> {

        private WeakReference<ChooseCountryPresenter> mPresenterWeakReference;
        private String strOther;

        public CountryAsyncTask(ChooseCountryPresenter presenter) {
            mPresenterWeakReference = new WeakReference<>(presenter);
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
            strOther = mPresenterWeakReference.get().chooseCountryModel.getCountry(integers[0], integers[1]);
            return Utility.handleCountyResponse(strOther, integers[1]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (mPresenterWeakReference.get().isAttach && aBoolean) {
                mPresenterWeakReference.get().view.get().show();
            }
        }
    }
}
