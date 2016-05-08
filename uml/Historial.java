package veterinario;

import java.util.Date;

/**
 * Clase Historial donde almacenaremos las enfermedades diagnosticadas en cada
 * visita al veterinario y la fecha de dicha visita.
 * 
 * @author Elena González Rojo
 */
public class Historial {
    
    // Definimos los atributos de la clase
    private Enfermedad[] enfermedades;
    private Date[] fechaEnfermedad;

    // En el constructor deberemos especificar cuántas enfermedades se han
    // diagnosticado para inicializar el array
    public Historial(int numeroEnfermedades) {
        enfermedades = new Enfermedad[numeroEnfermedades];
    }
    
    // Definimos los getter y setter de los atributos
    public Enfermedad[] getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(Enfermedad[] enfermedades) {
        this.enfermedades = enfermedades;
    }

    public Date[] getFechaEnfermedad() {
        return fechaEnfermedad;
    }

    public void setFechaEnfermedad(Date[] fechaEnfermedad) {
        this.fechaEnfermedad = fechaEnfermedad;
    }   
}
