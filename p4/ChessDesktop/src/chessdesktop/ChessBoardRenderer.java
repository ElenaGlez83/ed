package chessdesktop;

import Chess.ChessBoard;
import Chess.ChessPiece;
import Chess.PiecePosition;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ChessBoardRenderer {

    final double LEFT_MARGIN = 50;
    final double TOP_MARGIN = 50;
    final double PIECE_OFFSET = 5;

    private final ChessBoard board = new Chess.ChessBoardImplementation();
    private ChessPiece movingPiece;

    static private void drawCrown(GraphicsContext aContext, double minX, double minY, double width, double height) {
        double maxX = minX + width;
        double maxY = minY + height;
        double[] x = {minX, minX, maxX, maxX, minX + width * 0.8, (minX + maxX) * 0.5, minX + width * 0.2};
        double[] y = {minY, maxY, maxY, minY, minY + height * 0.5, minY, minY + height * 0.5};
        aContext.fillPolygon(x, y, 7);
    }

    static private void drawTriangle(GraphicsContext aContext, double minX, double minY, double width, double height) {
        double maxX = minX + width;
        double maxY = minY + height;
        double[] x = {minX + width * 0.5, minX, maxX};
        double[] y = {minY, maxY, maxY};
        aContext.fillPolygon(x, y, 3);
    }

    void drawPiece(Canvas canvas, ChessPiece piece, double minX, double minY, double width, double height) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if (piece == movingPiece)
            gc.setFill(Color.GREY);
        else if (piece.getColor() == ChessPiece.Color.BLACK)
            gc.setFill(Color.BLACK);
        else 
            gc.setFill(Color.WHITE);

        minX += PIECE_OFFSET;
        minY += PIECE_OFFSET;
        width -= PIECE_OFFSET * 2;
        height -= PIECE_OFFSET * 2;

        switch (piece.getType()) {
            case KING:
                drawCrown(gc, minX, minY, width, height);
                break;
            case QUEEN:
                drawCrown(gc, minX + width * 0.3, minY, width * 0.4, height * 0.3);
                gc.fillOval(minX, minY + height * 0.3, width, height * 0.7);
                gc.fillRect(minX, minY + height * 0.7, width, height * 0.3);
                break;
            case ROOK: {
                double maxX = minX + width;
                double maxY = minY + height;
                double[] x = {minX, minX, maxX, maxX,
                    minX + width * 0.8, minX + width * 0.8,
                    minX + width * 0.6, minX + width * 0.6,
                    minX + width * 0.4, minX + width * 0.4,
                    minX + width * 0.2, minX + width * 0.2
                };
                double[] y = {minY, maxY, maxY,
                    minY, minY,
                    minY + height * 0.3, minY + height * 0.3,
                    minY, minY,
                    minY + height * 0.3, minY + height * 0.3,
                    minY
                };
                gc.fillPolygon(x, y, 12);
                break;
            }
            case BISHOP:
                gc.fillOval(minX + width * 0.4, minY, width * 0.2, height);
                drawTriangle(gc, minX + width * 0.3, minY + height * 0.2, width * 0.4, height * 0.8);
                break;
            case KNIGHT:
                // En vez de realizar el caballo como un simple rectángulo con un
                // triángulo encima, se pintará un cuerpo con patas, una pequeña
                // cola y la cabeza será un triángulo
                double maxX = minX + width;
                double maxY = minY + height;
                // Definimos el polígono que pintará el cuerpo del caballo y lo 
                // pintamos
                double[] x = {minX + width * 0.15, minX + width * 0.15,
                    maxX - width * 0.15, maxX - width * 0.15,
                    minX + width * 0.7, minX + width * 0.7,
                    minX + width * 0.3, minX + width * 0.3
                };
                double[] y = {maxY, minY + height * 0.4,
                    minY + height * 0.4, maxY,
                    maxY, minY + height * 0.75,
                    minY + height * 0.75, maxY
                };
                gc.fillPolygon(x, y, 8);
                // Definimos el polígono que pintará la cola del caballo y lo 
                // pintamos
                double[] x2 = {minX, minX,
                    minX + width * 0.15, minX + width * 0.15
                };
                double[] y2 = {minY + height * 0.6, minY + height * 0.8,
                    minY + height * 0.6, minY + height * 0.4
                };
                gc.fillPolygon(x2, y2, 4);
                // Pintamos el triángulo que hará las funciones de cabeza del 
                // caballo.
                drawTriangle(gc, minX + width * 0.45, minY - height * 0.1, width * 0.6, height * 0.5);
                break;
            case PAWN:
                drawTriangle(gc, minX, minY + height * 0.3, width, height * 0.7);
                break;
        }
    }

    Bounds getBoardBounds(Canvas canvas) {
        Bounds bounds = canvas.getBoundsInLocal();
        return new BoundingBox(bounds.getMinX() + LEFT_MARGIN, bounds.getMinY() + TOP_MARGIN,
                bounds.getWidth() - LEFT_MARGIN, bounds.getHeight() - TOP_MARGIN);
    }

    void drawPiece(Canvas canvas, Bounds boardBounds, ChessPiece piece) {
        PiecePosition position = board.getPiecePosition(piece);
        int c = position.getColumn();
        int r = position.getRow();
        double width = boardBounds.getWidth() / 8;
        double height = boardBounds.getHeight() / 8;

        drawPiece(canvas, piece, boardBounds.getMinX() + c * width,
                boardBounds.getMinY() + r * height, width, height);
    }

    void draw(Canvas canvas) {
        Bounds bounds = canvas.getBoundsInLocal();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Bounds boardBounds = getBoardBounds(canvas);
        double width = boardBounds.getWidth() / 8;
        double height = boardBounds.getHeight() / 8;

        gc.setFill(Color.WHITE);
        gc.clearRect(0, 0, bounds.getWidth(), bounds.getHeight());
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0)
                    gc.setFill(Color.AQUA);
                else
                    gc.setFill(Color.YELLOW);

                gc.fillRect(boardBounds.getMinX() + i * width, boardBounds.getMinY() + j * height,
                        width, height);
            }
        }

        for (ChessPiece piece : board.getPieces()) 
            drawPiece(canvas, boardBounds, piece);

        gc.setFill(Color.BLACK);

        for (int i = 0; i < 8; i++) {
            gc.fillText("(" + (i + 1) + ")", 10, TOP_MARGIN + (i + 0.5) * height);
            gc.fillText("(" + (i + 1) + ")", LEFT_MARGIN + (i + 0.5) * width, 10);
        }
    }

    ChessPiece getPieceAt(Canvas canvas, double x, double y) {
        Bounds boardBounds = getBoardBounds(canvas);
        double width = boardBounds.getWidth() / 8;
        double height = boardBounds.getHeight() / 8;

        for (ChessPiece piece : board.getPieces()) {
            PiecePosition position = board.getPiecePosition(piece);
            int c = position.getColumn();
            int r = position.getRow();

            double minx = boardBounds.getMinX() + (c + 0.0) * width;
            double maxx = boardBounds.getMinX() + (c + 1.0) * width;
            double miny = boardBounds.getMinY() + (r + 0.0) * height;
            double maxy = boardBounds.getMinY() + (r + 1.0) * height;

            if (minx <= x && x <= maxx && miny <= y && y <= maxy) {
                return piece;
            }
        }

        return null;
    }

    public void setMovingPiece(ChessPiece movingPiece) {
        this.movingPiece = movingPiece;
    }

    public ChessPiece getMovingPiece() {
        return movingPiece;
    }

    boolean movePieceTo(Canvas canvas, double x, double y) {
        if (movingPiece == null) {
            return false;
        }

        Bounds boardBounds = getBoardBounds(canvas);
        if (boardBounds.contains(x, y) && containsKing(ChessPiece.Color.WHITE)
                && containsKing(ChessPiece.Color.BLACK)) {
            double width = boardBounds.getWidth() / 8;
            double height = boardBounds.getHeight() / 8;
            int column = (int) ((x - boardBounds.getMinX()) / width);
            int row = (int) ((y - boardBounds.getMinY()) / height);
            PiecePosition position = new PiecePosition(column, row);
            if (movingPiece.canMoveToPosition(position, board)) {
                return board.movePieceTo(movingPiece, position);
            }
        }
        return false;
    }

    boolean containsKing(ChessPiece.Color aColor) {
        return board.containsKing(aColor);
    }

    ChessBoard getBoardImpl() {
        return this.board;
    }

    /**
     * Método que pinta las opciones disponibles de movimiento de la pieza
     * 
     * @param canvas tablero y piezas actuales
     * @param posicionesPosibles array de las piezas posibles a pintar
     */
    void pintarMovimientosPosibles(Canvas canvas, PiecePosition[] posicionesPosibles) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Bounds boardBounds = getBoardBounds(canvas);
        double width = boardBounds.getWidth() / 8;
        double height = boardBounds.getHeight() / 8;

        // Para cada posición la pintaremos de un color, revisaremos si la posición
        // tiene una pieza (que se puede comer) y en tal caso, pintamos la pieza
        // para que no quede oculta al pintar el rectángulo
        for (PiecePosition posicionPieza : posicionesPosibles) {
            gc.setFill(Color.SLATEBLUE);
            gc.fillRect(boardBounds.getMinX() + posicionPieza.getColumn() * width, boardBounds.getMinY() + posicionPieza.getRow() * height,
                    width, height);
            ChessPiece piezaPosicion = board.getPieceAt(posicionPieza);
            if (piezaPosicion != null)
                drawPiece(canvas, boardBounds, piezaPosicion);
        }
    }
}
