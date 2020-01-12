package es.uma.sedmp.sensors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import es.uma.sedmp.R;

import static android.hardware.SensorManager.SENSOR_DELAY_NORMAL;

public class SensorsActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<SensorWithValues> sensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        sensors = new ArrayList<>();
        recyclerView = findViewById(R.id.sensors_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new SensorsAdapter(sensors);
        recyclerView.setAdapter(mAdapter);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        for (Sensor sensor : sensorManager.getSensorList(Sensor.TYPE_ALL)) {
            sensors.add(new SensorWithValues(sensor));
        }

        for (SensorWithValues sensor : sensors) {
            sensorManager.registerListener(this, sensor.getSensor(), SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        SensorWithValues eventedSensor = new SensorWithValues(event.sensor, event.values);
        boolean sensorNotFound = true;
        int i = 0;

        while (sensorNotFound && i < sensors.size()) {
            SensorWithValues sensor = sensors.get(i);

            if (sensor.equals(eventedSensor)) {
                sensors.set(i, eventedSensor);
                sensorNotFound = false;
            }
            i++;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
