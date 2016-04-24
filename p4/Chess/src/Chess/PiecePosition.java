package Chess;

/**
 * Clase que define las PiecePosition y proporciona métodos para comprobar la
 * validez de las mismas, que se encuentran en el tablero y algunas
 * funcionalidades extras para este objeto.
 *
 * @author Javier Morcillo
 */
public class PiecePosition {

    /**
     * Método que recibe una columna y una fila y comprueba si está dentro de
     * los rangos de nuestro tablero y, por tanto, es una posición correcta.
     *
     * @param column columna a comprobar
     * @param row fila a comprobar
     * @return true si es una posición válida, false en caso contrario
     */
    public static boolean isAvailable(int column, int row) {
        return column >= 0 && column < 8 && row >= 0 && row < 8;
    }

    /**
     * Método que recibe una PiecePosition, y el número de columnas y filas que
     * se desean incrementar y comprueba si es una posición válida.
     *
     * @param position Posición a comprobar
     * @param columnIncrement Número de columnas que se va a mover la pieza
     * @param rowIncrement Número de FILAS que se va a mover la pieza
     * @return true si la nueva posición es correcta, false en caso contrario
     */
    static boolean isAvailable(PiecePosition position, int columnIncrement, int rowIncrement) {
        // Si la posición que se recibe como parámetro es null, sabemos que no 
        // es una posición disponible, ni válida, y devolvemos false.
        if (position == null) {
            return false;
        }
        // En caso contrario, calculamos la nueva columna y fila y comprobamos
        // si es una posición correcta llamando al método apropiado.
        int newColumn = position.getColumn() + columnIncrement;
        int newRow = position.getRow() + rowIncrement;
        return isAvailable(newColumn, newRow);
    }

    /**
     * Método que recibe una PiecePosition y determina si es una posición válida
     * o no.
     *
     * @param position Posición a comprobar
     * @return true si es correcta, false en caso contrario
     */
    static boolean isAvailable(PiecePosition position) {
        // Si la posición es null, sabemos que no es una posición correcta
        if (position == null) {
            return false;
        }
        // En caso contrario, llamamos al método que comprueba los límites del
        // tablero con la columna y la fila de la posición que se recibe como
        // parámetro.
        return isAvailable(position.getColumn(), position.getRow());
    }

    // Variables que definen una PiecePosition, columna y fila
    private int column, row;

    /**
     * Constructor PiecePosition que recibe como parámetros la fila y la columna
     * de la posición
     *
     * @param column Columna de la posición de la pieza
     * @param row Fila de la posición de la pieza
     */
    public PiecePosition(int column, int row) {
        this.column = column;
        this.row = row;
    }

    /**
     * Método que devuelve la columna de esta posición
     *
     * @return número de columna
     */
    public int getColumn() {
        return column;
    }

    /**
     * Método que devuelve la fila de esta posición
     *
     * @return número de fila
     */
    public int getRow() {
        return row;
    }

    /**
     * Método que recibe una columa y una fila y asigna estos valores, en caso
     * de ser válidos, a la posición de la ficha.
     *
     * @param column Columna que se quiere como valor de la posición
     * @param row Fila que se quiere como valor de la posición
     * @return true si la fila y la columna son valores dentro del tablero y se
     * realiza la asignación de valores, false en caso contrario.
     */
    public boolean setValues(int column, int row) {
        if (isAvailable(column, row)) {
            this.column = column;
            this.row = row;
            return true;
        }
        return false;
    }

    /**
     * Método que devuelve la nueva posción de la pieza tras aplicar un
     * movimiento que desplaza la pieza en columnCount columnas y rowCount filas
     *
     * @param columnCount número de columnas que se desplazaría la pieza
     * @param rowCount número de filas que se desplazaría la pieza
     * @return null si la posición no es válida y una nueva PiecePosition en
     * caso contrario
     */
    public PiecePosition getDisplacedPiece(int columnCount, int rowCount) {
        // Se compruba si es una posición válida, en caso de no serlo se devuelve
        // null directamente
        if (!isAvailable(this, columnCount, rowCount)) {
            return null;
        }
        // Si la posición es correcta, se calcula la nueva fila y columna y se
        // devuelve una nueva PiecePosition con esos valores calculados
        int newColumn = getColumn() + columnCount;
        int newRow = getRow() + rowCount;
        return new PiecePosition(newColumn, newRow);
    }

    /**
     * Método que devuelve una nueva PiecePosition con los mismos valores de
     * fila y columna que ya tenemos
     *
     * @return PiecePosition clonada
     */
    public PiecePosition clone() {
        return new PiecePosition(column, row);
    }

    /**
     * Método que comprueba si la PiecePosition que se pasa como argumento tiene
     * la misma fila y columna que la actual.
     *
     * @param aPosition PiecePosition a comparar
     * @return true si sus filas y columnas son iguales, false en caso contrario
     */
    public boolean equals(PiecePosition aPosition) {
        return aPosition.getColumn() == getColumn() && aPosition.getRow() == getRow();
    }
}
