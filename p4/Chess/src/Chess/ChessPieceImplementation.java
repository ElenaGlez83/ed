package Chess;

/**
 * Clase que implementa la interfaz ChessPiece 
 *
 * @author Elena González
 */
public class ChessPieceImplementation implements ChessPiece {

    Color color;
    Type tipo;
    boolean piezaMovida;
    
    public ChessPieceImplementation(Color color, Type tipo) {
        this.color = color;
        this.tipo = tipo;
        this.piezaMovida = false;
    }
    
    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public Type getType() {
        return this.tipo;
    }

    @Override
    public void notifyMoved() {
        this.piezaMovida = true;
    }

    @Override
    public boolean wasMoved() {
        return this.piezaMovida;
    }

    @Override
    public PiecePosition[] getAvailablePositions(ChessBoard aBoard) {
        PiecePosition[] posicionesDisponibles = null;
        switch (this.tipo){
            case KING:
                posicionesDisponibles = ChessMovementManager.getAvailablePositionsOfKing(this, aBoard);
                break;
            case QUEEN:
                posicionesDisponibles = ChessMovementManager.getAvailablePositionsOfQueen(this, aBoard);
                break;
            case ROOK:
                posicionesDisponibles = ChessMovementManager.getAvailablePositionsOfRook(this, aBoard);
                break;
            case BISHOP:
                posicionesDisponibles = ChessMovementManager.getAvailablePositionsOfBishop(this, aBoard);
                break;
            case KNIGHT:
                posicionesDisponibles = ChessMovementManager.getAvailablePositionsOfKnight(this, aBoard);
                break;
            case PAWN:
                posicionesDisponibles = ChessMovementManager.getAvailablePositionsOfPawn(this, aBoard);
                break;
        }
        return posicionesDisponibles;
    }
}