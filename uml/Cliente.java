package veterinario;

/**
 * Clase Cliente donde se almacenarán todos los datos de cada cliente y la
 * familia. Proporciona métodos de consulta y edición sobre esos atributos.
 *
 * @author Elena González Rojo
 */
public class Cliente {

    // Definimos los atributos de la clase
    private int codigo;
    private String primerApellido;
    private long cuentaBancaria;
    private String direccion;
    private int telefono;
    private Persona[] miembrosFamilia;
    private Mascota[] mascotas;

    // Definimos algunos de los métodos que podrían estar definidos en esta clase
    /**
     * Método que añadirá una nueva persona al cliente (un nuevo miembro en la
     * familia), creando un array de mayor dimensión si es necesario.
     *
     * @param persona nuevo miembro de la familia del cliente
     * @return true si se ha podido añadir la nueva persona, false en caso
     * contrario
     */
    public boolean anyadirPersona(Persona persona) {
        return true;
    }

    /**
     * Método que añadirá una nueva mascota al cliente, creando un array de
     * mayor dimensión si es necesario.
     *
     * @param mascota nueva mascota del cliente
     * @return true si se ha podido añadir la nueva mascota, false en caso
     * contrario
     */
    public boolean anyadirMascota(Mascota mascota) {
        return true;
    }

    // Definimos los getter y setter de los atributos
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public long getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(long cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Persona[] getMiembrosFamilia() {
        return miembrosFamilia;
    }

    public void setMiembrosFamilia(Persona[] miembrosFamilia) {
        this.miembrosFamilia = miembrosFamilia;
    }

    public Mascota[] getMascotas() {
        return mascotas;
    }

    public void setMascotas(Mascota[] mascotas) {
        this.mascotas = mascotas;
    }
}
