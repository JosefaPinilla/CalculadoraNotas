package unach.desarrollomovil.calculadoranotas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistorialActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Estudiante> lista;

    Button volver;
    Button salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        recyclerView = findViewById(R.id.recyclerHistorial);
        volver = findViewById(R.id.btVolver);
        salir = findViewById(R.id.btSalir);

        lista = DataHolder.listaEstudiantes;

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new RecyclerView.Adapter() {

            class VH extends RecyclerView.ViewHolder {
                TextView txt;

                public VH(View itemView) {
                    super(itemView);
                    txt = itemView.findViewById(R.id.txt);
                }
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_estudiante, parent, false);

                return new VH(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                VH vh = (VH) holder;

                Estudiante e = lista.get(position);

                vh.txt.setText(
                        "Nombre: " + e.nombre +
                                "\nAsignatura: " + e.asignatura +
                                "\nEstado: " + e.estado +
                                "\nPromedio: " + e.promedio
                );
            }

            @Override
            public int getItemCount() {
                return lista.size();
            }
        });

        volver.setOnClickListener(v -> {
            Intent intent = new Intent(HistorialActivity.this, DatosActivity.class);
            startActivity(intent);
            finish();
        });

        salir.setOnClickListener(v -> finishAffinity());
    }
}