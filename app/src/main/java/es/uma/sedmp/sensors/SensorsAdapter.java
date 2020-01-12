package es.uma.sedmp.sensors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.uma.sedmp.R;

class SensorsAdapter extends RecyclerView.Adapter<SensorsAdapter.ResultHolder> {
    private final List<SensorWithValues> sensors;

    static class ResultHolder extends RecyclerView.ViewHolder {
        TextView sensorName;
        TextView sensorDetails;
        ImageButton sensorInfo;
        Context context;

        ResultHolder(View v) {
            super(v);
            sensorName = v.findViewById(R.id.firstInfo);
            sensorDetails = v.findViewById(R.id.secondInfo);
            sensorInfo = v.findViewById(R.id.sensor_icon);
            context = v.getContext();
        }

        void bind(final SensorWithValues sensor) {
            sensorName.setText(sensor.getSensor().getName());
            String vendorMessage = context.getString(R.string.vendor, sensor.getSensor().getVendor());
            sensorDetails.setText(vendorMessage);
            sensorInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View l) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    System.out.println(sensor.getValues().length);

                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < sensor.getValues().length; i++) {
                        sb.append("Value ").append(i).append(": ").append(sensor.getValues()[i]).append("\n");
                    }

                    builder.setTitle(sensor.getSensor().getName())
                            .setMessage(sb.toString());

                    builder.create().show();
                }
            });
        }
    }

    SensorsAdapter(List<SensorWithValues> sensors) {
        this.sensors = sensors;
    }

    @NonNull
    @Override
    public SensorsAdapter.ResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sensor_item, parent, false);

        return new SensorsAdapter.ResultHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultHolder holder, int position) {
        holder.bind(sensors.get(position));
    }

    @Override
    public int getItemCount() {
        return sensors.size();
    }
}
