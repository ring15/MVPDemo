package com.example.founq.mvpdemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.founq.mvpdemo.util.FileUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener ,SensorEventListener {

    TextView tvShow;
    Button btnGet;

    SensorManager sensorManager;
    String string = "加速度\n";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvShow = findViewById(R.id.tv_show);
        btnGet = findViewById(R.id.btn_get);
        btnGet.setOnClickListener(this);

        //获取sensorManager对象
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_FASTEST);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get:
                tvShow.setText(string);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                string  = "X:"+event.values[0] + "  "
                        +"Y:"+event.values[1] + "  "
                        +"Z:"+event.values[2] + "\n";
                Log.e("数据:",string);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }
}
