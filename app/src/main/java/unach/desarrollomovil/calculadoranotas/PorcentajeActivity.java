package unach.desarrollomovil.calculadoranotas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PorcentajeActivity extends AppCompatActivity {

    EditText p1, p2, p3, p4;
    Button guardar;

    // porcentajes por defecto
    public static double porcentaje1 = 10;
    public static double porcentaje2 = 20;
    public static double porcentaje3 = 30;
    public static double porcentaje4 = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porcentaje);

        p1 = findViewById(R.id.p1);
        p2 = findViewById(R.id.p2);
        p3 = findViewById(R.id.p3);
        p4 = findViewById(R.id.p4);

        guardar = findViewById(R.id.btGuardar);

        // mostrar valores actuales
        p1.setText(String.valueOf(porcentaje1));
        p2.setText(String.valueOf(porcentaje2));
        p3.setText(String.valueOf(porcentaje3));
        p4.setText(String.valueOf(porcentaje4));

        guardar.setOnClickListener(v -> guardarPorcentajes());
    }

    private void guardarPorcentajes(){

        if(p1.getText().toString().isEmpty() ||
                p2.getText().toString().isEmpty() ||
                p3.getText().toString().isEmpty() ||
                p4.getText().toString().isEmpty()){

            Toast.makeText(this,"Debe ingresar todos los porcentajes",Toast.LENGTH_SHORT).show();
            return;
        }

        double v1 = Double.parseDouble(p1.getText().toString());
        double v2 = Double.parseDouble(p2.getText().toString());
        double v3 = Double.parseDouble(p3.getText().toString());
        double v4 = Double.parseDouble(p4.getText().toString());

        double suma = v1 + v2 + v3 + v4;

        if(suma != 100){
            Toast.makeText(this,"La suma debe ser 100%",Toast.LENGTH_SHORT).show();
            return;
        }

        porcentaje1 = v1;
        porcentaje2 = v2;
        porcentaje3 = v3;
        porcentaje4 = v4;

        Toast.makeText(this,"Porcentajes guardados",Toast.LENGTH_SHORT).show();

        finish(); // vuelve a MainActivity
    }
}