package es.uma.sedmp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import es.uma.sedmp.models.Agenda;

/*
Se utilizará el patrón de diseño Modelo-Vista-Controlador, donde las vistas se encuentran
especificadas en los ficheros layout de recursos correspondientes a cada actividad, y cada actividad
actúa como controlador de la vista asociada.
Además, el Modelo(Agenda.java) será un objeto
compartido entre las dos actividades y permitirá gestionar los accesos y actualizaciones de los
datos de la agenda, proporcionando métodos para añadir contactos, eliminar contactos, así
como para acceder a los contactos almacenados.
 */

public class AddEntryActivity extends AppCompatActivity {
    private EditText nombreEditText;
    private EditText telefonoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        nombreEditText = findViewById(R.id.add_nombre_input);
        telefonoEditText = findViewById(R.id.add_telefono_input);
    }

    public void back(View view) {
        finish();
    }

    public void save(View view) {
        try {
            String persona = nombreEditText.getText().toString();
            String telefono = telefonoEditText.getText().toString();

            if (persona.isEmpty() || telefono.isEmpty()) {
                DialogExceptionHandler.showDialog(getString(R.string.add_no_vacio), this);
            } else {
                boolean success = Agenda.INSTANCE.putValue(persona, telefono);

                if (success) {
                    Toast.makeText(getBaseContext(), R.string.salvado, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), R.string.actualizado, Toast.LENGTH_SHORT).show();
                }
            }

        } catch (RuntimeException e) {
            DialogExceptionHandler.uncaughtException(e, this);
        }
    }
}
