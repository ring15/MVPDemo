package com.example.founq.gaitrecognition.mvp.prsenter;

import com.example.founq.gaitrecognition.MainActivity;
import com.example.founq.gaitrecognition.base.BasePresenter;
import com.example.founq.gaitrecognition.mvp.Contract;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainPresenter extends BasePresenter<MainActivity> implements Contract.Presenter {

    private BufferedWriter mWriter = null;
    private FileWriter mFileWriter = null;

    @Override
    public void pass() {
        view.get().show();
    }

    @Override
    public void getGaitInfo(boolean isRecord) {
        if (isRecord) {
            view.get().stopRecord();
            if (mWriter != null) {
                try {
                    mWriter.close();
                    mFileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            view.get().showGaitInfo();
        }
    }

    @Override
    public void writeToFile(File file, String content) {
        try {
            mFileWriter = new FileWriter(file, true);
            mWriter = new BufferedWriter(mFileWriter);
            mWriter.write(content);
            mWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
