package veterinario;

/**
 * Clase Enfermedad que almacena el nombre de la enfermedad diagnosticada y 
 * tratada o vacunada.
 * 
 * @author Elena González Rojo
 */
public class Enfermedad {
 
    // Definimos el atributo de la clase
    private String nombre;    
    
    // Definimos el getter y setter del atributo nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
