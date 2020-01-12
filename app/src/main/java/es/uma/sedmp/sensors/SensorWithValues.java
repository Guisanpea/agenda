package es.uma.sedmp.sensors;

import android.hardware.Sensor;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;

public class SensorWithValues {
    private Sensor sensor;
    private float[] values;

    SensorWithValues(Sensor sensor) {
        this.sensor = sensor;
        this.values = new float[]{};
    }

    SensorWithValues(Sensor sensor, float[] values) {
        this.values = values;
        this.sensor = sensor;
    }

    public void setValues(float[] values) {
        this.values = values;
    }

    public float[] getValues() {
        return values;
    }

    public Sensor getSensor() {
        return sensor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorWithValues that = (SensorWithValues) o;
        return that.sensor.getName().equals(sensor.getName())
                && that.sensor.getVendor().equals(sensor.getVendor());
    }

    @Override
    public int hashCode() {
        return (sensor.getName() + sensor.getVendor().hashCode()).hashCode();
    }
}
