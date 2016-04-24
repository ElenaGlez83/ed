package chessdesktop;

import Chess.ChessAI;
import Chess.ChessPiece;
import Chess.ChessRandomAI;
import Chess.PiecePosition;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Clase que permite jugar de forma gráfica, controlando las acciones de todos
 * los botones presentes y las acciones sobre las piezas que se encuentran en el
 * tablero.
 *
 * @author Javier Morcillo y Elena González
 */
public class FXMLDocumentController implements Initializable {

    ChessBoardRenderer board = new ChessBoardRenderer();

    @FXML
    private Label label;
    @FXML
    private Canvas canvas;

    private ChessPiece.Color piezasMueven = ChessPiece.Color.WHITE;
    private ChessPiece.Color piezaSeleccionada;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        board = new ChessBoardRenderer();
        board.draw(canvas);
        // Borramos las etiquetas que se estén mostrando.
        label.setText("");
        // Volvemos a establecer que las piezas que deben mover son las blancas
        piezasMueven = ChessPiece.Color.WHITE;
    }

    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        // Abriremos la ventana para que el usuario seleccione el fichero donde
        // desea guardar la partida
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Game");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Chess Files", "*.chess.xml"));
        File file = fileChooser.showSaveDialog(null);
        // Una vez seleccionado un fichero, lo intentaremos guardar y mostraremos
        // el mensaje acorde a si ha ido bien o no el guardado
        if (file != null) {
            if (board.getBoardImpl().saveToFile(file)) {
                label.setText("Partida guardada correctamente");
            } else {
                label.setText("Error al guardar la partida");
            }
        }
    }

    @FXML
    private void handleLoadButtonAction(ActionEvent event) {
        // Abriremos la ventana para que el usuario seleccione el fichero a abrir
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Chess Files", "*.chess.xml"));
        File selectedFile = fileChooser.showOpenDialog(null);
        // Una vez seleccionado un fichero, lo intentaremos cargar y mostraremos
        // el mensaje acorde a si ha ido bien o no la carga
        if (selectedFile != null) {
            if (board.getBoardImpl().loadFromFile(selectedFile)) {
                board.draw(canvas);
                label.setText("Partida cargada correctamente");
            } else {
                label.setText("Error al cargar la partida");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Creamos al jugador de inteligencia artificial que será el que mueva
        // las piezas negras, y dibujamos el tablero.
        ChessAI ai = new ChessRandomAI();
        board.draw(canvas);

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            // Se comprueba si ha terminado el juego, comprobando si ambos reyes
            // siguen en el tablero.
            if (board.containsKing(ChessPiece.Color.BLACK) && board.containsKing(ChessPiece.Color.WHITE)) {
                // Eliminamos la label que esté mostrado ahora, para que si se 
                // ha cargado o guardado una partida deje de aparecer el mensaje
                // asociado a ello
                label.setText("");
                // Obtenemos la pieza que se ha seleccionado
                Chess.ChessPiece piece = board.getPieceAt(canvas, e.getX(), e.getY());
                // Si le toca jugar a las blancas, miraremos si la pieza no es 
                // nula y si la pieza seleccionada es blanca también para permitirle
                // jugar con ella.
                if (piezasMueven == ChessPiece.Color.WHITE) {
                    if (piece != null && piezasMueven == piece.getColor()) {
                        // Si hacemos click en una pieza que estaba marcada como
                        // la que íbamos a mover, la volvemos a su color original
                        // y repintamos el tablero.
                        if (board.getMovingPiece() == piece) {
                            board.setMovingPiece(null);
                            board.draw(canvas);
                            return;
                        }
                        // Si en el tablero no existe una pieza marcada como la
                        // que se va a mover, marcamos esta como dicha pieza y 
                        // pintamos las posiciones posibles de movimiento para
                        // esa pieza.
                        if (board.getMovingPiece() == null) {
                            board.setMovingPiece(piece);
                            board.draw(canvas);
                            PiecePosition[] posicionesPosibles = piece.getAvailablePositions(board.getBoardImpl());
                            if (posicionesPosibles != null && posicionesPosibles.length > 0) {
                                board.pintarMovimientosPosibles(canvas, posicionesPosibles);
                            }
                            piezaSeleccionada = piece.getColor();
                            return;
                        }
                    }
                    // Si se puede mover la pieza al lugar seleccionado y es del
                    // mismo color al que le toca mover, la moveremos y pintaremos
                    // de nuevo el tablero. Comprobando si el juego ha terminado
                    if (board.movePieceTo(canvas, e.getX(), e.getY()) && piezasMueven == piezaSeleccionada) {
                        board.setMovingPiece(null);
                        board.draw(canvas);
                        comprobarFinJuego();
                    }
                }
                // Si le toca jugar a las piezas negras, haremos que la inteligencia
                // artificial mueva y después comprobaremos si ha finalizado el 
                // juego.
                if (piezasMueven == ChessPiece.Color.BLACK) {
                    if (ai.performNextMovement(board.getBoardImpl(), ChessPiece.Color.BLACK)) {
                        board.draw(canvas);
                        comprobarFinJuego();
                    } else {
                        label.setText("Algo ha ido mal, ...");
                    }
                }
            }
        });
    }

    /**
     * Método que comprueba el final del juego viendo si ambos reyes continúan
     * en el tablero. En caso de que el juego haya terminado se mostrará quien
     * gana y en caso contrario se cambiará el color de las piezas que deben
     * jugar el siguiente turno.
     */
    private void comprobarFinJuego() {
        if (!board.containsKing(ChessPiece.Color.BLACK) || !board.containsKing(ChessPiece.Color.WHITE)) {
            if (!board.containsKing(ChessPiece.Color.BLACK)) {
                label.setText("Ganan las blancas");
            } else {
                label.setText("Ganan las negras");
            }
        } else {
            this.piezasMueven = (this.piezasMueven == ChessPiece.Color.WHITE) ? ChessPiece.Color.BLACK : ChessPiece.Color.WHITE;
        }
    }

}
