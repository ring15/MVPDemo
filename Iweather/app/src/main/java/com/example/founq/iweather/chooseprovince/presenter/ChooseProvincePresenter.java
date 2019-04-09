package com.example.founq.iweather.chooseprovince.presenter;

import android.os.AsyncTask;

import com.example.founq.iweather.base.BasePresenter;
import com.example.founq.iweather.chooseprovince.ProvinceContract;
import com.example.founq.iweather.chooseprovince.model.ChooseProvinceModel;
import com.example.founq.iweather.chooseprovince.view.ChooseProvinceActivity;
import com.example.founq.iweather.data.Utility;

import java.lang.ref.WeakReference;

public class ChooseProvincePresenter extends BasePresenter<ChooseProvinceActivity> implements ProvinceContract.ProvincePresenterInterface {

    ProvinceContract.ProvinceModelInterface chooseProvinceModel = new ChooseProvinceModel();

    @Override
    public void getModel() {

        ProvinceAsyncTask mProvinceAsyncTask = new ProvinceAsyncTask(ChooseProvincePresenter.this);
        mProvinceAsyncTask.execute();
    }

    private static class ProvinceAsyncTask extends AsyncTask<Void, Void, Boolean> {

        private WeakReference<ChooseProvincePresenter> mPresenterWeakReference;
        private String strOther;

        public ProvinceAsyncTask(ChooseProvincePresenter presenter) {
            mPresenterWeakReference = new WeakReference<>(presenter);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            strOther = mPresenterWeakReference.get().chooseProvinceModel.getProvince();
            return Utility.handleProvinceResponse(strOther);
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
