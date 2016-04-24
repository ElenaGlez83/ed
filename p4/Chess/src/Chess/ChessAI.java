package Chess;

/**
 * Interfaz que define el método que permite que pueda jugarse contra un
 * ordenador
 *
 * @author Javier Morcillo
 */
public interface ChessAI {

    /**
     * Método que mueve una pieza aleatoria del color que se indique a una de
     * las posiciones disponibles para el movimiento de esa pieza
     *
     * @param aTable Tablero con las piezas
     * @param playerColor Color de la pieza que está jugando y se desea mover
     * @return Devolverá true si se ha podido realizar el movimiento y false en
     * caso contrario
     */
    boolean performNextMovement(ChessBoard aTable, ChessPiece.Color playerColor);
}
