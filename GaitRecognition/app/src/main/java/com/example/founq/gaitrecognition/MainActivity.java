package com.example.founq.gaitrecognition;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.founq.gaitrecognition.base.BaseActivity;
import com.example.founq.gaitrecognition.mvp.Contract;
import com.example.founq.gaitrecognition.mvp.prsenter.MainPresenter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends BaseActivity<MainPresenter> implements View.OnClickListener, Contract.View, SensorEventListener {

    private Button btnInput, btnRecognize, btnGet;
    private TextView test;
    private float X, Y, Z;
    private boolean isRecord = false;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private static final int mRequestCode = 0x01;
    private File newFile = null;


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
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, mRequestCode);
        } else {
            presenter.writeToFile(newFile, "加速度信息   X：" + X + "    Y:" + Y + "   Z:" + Z + "\n");
        }
        test.setText("加速度信息   X：" + X + "    Y:" + Y + "   Z:" + Z);
    }

    @Override
    public void showGaitInfo() {
        /*rate的单位是microsecond，也就是10^-6。
        传感器事件给出的时间间隔*/
        sensorManager.registerListener(this, accelerometer, 2000000);
        btnInput.setText(R.string.input_gait_info_stop);
        Date date = new Date();
        String fileName = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(date.getTime());
        newFile = new File(getExternalFilesDir(""), fileName + ".txt");
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case mRequestCode:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    presenter.writeToFile(newFile, "加速度信息   X：" + X + "    Y:" + Y + "   Z:" + Z + "\n");
                } else {
                    Toast.makeText(MainActivity.this, "you denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
