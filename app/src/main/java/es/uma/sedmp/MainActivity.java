package es.uma.sedmp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import es.uma.sedmp.models.Agenda;

public class MainActivity extends AppCompatActivity {
    private EditText nombre;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Map.Entry<String, String>> results;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.main_nombre_input);
        recyclerView = findViewById(R.id.my_recycler_view);

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
            Intent myIntent = new Intent(MainActivity.this, AddEntryActivity.class);
            MainActivity.this.startActivity(myIntent);
        } catch (RuntimeException e) {
            DialogExceptionHandler.uncaughtException(e, this);
        }
    }
}
