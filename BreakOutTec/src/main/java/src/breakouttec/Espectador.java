package src.breakouttec;

import componentes.Bola;
import componentes.ListaBolas;
import componentes.Raqueta;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import json.ManejoJsonSingleton;
import ladrillosPack.*;
import org.json.simple.parser.ParseException;
import serverSockets.SocketClient;

/**
 * Clase espectador que permite ver el juego en tiempo real. Extiende de Application par JavaFX.
 * Es un singleton, por lo que solo se puede instanciar una vez.
 */
public class Espectador extends Application {

    private static Espectador instance = null;

    private Group group = new Group();

    private Scene scene = new Scene(group, 800, 600, Color.BLACK);

    private Raqueta raq = Raqueta.getInstance();

    LadList ladrillosList = new LadList();

    ListaBolas listaBolas = new ListaBolas();

    private final SocketClient observador = new SocketClient("127.0.0.1", 8080);

    /**
     * Constructor de la clase
     * @return Instancia de la clase
     */

    public static Espectador getInstance() {
        if (instance == null) {
            instance = new Espectador();
        }
        return instance;
    }

    /**
     * Constructor de la clase privado para evitar que se pueda instanciar más de una vez
     */
    private Espectador() {

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

        group.getChildren().add(raq.getRaqueta());

        //Agregar lo que tenga que ir en el monitor, hay que poner un boton que sino no sirve por alguna razon

        // esto lo puso sergio así que no lo toqué

        Button btn = new Button("Salir");
        btn.setLayoutX(725);
        btn.setLayoutY(565);
        btn.setMinWidth(70);
        btn.setMinHeight(30);
        group.getChildren().add(btn);
        btn.setOnAction(e -> {
            System.exit(0);
        });

        Bucle();

        win.setScene(scene);
        win.show();
    }

    private void Bucle() {
        Thread thread = new Thread(() -> {

            Integer i = 0;
            String respuesta = observador.receiveString();
            System.out.println("Primera respuesta: " + respuesta);
            try {
                ManejoJsonSingleton.getInstance().setDatos(respuesta);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

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
                    Ladrillos temp = ladrillosList.Acceder(i);
                    temp.getLadrillo().setX(ManejoJsonSingleton.getInstance().getXPos());
                    temp.getLadrillo().setY(ManejoJsonSingleton.getInstance().getYPos());

                    UpdateRaq();

                    ManejoJsonSingleton.getInstance().Next();
                }

                ManejoJsonSingleton.getInstance().ResetI();
            }
        });
        thread.start();
    }

    public void UpdateRaq(){
        raq.getRaqueta().setWidth(ManejoJsonSingleton.getInstance().GetLargoRaqueta());
        raq.getRaqueta().setX(ManejoJsonSingleton.getInstance().GetXRaqueta());
        raq.getRaqueta().setY(ManejoJsonSingleton.getInstance().GetYRaqueta());
    }

}
