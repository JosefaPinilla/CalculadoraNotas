package unach.desarrollomovil.calculadoranotas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText nota1, nota2, nota3, nota4;
    Button calcular, configurar;
    ProgressBar progreso;

    RadioButton radio100, radio7;

    String nombre, asignatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nota1 = findViewById(R.id.nota1);
        nota2 = findViewById(R.id.nota2);
        nota3 = findViewById(R.id.nota3);
        nota4 = findViewById(R.id.nota4);

        calcular = findViewById(R.id.btCalcular);
        configurar = findViewById(R.id.btConfigurar);
        progreso = findViewById(R.id.progreso);

        radio100 = findViewById(R.id.radio100);
        radio7 = findViewById(R.id.radio7);

        nombre = getIntent().getStringExtra("nombre");
        asignatura = getIntent().getStringExtra("asignatura");

        calcular.setOnClickListener(v -> iniciarProgreso());

        configurar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PorcentajeActivity.class);
            startActivity(intent);
        });

        actualizarPorcentajes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizarPorcentajes();
    }

    private void actualizarPorcentajes() {
        nota1.setHint("Nota 1 (" + PorcentajeActivity.porcentaje1 + "%)");
        nota2.setHint("Nota 2 (" + PorcentajeActivity.porcentaje2 + "%)");
        nota3.setHint("Nota 3 (" + PorcentajeActivity.porcentaje3 + "%)");
        nota4.setHint("Nota 4 (" + PorcentajeActivity.porcentaje4 + "%)");
    }

    private void iniciarProgreso() {

        progreso.setVisibility(View.VISIBLE);
        progreso.setProgress(0);

        new Thread(() -> {

            for (int i = 0; i <= 100; i += 10) {

                int valor = i;

                runOnUiThread(() -> progreso.setProgress(valor));

                try {
                    Thread.sleep(80);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            runOnUiThread(() -> {
                progreso.setVisibility(View.GONE);
                calcularPromedio();
            });

        }).start();
    }

    private void calcularPromedio() {

        if (nota1.getText().toString().isEmpty() ||
                nota2.getText().toString().isEmpty() ||
                nota3.getText().toString().isEmpty() ||
                nota4.getText().toString().isEmpty()) {

            Toast.makeText(this, "Debe ingresar todas las notas", Toast.LENGTH_SHORT).show();
            return;
        }

        double n1 = Double.parseDouble(nota1.getText().toString());
        double n2 = Double.parseDouble(nota2.getText().toString());
        double n3 = Double.parseDouble(nota3.getText().toString());
        double n4 = Double.parseDouble(nota4.getText().toString());

        if (radio100.isChecked()) {

            if (n1 < 1 || n1 > 100 ||
                    n2 < 1 || n2 > 100 ||
                    n3 < 1 || n3 > 100 ||
                    n4 < 1 || n4 > 100) {

                Toast.makeText(this, "Notas deben estar entre 1 y 100", Toast.LENGTH_SHORT).show();
                return;
            }

        } else {

            if (n1 < 1.0 || n1 > 7.0 ||
                    n2 < 1.0 || n2 > 7.0 ||
                    n3 < 1.0 || n3 > 7.0 ||
                    n4 < 1.0 || n4 > 7.0) {

                Toast.makeText(this, "Notas deben estar entre 1.0 y 7.0", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        double promedio =
                (n1 * PorcentajeActivity.porcentaje1 / 100) +
                        (n2 * PorcentajeActivity.porcentaje2 / 100) +
                        (n3 * PorcentajeActivity.porcentaje3 / 100) +
                        (n4 * PorcentajeActivity.porcentaje4 / 100);

        promedio = Math.round(promedio * 10) / 10.0;

        String estado = (radio100.isChecked() ? promedio >= 51 : promedio >= 4)
                ? "Aprobado"
                : "Reprobado";

        String nombreFinal = nombre;
        String asignaturaFinal = asignatura;

        if (nombreFinal == null) nombreFinal = "Sin nombre";
        if (asignaturaFinal == null) asignaturaFinal = "Sin asignatura";

        DataHolder.listaEstudiantes.add(
                new Estudiante(nombreFinal, asignaturaFinal, estado, promedio)
        );

        Intent intent = new Intent(MainActivity.this, ResultadoActivity.class);
        intent.putExtra("promedio", promedio);
        intent.putExtra("estado", estado);
        startActivity(intent);
    }
}