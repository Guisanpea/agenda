package es.uma.sedmp.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import es.uma.sedmp.DialogExceptionHandler;
import es.uma.sedmp.R;
import es.uma.sedmp.sensors.SensorsActivity;

public class AgendaActivity extends AppCompatActivity {
    private EditText nombre;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Map.Entry<String, String>> results;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        nombre = findViewById(R.id.main_nombre_input);
        recyclerView = findViewById(R.id.agenda_recycler_view);

        results = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new AgendaAdapter(results);
        recyclerView.setAdapter(mAdapter);
    }

    public void buscar(View view) {
        try {
            String persona = nombre.getText().toString();
            Map<String, String> busqueda = Agenda.INSTANCE.get(persona);

            if (!busqueda.isEmpty()) {
                updateResults(busqueda);
                mAdapter.notifyDataSetChanged();
                Toast.makeText(this.getBaseContext(), R.string.found, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this.getBaseContext(), R.string.no_result, Toast.LENGTH_SHORT).show();
            }
        } catch (RuntimeException e) {
            DialogExceptionHandler.uncaughtException(e, this);
        }
    }

    private void updateResults(Map<String, String> busqueda) {
        results.clear();
        results.addAll(busqueda.entrySet());
    }

    public void loadAddEntryView(View view) {
        try {
            Intent myIntent = new Intent(AgendaActivity.this, AddEntryActivity.class);
            AgendaActivity.this.startActivity(myIntent);
        } catch (RuntimeException e) {
            DialogExceptionHandler.uncaughtException(e, this);
        }
    }

    public void loadSensorsView(View view) {
        try {
            Intent myIntent = new Intent(AgendaActivity.this, SensorsActivity.class);
            AgendaActivity.this.startActivity(myIntent);
        } catch (RuntimeException e) {
            DialogExceptionHandler.uncaughtException(e, this);
        }
    }
}
