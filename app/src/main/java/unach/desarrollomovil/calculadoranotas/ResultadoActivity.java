package unach.desarrollomovil.calculadoranotas;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultadoActivity extends AppCompatActivity {

    TextView promedioTxt, estadoTxt;
    ImageView imgAprobado, imgReprobado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        promedioTxt = findViewById(R.id.txtPromedio);
        estadoTxt = findViewById(R.id.txtEstado);

        imgAprobado = findViewById(R.id.aprobado);
        imgReprobado = findViewById(R.id.reprobado);

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
    }
}