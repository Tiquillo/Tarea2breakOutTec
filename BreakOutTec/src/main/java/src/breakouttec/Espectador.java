package src.breakouttec;

import componentes.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotResult;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import json.ManejoJsonSingleton;
import ladrillosPack.*;
import javafx.scene.text.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import serverSockets.SocketClient;

public class Espectador extends Application {

    private static Espectador instance = null;

    private Group group = new Group();

    private Scene scene = new Scene(group, 800, 600, Color.BLACK);

    LadList ladrillosList = new LadList();

    ListaBolas listaBolas = new ListaBolas();

    private final SocketClient observador = new SocketClient("127.0.0.1", 8080);

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

        Integer XY = -200;

        // aquí generé todos los ladrillos fuera del campo de visión para asignarles pa posición usando los sockets.

        for (int i = 0; i < 56; i++) {
            Ladrillos temp = null;
            if (i / 14 == 0) {
                temp = new LadRojo(XY, XY);

            } else if (i / 14 == 1) {
                temp = new LadNaranja(XY, XY);

            } else if (i / 14 == 2) {
                temp = new LadAmarillo(XY, XY);

            } else if (i / 14 == 3) {
                temp = new LadVerde(XY, XY);

            }
            ladrillosList.Insertar(temp);
            group.getChildren().add(temp.getLadrillo());
        }

        for (int i = 0; i < 3; i++) {
            Bola temp = new Bola();
            listaBolas.Insertar(temp);
            group.getChildren().add(temp.getBola());
        }

        group.getChildren().add(Raqueta.getInstance().getRaqueta());

        //Agregar lo que tenga que ir en el monitor, hay que poner un boton que sino no sirve por alguna razon

        // esto lo puso sergio así que no lo toqué

        Button btn = new Button("Salir");
        btn.setLayoutX(725);
        btn.setLayoutY(565);
        btn.setMinWidth(70);
        btn.setMinHeight(30);
        group.getChildren().add(btn);

        Bucle();

        win.setScene(scene);
        win.show();
    }

    private void Bucle() {
        Thread thread = new Thread(() -> {

            Integer i = 0;
            final JSONObject json = new JSONObject();
            json.put("tipo", 1);
             String jsonString = json.toJSONString();
            observador.sentString(jsonString);
            String respuesta = observador.receiveString();
            System.out.println("Primera respuesta: " + respuesta);

            // aquí hice lo mismo que hizo david en prueba sockets
            try {
                ManejoJsonSingleton.getInstance().setDatos(respuesta);
                // aquí me pedía un try, se lo puse y ya xd
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            while (true) {
                //este while es el mismo que el de jugador y no debería enciclarse
                // porque el socket pausa la ejecución esperando la llamada.

                observador.sentString(ManejoJsonSingleton.getInstance().GetJsonString());
                respuesta = observador.receiveString(); // esta línea me parece que pausa la ejecución xd
                System.out.println("Respuesta: " + respuesta);

                for (i = 0; i < ManejoJsonSingleton.getInstance().size; i++) {
                    System.out.println("----------------------------------------------------");
                    System.out.println(i);
                    System.out.println(ManejoJsonSingleton.getInstance().getXPos());
                    System.out.println(ManejoJsonSingleton.getInstance().getYPos());
                    System.out.println(ManejoJsonSingleton.getInstance().getPower());
                    System.out.println(ManejoJsonSingleton.getInstance().getPts());
                    System.out.println("----------------------------------------------------");

                    // aquí hice lo mismo que david, pero usando un for
                    // tengo entendido que esto coloca la posición de los objetos en los objetos obteniéndola
                    // desde los sockekts

                    ManejoJsonSingleton.getInstance().Next();
                }
                ManejoJsonSingleton.getInstance().Reset();
            }
        });
    }

}
