package com.example.founq.gaitrecognition.mvp;

public interface Contract {

    interface Model{
        void postGait();
    }

    interface View{
        void show();
        void showGaitInfo();
    }

    interface Presenter{
        void pass();
        void getGaitInfo();
    }

}
