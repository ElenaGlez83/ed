package veterinario;

import java.util.Date;

/**
 * Clase CalendarioVacunacion donde almacenaremos las enfermedades de las que se
 * vacuna a la mascota en cada vacuna y la fecha de dicha vacunación.
 *
 * @author Elena González Rojo
 */
public class CalendarioVacunacion {

    // Definimos los atributos de la clase
    private Enfermedad[] enfermedades;
    private Date fechaVacuna;

    // En el constructor deberemos especificar cuántas enfermedades se han
    // vacunado para inicializar el array
    public CalendarioVacunacion(int numeroEnfermedades) {
        enfermedades = new Enfermedad[numeroEnfermedades];
    }
    
    // Definimos los getter y setter de los atributos
    public Enfermedad[] getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(Enfermedad[] enfermedades) {
        this.enfermedades = enfermedades;
    }

    public Date getFechaVacuna() {
        return fechaVacuna;
    }

    public void setFechaVacuna(Date fechaVacuna) {
        this.fechaVacuna = fechaVacuna;
    }
}
