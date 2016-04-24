package Chess;

import java.util.Random;

/**
 * Clase que implementa la interfaz ChessAI e implementa el método que permite
 * que pueda jugarse contra un ordenador
 *
 * @author Javier Morcillo
 */
public class ChessRandomAI implements ChessAI {

    private final Random random = new Random();

    @Override
    public boolean performNextMovement(ChessBoard aTable, ChessPiece.Color playerColor) {
        // Se recogen todas las piezas del color seleccionado.
        ChessPiece[] pieces = aTable.getPieces(playerColor);
        // Se cambia el orden de las piezas en el array, pues sólo se moverá una
        // de ellas y así se mueve una pieza aleatoria de las existentes.
        if (pieces != null) {
            ChessUtils.randomizeArray(pieces);

            for (ChessPiece piece : pieces) {
                // Para la primera pieza de ese array, se recogen las posiciones 
                // disponibles para el movimiento, se coge una posición al azar y se 
                // intenta mover la pieza a esa posición.
                PiecePosition[] positions = piece.getAvailablePositions(aTable);
                if (positions != null && positions.length > 0) {
                    PiecePosition position = positions[random.nextInt(positions.length)];
                    if (aTable.movePieceTo(piece, position)) {
                        return true;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
