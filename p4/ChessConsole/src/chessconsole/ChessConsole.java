package chessconsole;

import Chess.*;

/**
 * Clase que inicializa el modo consola del ajedrez, muestra el tablero y
 * permite que las inteligencias artificiales jueguen en ambos colores.
 *
 * @author Javier Morcillo
 */
public class ChessConsole {

    /**
     * Método que crea un jugador de inteligencia artificial
     *
     * @return la inteligencia artificial creada
     */
    static ChessAI createArtificialIntelligenceLogic() {
        return new ChessRandomAI();
    }

    /**
     * Método que crea un jugador de inteligencia artificial para sustituir al
     * jugador
     *
     * @return la inteligencia artificial creada
     */
    static ChessAI createPlayerLogic() {
        return new ChessRandomAI();
    }

    /**
     * Método que crea un tablero con sus piezas en las posiciones iniciales
     *
     * @return tablero creado
     */
    static ChessBoard createTable() {
        return new ChessBoardImplementation();
    }

    /**
     * Método que devolverá un asterisco o un espacio en blanco de forma
     * alternativa a la hora de imprimir las posiciones vacías del tablero
     *
     * @param column columna del tablero a pintar
     * @param row fila del tablero a pintar
     * @return carácter que le corresponde a esa columna y esa fila
     */
    static char getCharForBoard(int column, int row) {
        return ((column + row) % 2 == 0) ? '*' : ' ';
    }

    /**
     * Método que recorre el tablero y lo muestra por consola, mostrando el
     * número de fila y columna y la pieza que ocupa cada posición
     *
     * @param Table tablero con las piezas en cada momento
     */
    static void printTable(ChessBoard Table) {
        PiecePosition position = new PiecePosition(0, 7);
        System.out.print("   ");
        for (int column = 0; column < 8; column++) {
            System.out.print(" " + (column + 1) + " ");
        }
        System.out.println();

        for (int row = 7; row >= 0; row--) {
            System.out.print("[" + (row + 1) + "]");
            for (int column = 0; column < 8; column++) {
                position.setValues(column, row);
                ChessPiece piece = Table.getPieceAt(position);
                if (piece == null) {
                    System.out.print(" " + getCharForBoard(column, row) + " ");
                } else {
                    switch (piece.getType()) {
                        case PAWN:
                            System.out.print("P");
                            break;
                        case BISHOP:
                            System.out.print("A");
                            break;
                        case KING:
                            System.out.print("R");
                            break;
                        case QUEEN:
                            System.out.print("Q");
                            break;
                        case KNIGHT:
                            System.out.print("C");
                            break;
                        case ROOK:
                            System.out.print("T");
                            break;
                    }
                    System.out.print(piece.getColor() == ChessPiece.Color.BLACK ? "N " : "B ");
                }
            }
            System.out.println("[" + (row + 1) + "]");
        }
        System.out.print("   ");
        for (int column = 0; column < 8; column++) {
            System.out.print(" " + (column + 1) + " ");
        }
        System.out.println();
        System.out.print("-----------------------------------------------------\n");
    }

    /**
     * Método main que crea a los dos jugadores como parte de la inteligencia
     * artificial, crea el tablero y hace que ambos jugadores muevan ficha de
     * forma alternativa hasta finalizar la partida
     *
     * @param args
     */
    public static void main(String[] args) {
        // Creamos los dos jugadores con inteligencia artificial y el tablero 
        // para jugar.
        ChessBoard table = createTable();
        ChessAI ai = createArtificialIntelligenceLogic();
        ChessAI player = createPlayerLogic();

        System.out.println("Bienvenido al juego del ajedrez, tu llevas las blancas");

        // Se comprueba si se ha terminado el juego y mientras no haya terminado
        // se seguirá jugando
        while (table.containsKing(ChessPiece.Color.BLACK) && table.containsKing(ChessPiece.Color.WHITE)) {
            // Imprime el tablero con las piezas que tenga en ese momento
            printTable(table);

            // Hacemos que cada jugador mueva siempre que la partida no haya acabado,
            // si no se puede realizar el movimiento es porque algo ha ido mal.
            if (!player.performNextMovement(table, ChessPiece.Color.WHITE)) {
                System.out.println("Algo ha ido mal, ...");
            }

            if (table.containsKing(ChessPiece.Color.BLACK) && table.containsKing(ChessPiece.Color.WHITE)) {
                if (!ai.performNextMovement(table, ChessPiece.Color.BLACK)) {
                    System.out.println("Algo ha ido mal, ...");
                }
            }
        }
        // Una vez finalizada la partida, mostramos el tablero final y mostramos
        // quién ha ganado la partida.
        printTable(table);
        if (table.containsKing(ChessPiece.Color.BLACK)) {
            System.out.println("GANARON LAS NEGRAS!!!!");
        } else {
            System.out.println("GANARON LAS BLANCAS!!!!");
        }
    }
}
