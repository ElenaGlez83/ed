package Chess;

/**
 * Clase que implementa los métodos necesarios para obtener y conocer el array
 * de posibles movimientos de cada pieza
 *
 * @author Javier Morcillo
 */
public class ChessMovementHelper {

    private final PiecePosition[] positions = new PiecePosition[8 * 8];
    private int count = 0;

    /**
     * Crea un array del tamaño exacto del número de posiciones disponibles en
     * el que se pasan los datos de las posiciones disponibles
     *
     * @return el array de las posiciones disponibles
     */
    @SuppressWarnings("ManualArrayToCollectionCopy")
    public PiecePosition[] getResult() {
        PiecePosition[] result = new PiecePosition[count];
        for (int i = 0; i < count; i++) {
            result[i] = positions[i];
        }

        return result;
    }

    /**
     * Esta función intenta añadir en el array de soluciones una posición
     * siempre que no haya ya una ficha en ella
     *
     * @param aPosition posición inicial de la pieza
     * @param aBoard tablero de ajedrez con las piezas
     * @param columnCount número de columnas que aumentará la pieza
     * @param rowCount número de filas que aumentará la pieza
     * @return true si se la nueva posición es correcta y está vacía y false en
     * caso contrario
     */
    public boolean addPositionWithDisplacementIfAvaiable(PiecePosition aPosition,
            ChessBoard aBoard, int columnCount, int rowCount) {
        // Se comprueba si la nueva posición está dentro de los límites y si no 
        // tenemos ya el array de posiciones completo antes de añadir la nueva
        // posición al array. Además, se comprueba si la nueva posición está
        // vacía
        if (PiecePosition.isAvailable(aPosition, columnCount, rowCount) && count < positions.length) {
            PiecePosition newPosition = aPosition.getDisplacedPiece(columnCount, rowCount);
            ChessPiece piece = aBoard.getPieceAt(newPosition);
            if (piece == null) {
                positions[count++] = newPosition;
                return true;
            }
        }
        return false;
    }

    /**
     * Método que añade la nueva posición de la pieza al array de posiciones
     * siempre que sea una posición válida y haya espacio en el array
     *
     * @param aPosition posición inicial de la pieza
     * @param columnCount número de columnas que aumentará la pieza
     * @param rowCount número de filas que aumentará la pieza
     * @return true si se puede añadir la nueva posición al array y false en
     * caso contrario
     */
    public boolean addPosition(PiecePosition aPosition, int columnCount, int rowCount) {
        // Se comprueba si la nueva posición está dentro de los límites y si no 
        // tenemos ya el array de posiciones completo antes de añadir la nueva
        // posición al array
        if (PiecePosition.isAvailable(aPosition, columnCount, rowCount) && count < positions.length) {
            positions[count++] = aPosition.getDisplacedPiece(columnCount, rowCount);
            return true;
        }
        return false;
    }
}
