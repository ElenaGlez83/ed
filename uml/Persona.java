package veterinario;

/**
 * Clase Persona donde almacenaremos los datos de todas las personas que están
 * relacionadas con las mascotas.
 *
 * @author Elena González Rojo
 */
public class Persona {
    
    // Definimos los atributos de la clase
    private String DNI;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;

    // Definimos los getter y setter de los atributos
    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }
}
