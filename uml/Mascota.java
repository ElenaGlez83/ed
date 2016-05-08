package veterinario;

import java.util.Date;

/**
 * Clase Mascota donde se almacenarán todos los datos de cada mascota así como
 * su peso, enfermedades diagnosticadas y vacunas puestas. Proporciona métodos
 * de consulta y edición sobre esos atributos.
 * 
 * @author Elena González Rojo
 */
public class Mascota {

    // Definimos los atributos de la clase
    private int codigo;
    private String alias;
    private String especie;
    private String raza;
    private String colorPelo;
    private Date fechaNacimiento;
    private Historial[] historiales;
    private CalendarioVacunacion[] calendarioVacunaciones;
    private double[] historicoPeso = new double[10];

    // Definimos algunos de los métodos que podrían estar definidos en esta clase
    /**
     * En cada visita al veterinario se pesará a la mascota y se añadirá el peso
     * actual al array del histórico de pesos
     *
     * @param pesoActual peso actual de la mascota
     * @return true si se ha podido actualizar el histórico, false en caso
     * contrario
     */
    public boolean anyadirNuevoPeso(double pesoActual) {
        return true;
    }

    /**
     * Método que añadirá un nuevo elemento al array de historiales de
     * enfermedades, creando un array de mayor dimensión si es necesario.
     *
     * @param historial nueva enfermedad diagnosticada a la mascota con la fecha
     * de diagnóstico
     * @return true si se ha podido añadir el nuevo historial de enfermedad,
     * false en caso contrario
     */
    public boolean anyadirEnfermedad(Historial historial) {
        return true;
    }

    /**
     * Método que añadirá un nuevo elemento al array de calendarioVacunaciones,
     * creando un array de mayor dimensión si es necesario.
     *
     * @param calendarioVacunacion nueva vacuna que se ha puesto a la mascota
     * con la fecha de vacunación
     * @return true si se ha podido añadir la nueva vacuna, false en caso
     * contrario
     */
    public boolean anyadirVacuna(CalendarioVacunacion calendarioVacunacion) {
        return true;
    }

    /**
     * Método que devolverá el último valor válido del array historicoPeso, que
     * será el peso de la última visita
     *
     * @return peso de la última visita
     */
    public double conocerPesoActual() {
        return 0.0;
    }

    /**
     * Método que recorrerá el array del historicoPeso y calculará el peso medio
     * de los datos almacenados
     *
     * @return peso medio
     */
    public double conocerPesoMedio() {
        return 0.0;
    }

    // Definimos los getter y setter de los atributos
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColorPelo() {
        return colorPelo;
    }

    public void setColorPelo(String colorPelo) {
        this.colorPelo = colorPelo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Historial[] getHistoriales() {
        return historiales;
    }

    public void setHistoriales(Historial[] historiales) {
        this.historiales = historiales;
    }

    public CalendarioVacunacion[] getCalendarioVacunaciones() {
        return calendarioVacunaciones;
    }

    public void setCalendarioVacunaciones(CalendarioVacunacion[] calendarioVacunaciones) {
        this.calendarioVacunaciones = calendarioVacunaciones;
    }

    public double[] getHistoricoPeso() {
        return historicoPeso;
    }

    public void setHistoricoPeso(double[] historicoPeso) {
        this.historicoPeso = historicoPeso;
    }
}
