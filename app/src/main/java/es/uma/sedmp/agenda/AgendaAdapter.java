package es.uma.sedmp.agenda;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

import es.uma.sedmp.R;

class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.ResultHolder> {
    private List<Map.Entry<String, String>> results;

    static class ResultHolder extends RecyclerView.ViewHolder {
        TextView person;
        TextView telephone;
        ImageButton call;
        Context context;

        ResultHolder(View v) {
            super(v);
            person = v.findViewById(R.id.firstLine);
            telephone = v.findViewById(R.id.secondLine);
            call = v.findViewById(R.id.icon);
            context = v.getContext();
        }

        void bind(final Map.Entry<String, String> entry) {
            person.setText(entry.getKey());
            telephone.setText(entry.getValue());
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View l) {
                    String uri = "tel:" + entry.getValue().trim();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(uri));
                    context.startActivity(intent);
                }
            });
        }
    }

    AgendaAdapter(List<Map.Entry<String, String>> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);

        return new ResultHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultHolder holder, int position) {
        holder.bind(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}
