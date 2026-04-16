package unach.desarrollomovil.calculadoranotas;

public class Estudiante {

    String nombre;
    String asignatura;
    String estado;
    double promedio;

    public Estudiante(String nombre, String asignatura, String estado, double promedio) {
        this.nombre = nombre;
        this.asignatura = asignatura;
        this.estado = estado;
        this.promedio = promedio;
    }
}