package com.example.founq.gaitrecognition.mvp;

import com.example.founq.gaitrecognition.base.Listener;

import java.io.File;

public interface Contract {

    interface Model {
        void postGait(File file, Listener listener);
    }

    interface View {
        void show();

        void savedGaitInfo();

        void showGaitInfo(String result);

        void stopRecord();
    }

    interface Presenter {
        void pass();

        void saveGaitInfo(boolean isRecord);

        void gaitInfo(File file);

        void writeToFile(File file, String content);

        void copyFile(File oldFile, File newFile);
    }

}
