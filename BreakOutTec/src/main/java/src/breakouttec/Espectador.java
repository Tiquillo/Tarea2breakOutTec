package src.breakouttec;

import componentes.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ladrillosPack.*;
import javafx.scene.text.*;
import serverSockets.SocketClient;

public class Espectador extends Application {

    private static Espectador instance = null;

    private Group group = new Group();

    private Scene scene = new Scene(group, 800, 600);

    private SocketClient observador = new SocketClient("127.0.0.1", 8080);

    public static Espectador getInstance() {
        if (instance == null) {
            instance = new Espectador();
        }
        return instance;
    }

    private Espectador() {

        String respuesta = observador.receiveString();

        //Formar la lista de los ladrillos, etc

    }

    public void start(Stage win) {
        win.setTitle("Breakout-Espectador");
        win.setResizable(false);

        //Agregar lo que tenga que ir en el monitor, hay que poner un boton que sino no sirve por alguna razon
        Button btn = new Button("Salir");
        btn.setLayoutX(725);
        btn.setLayoutY(565);
        btn.setMinWidth(70);
        btn.setMinHeight(30);
        group.getChildren().add(btn);

        win.setScene(scene);
        win.show();
    }

}
