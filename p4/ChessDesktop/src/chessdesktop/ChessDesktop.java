package chessdesktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase que inicializa el modo gráfico del ajedrez
 *
 * @author Javier Morcillo
 */
public class ChessDesktop extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Se carga la gráfica elegida para el ajedrez, con los botones, etiquetas
        // y tablero definidos en el fichero fxml
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
