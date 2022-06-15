package src.breakouttec;

import componentes.Bola;
import componentes.ListaBolas;
import componentes.Raqueta;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import json.ManejoJsonSingleton;
import ladrillosPack.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import serverSockets.SocketClient;

public class Jugador extends Application {

    private static Jugador instance = null;

    private final Font font = new Font("Liberation Sans Italic", 16);

    private final Text textoDeAyuda = new Text();
    private final Group group = new Group();

    private final ListaBolas listaBolas;

    private Integer score = 0;

    private Integer vidas = 3;

    private final Raqueta raq = Raqueta.getInstance();

    private Ladrillos eliminarLad = null;

    LadList ladrillosList = new LadList();
    //LadList tempLadList = new LadList(); // TODO borrar esto xd es temporal

    private Boolean corriendo = false;
    private SocketClient cliente = new SocketClient("127.0.0.1", 8080);

    private JSONObject obj = new JSONObject();

    private Stage win;

    private ManejoJsonSingleton manejoJson = ManejoJsonSingleton.getInstance();

    public static Jugador getInstance() throws ParseException {
        if (instance == null) {
            instance = new Jugador();
        }
        return instance;
    }

    /**
     * Constructor de la clase Jugador
     */
    private Jugador() throws ParseException {

        Integer x = 12;
        Integer y = 55;
        for (Integer i = 0; i < 14; i++) {
            Ladrillos temp = new LadRojo(x, y);
            ladrillosList.Insertar(temp);
            group.getChildren().add(temp.getLadrillo());
            if (x + 112 < 795){
                x += 112;
            } else {
                x = 12;
                y += 20;
            }
        }

        for (Integer i = 0; i < 14; i++) {
            Ladrillos temp = new LadNaranja(x, y);
            ladrillosList.Insertar(temp);
            group.getChildren().add(temp.getLadrillo());
            if (x + 112 < 795){
                x += 112;
            } else {
                x = 12;
                y += 20;
            }
        }

        for (Integer i = 0; i < 14; i++) {
            Ladrillos temp = new LadAmarillo(x, y);
            ladrillosList.Insertar(temp);
            group.getChildren().add(temp.getLadrillo());
            if (x + 112 < 795){
                x += 112;
            } else {
                x = 12;
                y += 20;
            }


        }

        for (Integer i = 0; i < 14; i++) {
            Ladrillos temp = new LadVerde(x, y);
            ladrillosList.Insertar(temp);
            group.getChildren().add(temp.getLadrillo());
            if (x + 112 < 795){
                x += 112;
            } else {
                x = 12;
                y += 20;
            }
        }

        obj.put("accion", 1);
        String json = obj.toJSONString();
        cliente.sentString(json);
        String respuesta = cliente.receiveString(); //Devuelve los ladrillos

        listaBolas = new ListaBolas();
        Bola principal = new Bola();
        principal.setDireccion(270f);
        listaBolas.Insertar(principal);
        group.getChildren().add(principal.getBola());

        manejoJson.setDatos(respuesta);
        manejoJson.SetPuntos(score);
        manejoJson.SetVidas(vidas);
        manejoJson.SetRaqueta(raq.getX(),raq.getY(),raq.getWidth());
        Integer principalX = Math.round(principal.getPosicion()[0]);
        Integer principalY = Math.round(principal.getPosicion()[1]);
        manejoJson.AddBolas(principalX,principalY);

        cliente.sentString(manejoJson.GetJsonString());
        System.out.println("primer respuesta: " + respuesta);

    }

    /**
     * Método que se ejecuta al iniciar la aplicación. Establece los elementos del contenido y los dibuja
     * @param window ventana principal
     */
    public void start(Stage window) {
        this.win = window;
        win.setTitle("BreakOutTec!-Jugador");
        Scene scene = new Scene(group, 800, 600, Color.BLACK);

        textoDeAyuda.setFont(font);
        textoDeAyuda.setFill(Color.WHITE);
        textoDeAyuda.setText("Pulse E para comenzar");
        textoDeAyuda.setX(325);
        textoDeAyuda.setY(300);
        group.getChildren().add(textoDeAyuda);

        Text text = new Text(5, 20, "Puntuacion: ");
        text.setFill(Color.WHITE);
        text.setFont(font);
        group.getChildren().add(text);

        Text score = new Text(5, 40, String.valueOf(this.score));
        score.setFill(Color.WHITE);
        score.setFont(font);
        group.getChildren().add(score);

        group.getChildren().add(raq.getRaqueta());

        Button btn = new Button("Salir");
        btn.setLayoutX(725);
        btn.setLayoutY(565);
        btn.setMinWidth(70);
        btn.setMinHeight(30);
        group.getChildren().add(btn);
        btn.setOnAction(e -> {
            group.getChildren().remove(ladrillosList.Acceder(7).getLadrillo());
        });

        CheckEvent();

        if (eliminarLad != null) {
            group.getChildren().remove(eliminarLad.getLadrillo());
            eliminarLad = null;
        }

        win.setScene(scene);
        win.show();
    }

    /**
        * Metodo que se encarga de controlar los eventos de teclado
     */

    private void CheckEvent(){
        final Boolean[] moviendose = {false};
        group.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                //System.out.println(event.getCode());
                if (event.getCode() == javafx.scene.input.KeyCode.A) {
                    //System.out.println("Izquierda");
                    if (corriendo) {
                        raq.MoverIzquierda();
                        if (!moviendose[0]) textoDeAyuda.setText("");
                        moviendose[0] = true;
                    }
                } else if (event.getCode() == javafx.scene.input.KeyCode.D) {
                    //System.out.println("Derecha");
                    if (corriendo){
                        raq.MoverDerecha();
                        if (!moviendose[0]) textoDeAyuda.setText("");
                        moviendose[0] = true;
                    }
                } else if (event.getCode() == KeyCode.E) {
                    //TODO mostrar en pantalla que toque E para empezar
                    //System.out.println("Empezar");
                    if (!corriendo) {
                        Bucle();
                        corriendo = true;
                    }

                }
            }
        });

    }

    /**
        * Mueve la bola mientras el juego está corriendo
     */
    void Bucle() {
        Thread thread = new Thread(() -> {

            textoDeAyuda.setText("Utilice A y D para mover la raqueta");
            textoDeAyuda.setX(275);

            while (corriendo) {
                try {
                    Thread.sleep(16, 666);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for (Integer i = 0; i < listaBolas.getCantidad(); i++) {

                    try {
                        listaBolas.getBola(i).Mover(ladrillosList, raq);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }

                    if (listaBolas.getBola(i).getPosicion()[1] > 505) {
                        //TODO destruir la bola
                        //listaBolas.Eliminar(i);
                        //corriendo = false;
                    }
                }
                if (listaBolas.getCantidad() == 0) {
                    corriendo = false;
                    vidas--;
                    Bola principal = new Bola();
                    principal.setDireccion(270f);
                    listaBolas.Insertar(principal);
                }
                if (vidas == 0) {
                    //TODO mostrar que perdió
                }
            }
        });
        thread.start();

    }


    /**
        * getters y setters
     */
    public Group getGroup() {
        return group;
    }

    public ListaBolas getListaBolas(){
        return listaBolas;
    }

    public Raqueta getRaqueta(){
        return raq;
    }

    public void setVidas(Integer vidas){
        this.vidas = vidas;
    }

    public Integer getVidas(){
        return vidas;
    }

    public LadList getLadrillosList(){
        return ladrillosList;
    }
}
