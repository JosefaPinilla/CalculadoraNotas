package unach.desarrollomovil.calculadoranotas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultadoActivity extends AppCompatActivity {

    TextView promedioTxt, estadoTxt;
    ImageView imgAprobado, imgReprobado;
    RatingBar ratingApp;
    Button salir, volver, historial;

    boolean calificado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        promedioTxt = findViewById(R.id.txtPromedio);
        estadoTxt = findViewById(R.id.txtEstado);

        imgAprobado = findViewById(R.id.aprobado);
        imgReprobado = findViewById(R.id.reprobado);

        ratingApp = findViewById(R.id.ratingApp);

        salir = findViewById(R.id.btSalir);
        volver = findViewById(R.id.btVolver);

        historial = findViewById(R.id.btHistorial);

        double promedio = getIntent().getDoubleExtra("promedio", 0);
        String estado = getIntent().getStringExtra("estado");

        promedioTxt.setText("Promedio: " + String.format("%.1f", promedio));
        estadoTxt.setText("Estado: " + estado);

        if (estado.equals("Aprobado")) {

            estadoTxt.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            imgAprobado.setVisibility(View.VISIBLE);

        } else {

            estadoTxt.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            imgReprobado.setVisibility(View.VISIBLE);

        }

        ratingApp.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {

            calificado = true;

            Toast.makeText(this,
                    "Calificación: " + rating + " estrellas",
                    Toast.LENGTH_SHORT).show();

        });

        salir.setOnClickListener(v -> {

            if (!calificado) {

                Toast.makeText(this,
                        "Debe calificar la aplicación antes de salir",
                        Toast.LENGTH_SHORT).show();

            } else {

                finishAffinity();
            }
        });

        volver.setOnClickListener(v -> {
            Intent intent = new Intent(ResultadoActivity.this, DatosActivity.class);
            startActivity(intent);
            finish();
        });

        historial = findViewById(R.id.btHistorial);

        historial.setOnClickListener(v -> {
            Intent intent = new Intent(ResultadoActivity.this, HistorialActivity.class);
            startActivity(intent);
        });
    }
}