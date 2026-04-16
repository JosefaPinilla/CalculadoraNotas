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
    Button volver, salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        recyclerView = findViewById(R.id.recyclerHistorial);
        volver = findViewById(R.id.btVolver);
        salir = findViewById(R.id.btSalir);

        lista = DataHolder.listaEstudiantes;

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(lista));

        volver.setOnClickListener(v -> {
            Intent intent = new Intent(HistorialActivity.this, DatosActivity.class);
            startActivity(intent);
            finish();
        });

        salir.setOnClickListener(v -> finishAffinity());
    }

    public static class Adapter extends RecyclerView.Adapter<Adapter.VH> {

        ArrayList<Estudiante> lista;

        public Adapter(ArrayList<Estudiante> lista) {
            this.lista = lista;
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_estudiante, parent, false);
            return new VH(v);
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {

            Estudiante e = lista.get(position);

            holder.nombre.setText(e.nombre);
            holder.asignatura.setText(e.asignatura);
            holder.estado.setText(e.estado);
            holder.nota.setText(String.valueOf(e.promedio));
        }

        @Override
        public int getItemCount() {
            return lista.size();
        }

        public static class VH extends RecyclerView.ViewHolder {

            TextView nombre, asignatura, estado, nota;

            public VH(View itemView) {
                super(itemView);

                nombre = itemView.findViewById(R.id.txtNombre);
                asignatura = itemView.findViewById(R.id.txtAsignatura);
                estado = itemView.findViewById(R.id.txtEstado);
                nota = itemView.findViewById(R.id.txtNota);
            }
        }
    }
}