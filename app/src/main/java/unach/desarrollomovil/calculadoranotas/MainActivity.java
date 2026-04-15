package unach.desarrollomovil.calculadoranotas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText nota1, nota2, nota3, nota4;
    Button calcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nota1 = findViewById(R.id.nota1);
        nota2 = findViewById(R.id.nota2);
        nota3 = findViewById(R.id.nota3);
        nota4 = findViewById(R.id.nota4);

        calcular = findViewById(R.id.btCalcular);

        calcular.setOnClickListener(view -> calcularNota());
    }

    private void calcularNota() {

        if(nota1.getText().toString().isEmpty() ||
                nota2.getText().toString().isEmpty() ||
                nota3.getText().toString().isEmpty() ||
                nota4.getText().toString().isEmpty()){

            Toast.makeText(this,"Debe ingresar todas las notas",Toast.LENGTH_SHORT).show();
            return;
        }

        double n1 = Double.parseDouble(nota1.getText().toString());
        double n2 = Double.parseDouble(nota2.getText().toString());
        double n3 = Double.parseDouble(nota3.getText().toString());
        double n4 = Double.parseDouble(nota4.getText().toString());

        double finalNota = (n1*0.10) + (n2*0.20) + (n3*0.30) + (n4*0.40);

        String estado;

        if(finalNota >= 51){
            estado = "Aprobado";
        }else{
            estado = "Reprobado";
        }

        Toast.makeText(this,
                "Nota Final: "+finalNota+" - "+estado,
                Toast.LENGTH_LONG).show();
    }
}