package unach.desarrollomovil.calculadoranotas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DatosActivity extends AppCompatActivity {

    EditText etNombre;
    CheckBox cbSinNombre;
    Spinner spAsignatura;
    Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        etNombre = findViewById(R.id.etNombre);
        cbSinNombre = findViewById(R.id.cbSinNombre);
        spAsignatura = findViewById(R.id.spAsignatura);
        btnSiguiente = findViewById(R.id.btnSiguiente);

        ArrayList<String> asignaturas = new ArrayList<>();
        asignaturas.add("Matemática");
        asignaturas.add("Lenguaje");
        asignaturas.add("Ciencias");
        asignaturas.add("Historia");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                asignaturas
        );

        spAsignatura.setAdapter(adapter);

        cbSinNombre.setOnClickListener(v -> {
            if (cbSinNombre.isChecked()) {
                etNombre.setText("");
                etNombre.setEnabled(false);
            } else {
                etNombre.setEnabled(true);
            }
        });

        btnSiguiente.setOnClickListener(v -> {

            String nombre = etNombre.getText().toString().trim();
            String asignatura = spAsignatura.getSelectedItem().toString();
            boolean sinNombre = cbSinNombre.isChecked();

            if (nombre.isEmpty() && !sinNombre) {
                Toast.makeText(this,
                        "Ingresa nombre o marca continuar sin nombre",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            String nombreFinal = sinNombre ? "Sin nombre" : nombre;

            Intent intent = new Intent(DatosActivity.this, MainActivity.class);
            intent.putExtra("nombre", nombreFinal);
            intent.putExtra("asignatura", asignatura);
            startActivity(intent);
        });
    }
}