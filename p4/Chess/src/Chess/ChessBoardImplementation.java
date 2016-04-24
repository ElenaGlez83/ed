package Chess;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa los métodos de la interfaz ChessBoard
 *
 * @author Javier Morcillo y Elena González
 */
public class ChessBoardImplementation implements ChessBoard {

    ChessPiece pieces[] = new ChessPiece[8 * 8];

    /**
     * Constructor de la clase, en el que crearemos las posiciones iniciales de
     * las piezas en un juego de ajedrez
     *
     */
    public ChessBoardImplementation() {
        for (int i = 0; i < 8; i++) {
            pieces[getPieceIndex(i, 1)] = new ChessPieceImplementation(ChessPiece.Color.WHITE, ChessPiece.Type.PAWN);
            pieces[getPieceIndex(i, 6)] = new ChessPieceImplementation(ChessPiece.Color.BLACK, ChessPiece.Type.PAWN);
        }
        pieces[getPieceIndex(0, 0)] = new ChessPieceImplementation(ChessPiece.Color.WHITE, ChessPiece.Type.ROOK);
        pieces[getPieceIndex(7, 0)] = new ChessPieceImplementation(ChessPiece.Color.WHITE, ChessPiece.Type.ROOK);
        pieces[getPieceIndex(0, 7)] = new ChessPieceImplementation(ChessPiece.Color.BLACK, ChessPiece.Type.ROOK);
        pieces[getPieceIndex(7, 7)] = new ChessPieceImplementation(ChessPiece.Color.BLACK, ChessPiece.Type.ROOK);

        pieces[getPieceIndex(1, 0)] = new ChessPieceImplementation(ChessPiece.Color.WHITE, ChessPiece.Type.KNIGHT);
        pieces[getPieceIndex(6, 0)] = new ChessPieceImplementation(ChessPiece.Color.WHITE, ChessPiece.Type.KNIGHT);
        pieces[getPieceIndex(1, 7)] = new ChessPieceImplementation(ChessPiece.Color.BLACK, ChessPiece.Type.KNIGHT);
        pieces[getPieceIndex(6, 7)] = new ChessPieceImplementation(ChessPiece.Color.BLACK, ChessPiece.Type.KNIGHT);

        pieces[getPieceIndex(2, 0)] = new ChessPieceImplementation(ChessPiece.Color.WHITE, ChessPiece.Type.BISHOP);
        pieces[getPieceIndex(5, 0)] = new ChessPieceImplementation(ChessPiece.Color.WHITE, ChessPiece.Type.BISHOP);
        pieces[getPieceIndex(2, 7)] = new ChessPieceImplementation(ChessPiece.Color.BLACK, ChessPiece.Type.BISHOP);
        pieces[getPieceIndex(5, 7)] = new ChessPieceImplementation(ChessPiece.Color.BLACK, ChessPiece.Type.BISHOP);

        pieces[getPieceIndex(4, 0)] = new ChessPieceImplementation(ChessPiece.Color.WHITE, ChessPiece.Type.KING);
        pieces[getPieceIndex(3, 0)] = new ChessPieceImplementation(ChessPiece.Color.WHITE, ChessPiece.Type.QUEEN);
        pieces[getPieceIndex(3, 7)] = new ChessPieceImplementation(ChessPiece.Color.BLACK, ChessPiece.Type.QUEEN);
        pieces[getPieceIndex(4, 7)] = new ChessPieceImplementation(ChessPiece.Color.BLACK, ChessPiece.Type.KING);
    }

    @Override
    public ChessPiece[] getPieces(ChessPiece.Color PieceColor) {
        // Se recorren todas las piezas presentes en el tablero y se cuenta el
        // número de piezas que existen del color elegido.
        int count = 0;
        for (ChessPiece piece : pieces) {
            if (piece != null && piece.getColor() == PieceColor) {
                count++;
            }
        }

        // Si no existe ninguna pieza de ese color devolveremos null
        if (count == 0) {
            return null;
        }

        // En caso contrario, crearemos un array auxiliar que contendrá esas 
        // piezas y devolvemos este array auxiliar
        ChessPiece[] result = new ChessPiece[count];
        count = 0;
        for (ChessPiece piece : pieces) {
            if (piece != null && piece.getColor() == PieceColor) {
                result[count++] = piece;
            }
        }

        return result;
    }

    /**
     * Método que devuelve la posición del array a la que corresponden una fila
     * y una columna del tablero
     *
     * @param column Columna del tablero de la que se quiere conocer el index en
     * el array
     * @param row Fila del tablero de la que se quiere conocer el index en el
     * array
     * @return int con el índice del array que corresponde a esa columna y fila
     */
    private int getPieceIndex(int column, int row) {
        return row * 8 + column;
    }

    /**
     * Método que devuelve la posición del array a la que corresponden la
     * posición de una pieza
     *
     * @param position Posición de la pieza de la que se quiere conocer el index
     * en el array de piezas
     * @return int con el índice del array que corresponde a esa posición
     */
    private int getPieceIndex(PiecePosition position) {
        return position.getRow() * 8 + position.getColumn();
    }

    /**
     * Devuelve la pieza que se encuentra en el tablero en la posición
     * determinadala por la columna y la fila indicadas
     *
     * @param column Columna del tablero de la que queremos obtener la pieza
     * @param row Fila del tablero de la que queremos obtener la pieza
     * @return Pieza que se encuentra en esa posición
     */
    private ChessPiece getPiece(int column, int row) {
        // Se añade una comprobación para asegurarnos que si el índice excede
        // la dimensión del array no de una excepción
        if (PiecePosition.isAvailable(column, row)) {
            return pieces[getPieceIndex(column, row)];
        } else {
            return null;
        }
    }

    @Override
    public ChessPiece getPieceAt(PiecePosition position) {
        if (!PiecePosition.isAvailable(position)) {
            return null;
        }
        return getPiece(position.getColumn(), position.getRow());
    }

    @Override
    public PiecePosition getPiecePosition(ChessPiece APiece) {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (getPiece(column, row) == APiece) {
                    return new PiecePosition(column, row);
                }
            }
        }
        return null;
    }

    @Override
    public void removePieceAt(PiecePosition Position) {
        // Añadimos una comprobación para saber que se va a borrar un elemento 
        // dentro de los límites de nuestro array.
        if (PiecePosition.isAvailable(Position))
            pieces[getPieceIndex(Position)] = null;
    }

    @Override
    public boolean movePieceTo(ChessPiece Piece, PiecePosition Position) {
        PiecePosition oldPosition = getPiecePosition(Piece);
        if (oldPosition != null) {
            // Añadimos una comprobación para saber que la pieza se mueve dentro
            // de los límites del tablero, sino devolveremos false
            if (PiecePosition.isAvailable(oldPosition) && PiecePosition.isAvailable(Position)) {
                pieces[getPieceIndex(oldPosition)] = null;
                if (Piece != null) {
                    if (Piece.getType() == ChessPiece.Type.PAWN && 
                            ((Piece.getColor() == ChessPiece.Color.WHITE && Position.getRow() == 7)
                            || (Piece.getColor() == ChessPiece.Color.BLACK && Position.getRow() == 0))) {
                        ChessPiece auxPiece = new ChessPieceImplementation(Piece.getColor(), ChessPiece.Type.QUEEN);
                        Piece = auxPiece;
                    }
                    pieces[getPieceIndex(Position)] = Piece;
                    Piece.notifyMoved();
                }                
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsKing(ChessPiece.Color PieceColor) {
        for (ChessPiece piece : pieces) {
            if (piece != null && piece.getType() == ChessPiece.Type.KING && piece.getColor() == PieceColor) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean saveToFile(File location) {
        Charset charset = Charset.forName("US-ASCII");
        // Creamos la cadena en la que almacenaremos las posiciones actuales de
        // las piezas para posteriormente grabarlo en el fichero.
        String posicionesPiezas = "";
        // Recorremos el tablero y concatenamos el tipo de ficha, el color y la
        // posición dentro del tablero. También guardaremos la posición de las 
        // casillas vacías.
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                PiecePosition posicion = new PiecePosition(i, j);
                ChessPiece pieza = getPieceAt(posicion);
                if (pieza != null) {
                    posicionesPiezas += pieza.getType() + "#" + pieza.getColor() + "#" + i + "#" + j + "\n";
                } else {
                    posicionesPiezas += "NULL#NULL#" + i + "#" + j + "\n";
                }
            }
        }
        // Para evitar problemas, quitamos el último retorno de carro de la 
        // cadena con un substring
        posicionesPiezas = posicionesPiezas.substring(0, posicionesPiezas.length() - 1);
        // Y procedemos a guardar la información en el fichero deseado
        try (BufferedWriter writer = Files.newBufferedWriter(location.toPath(), charset)) {
            writer.write(posicionesPiezas, 0, posicionesPiezas.length());
            return true;
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
            return false;
        }
    }

    @Override
    public boolean loadFromFile(File location) {
        // Intentamos leer el fichero, si no existe dará un error
        try (Scanner in = new Scanner(location)) {
            // Se procederá a leer todas las líneas del fichero, se hará split
            // de cada una de ellas, por el símbolo separador
            while (in.hasNextLine()) {
                String piezaFichero = in.next();
                String[] datosPieza = piezaFichero.split("#");
                // Si no existe el número de datos que se necesitan no se podrá
                // cargar el fichero, además se debe asegurar que el tercer y
                // cuarto dato son enteros
                if (datosPieza.length == 4 && esEntero(datosPieza[2]) && esEntero(datosPieza[3])) {
                    // Cogemos todos los datos de cada pieza
                    String tipoPiezaFichero = datosPieza[0];
                    String colorPiezaFichero = datosPieza[1];
                    int column = Integer.parseInt(datosPieza[2]);
                    int row = Integer.parseInt(datosPieza[3]);
                    ChessPiece.Color colorPieza = null;
                    ChessPiece.Type tipoPieza = null;
                    // Damos valor al color y al tipo de la pieza si tienen 
                    // alguno de los valores esperados. El color se comprueba
                    // con un if y el tipo de pieza con un switch.
                    if ("BLACK".equals(colorPiezaFichero)) {
                        colorPieza = ChessPiece.Color.BLACK;
                    } else if ("WHITE".equals(colorPiezaFichero)) {
                        colorPieza = ChessPiece.Color.WHITE;
                    }

                    switch (tipoPiezaFichero) {
                        case "KING":
                            tipoPieza = ChessPiece.Type.KING;
                            break;
                        case "QUEEN":
                            tipoPieza = ChessPiece.Type.QUEEN;
                            break;
                        case "ROOK":
                            tipoPieza = ChessPiece.Type.ROOK;
                            break;
                        case "BISHOP":
                            tipoPieza = ChessPiece.Type.BISHOP;
                            break;
                        case "KNIGHT":
                            tipoPieza = ChessPiece.Type.KNIGHT;
                            break;
                        case "PAWN":
                            tipoPieza = ChessPiece.Type.PAWN;
                            break;
                    }
                    // Si el índice está en los límites adecuados se mirará si 
                    // todos los datos son válidos y añadiremos esa pieza o 
                    // devolveremos null. Si la pieza no está en los límites del
                    // array, acabaremos la carga devolviendo un false.
                    if (PiecePosition.isAvailable(column, row)) {
                        if (colorPieza != null && tipoPieza != null) {
                            pieces[getPieceIndex(column, row)] = new ChessPieceImplementation(colorPieza, tipoPieza);
                        } else {
                            pieces[getPieceIndex(column, row)] = null;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } catch (FileNotFoundException ex) {
            // Si hay un error con el fichero, no se cargará el tablero
            Logger.getLogger(ChessBoardImplementation.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * Método que comprueba si una cadena es un entero.
     *
     * @param cadenaComprobar cadena a comprobar
     * @return true si es entero, false en caso contrario
     */
    private boolean esEntero(String cadenaComprobar) {
        try {
            Integer.parseInt(cadenaComprobar);
            return true;
        } catch (NumberFormatException ex) {
            Logger.getLogger(ChessBoardImplementation.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
