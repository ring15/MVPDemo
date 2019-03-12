package com.example.founq.gaitrecognition;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.founq.gaitrecognition.base.BaseActivity;
import com.example.founq.gaitrecognition.mvp.Contract;
import com.example.founq.gaitrecognition.mvp.prsenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements View.OnClickListener, Contract.View , SensorEventListener {

    Button btnInput, btnRecognize, btnGet;
    TextView test;
    float X, Y, Z;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInput = findViewById(R.id.btn_input);
        btnRecognize = findViewById(R.id.btn_recognize);
        btnGet = findViewById(R.id.btn_get);
        test = findViewById(R.id.test);
        btnInput.setOnClickListener(this);
        btnRecognize.setOnClickListener(this);
        btnGet.setOnClickListener(this);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_input:
                presenter.getGaitInfo();
                break;
            case R.id.btn_recognize:
                break;
            case R.id.btn_get:
                break;
            default:
                break;
        }
    }

    @Override
    public void show() {
        test.setText("加速度信息   X："+ X +"    Y:" + Y + "   Z:"+Z);
    }

    @Override
    public void showGaitInfo() {
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,accelerometer,2000);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            X = event.values[0];
            Y = event.values[1];
            Z = event.values[2];
            presenter.pass();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
