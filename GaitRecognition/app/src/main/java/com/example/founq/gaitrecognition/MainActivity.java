package com.example.founq.gaitrecognition;

import android.content.Context;
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

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends BaseActivity<MainPresenter> implements View.OnClickListener, Contract.View, SensorEventListener {

    private Button btnInput, btnRecognize, btnGet;
    private TextView test;
    private float X, Y, Z;
    private boolean isRecord = false;
    private SensorManager sensorManager;
    private Sensor accelerometer;


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

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_input:
                presenter.getGaitInfo(isRecord);
                isRecord = !isRecord;
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
        FileOutputStream fileOutputStream = null;
        BufferedWriter bufferedWriter = null;
        String fileName = "gait";
        try {
            fileOutputStream = openFileOutput(fileName, Context.MODE_APPEND);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write("加速度信息   X：" + X + "    Y:" + Y + "   Z:" + Z + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        test.setText("加速度信息   X：" + X + "    Y:" + Y + "   Z:" + Z);
    }

    @Override
    public void showGaitInfo() {
        /*rate的单位是microsecond，也就是10^-6。
        传感器事件给出的时间间隔*/
        sensorManager.registerListener(this, accelerometer, 2000000);
        btnInput.setText(R.string.input_gait_info_stop);
    }

    @Override
    public void stopRecord() {
        if (null != sensorManager && null != accelerometer) {
            sensorManager.unregisterListener(MainActivity.this, accelerometer);
            btnInput.setText(R.string.input_gait_info);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
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
