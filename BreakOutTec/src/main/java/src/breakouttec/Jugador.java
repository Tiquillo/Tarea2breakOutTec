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
import javafx.scene.shape.Circle;
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
    private Text scoretxt;

    private Text livescount;

    private Text levelcount;

    private Text power;

    private Text speed;

    private final Group group = new Group();

    private final ListaBolas listaBolas;

    private Integer score = 0;

    private Integer vidas = 3;

    private Integer nivel = 1;

    private Integer velocidad = 3;

    private final Raqueta raq = Raqueta.getInstance();

    private Ladrillos eliminarLad = null;

    private String poder = "";

    LadList ladrillosList = new LadList();

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

        Integer principalX = Math.round(principal.getPosicion()[0]);
        Integer principalY = Math.round(principal.getPosicion()[1]);
        manejoJson.AddBolas(principalX,principalY);

//        for (Integer i = 0; i < 5; i++) {
//            Bola temp = new Bola();
//            temp.getBola().setCenterX(-100);
//            temp.getBola().setCenterY(-100);
//            //temp.setDireccion(0f);
//            listaBolas.Insertar(temp);
//            group.getChildren().add(temp.getBola());
//            manejoJson.AddBolas(principalX,principalY);
//        }

        manejoJson.setDatos(respuesta);
        manejoJson.SetPuntos(score);
        manejoJson.SetVidas(vidas);
        manejoJson.SetRaqueta(raq.getX(),raq.getY(),raq.getWidth());
        manejoJson.AddAction(2);

        Nodo temp = ladrillosList.getPrimero();
        while (manejoJson.i < manejoJson.size){
            temp.getLadrillo().setEfecto(manejoJson.getPower());
            temp.getLadrillo().setPts(manejoJson.getPts());
            manejoJson.Next();
            temp = temp.getSiguiente();
        }
        manejoJson.ResetI();

        cliente.sentString(manejoJson.GetJsonString());
        //System.out.println("primer respuesta: " + respuesta);

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

        scoretxt = new Text(5, 40, String.valueOf(this.score));
        scoretxt.setFill(Color.WHITE);
        scoretxt.setFont(font);
        group.getChildren().add(scoretxt);

        Text text2 = new Text(105, 20, "Vidas: ");
        text2.setFill(Color.WHITE);
        text2.setFont(font);
        group.getChildren().add(text2);

        livescount = new Text(105, 40, String.valueOf(this.vidas));
        livescount.setFill(Color.WHITE);
        livescount.setFont(font);
        group.getChildren().add(livescount);

        Text text3 = new Text(205, 20, "Nivel: ");
        text3.setFill(Color.WHITE);
        text3.setFont(font);
        group.getChildren().add(text3);

        levelcount = new Text(205, 40, String.valueOf(this.nivel));
        levelcount.setFill(Color.WHITE);
        levelcount.setFont(font);
        group.getChildren().add(levelcount);

        Text text4 = new Text(305, 20, "Velocidad: ");
        text4.setFill(Color.WHITE);
        text4.setFont(font);
        group.getChildren().add(text4);

        speed = new Text(305, 40, String.valueOf(this.velocidad));
        speed.setFill(Color.WHITE);
        speed.setFont(font);
        group.getChildren().add(speed);

//        Text text5 = new Text(405, 20, "Poder Activado: ");
//        text5.setFill(Color.WHITE);
//        text5.setFont(font);
//        group.getChildren().add(text5);
//
//        power = new Text(405, 40, poder);
//        power.setFill(Color.WHITE);
//        power.setFont(font);
//        group.getChildren().add(power);

        group.getChildren().add(raq.getRaqueta());

        Button btn = new Button("Salir");
        btn.setLayoutX(725);
        btn.setLayoutY(565);
        btn.setMinWidth(70);
        btn.setMinHeight(30);
        group.getChildren().add(btn);
        btn.setOnAction(e -> {
            System.exit(0);
        });

        CheckEvent();

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
                        Circle bola = listaBolas.getBola(i).getBola();
                        bola.setCenterX(-100);
                        bola.setCenterY(-100);
                        Integer bolaX = Math.round(listaBolas.getBola(i).getPosicion()[0]);
                        Integer bolaY = Math.round(listaBolas.getBola(i).getPosicion()[1]);
                        manejoJson.UpdateBall(bolaX, bolaY, i);
                        listaBolas.getBola(i).setActive(false);
                        //corriendo = false;
                    }
                }
                if (!CheckBolas()) {
                    //corriendo = false;
                    listaBolas.getPrimero().getBola().setActive(true);
                    listaBolas.getBola(0).getBola().setCenterX(400);
                    listaBolas.getBola(0).getBola().setCenterY(380);
                    listaBolas.getBola(0).setDireccion(270f);
                    Float[] posicion = {Float.valueOf(400), Float.valueOf(280)};
                    listaBolas.getBola(0).setPosicion(posicion);
                    listaBolas.getBola(0).setDireccion(-270f);
                    Integer bolaX = Math.round(listaBolas.getBola(0).getPosicion()[0]);
                    Integer bolaY = Math.round(listaBolas.getBola(0).getPosicion()[1]);
                    manejoJson.UpdateBall(bolaX, bolaY, 0);
                    vidas --;
                    livescount.setText(String.valueOf(vidas));
                    manejoJson.SetVidas(vidas);
                }
                if (vidas == 0) {
                    textoDeAyuda.setText("Has perdido");
                    textoDeAyuda.setX(275);
                    corriendo = false;
                    break;
                }

                for (int i = 0; i < listaBolas.getCantidad(); i++) {

                    manejoJson.UpdateBall(Math.round(listaBolas.getBola(i).getPosicion()[0]),Math.round(listaBolas.getBola(i).getPosicion()[1]), i);
                }
                cliente.sentString(manejoJson.GetJsonString());

                if (CheckLadrillos()) {
                    try {
                        manejoJson.ResetJson();
                        Reset();
                        nivel ++;
                        levelcount.setText(String.valueOf(nivel));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread.start();

    }

    /**
     * Revisa si todas las bolas están activas en el juego.
     * @return
     */
    public Boolean CheckBolas(){

        for (Integer i = 0; i < listaBolas.getCantidad(); i++) {
            if (listaBolas.getBola(i).isActive()) {
                return true;
            }
        }
        return false;

    }

    public void Reset(){
        vidas = 3;
        livescount.setText(String.valueOf(vidas));
        manejoJson.SetVidas(vidas);
        listaBolas.getBola(0).getBola().setCenterX(400);
        listaBolas.getBola(0).getBola().setCenterY(380);
        listaBolas.getBola(0).setDireccion(270f);
        Float[] posicion = {Float.valueOf(400), Float.valueOf(280)};
        listaBolas.getBola(0).setPosicion(posicion);
        listaBolas.getBola(0).setDireccion(-270f);
        Integer bolaX = Math.round(listaBolas.getBola(0).getPosicion()[0]);
        Integer bolaY = Math.round(listaBolas.getBola(0).getPosicion()[1]);
        manejoJson.UpdateBall(bolaX, bolaY, 0);
        listaBolas.getBola(0).setActive(true);

        for (Integer i = 0; i < ManejoJsonSingleton.getInstance().size; i++) {
            Ladrillos temp = ladrillosList.Acceder(i);
            temp.getLadrillo().setX(ManejoJsonSingleton.getInstance().getXPos());
            temp.getLadrillo().setY(ManejoJsonSingleton.getInstance().getYPos());


            ManejoJsonSingleton.getInstance().Next();
        }
        ManejoJsonSingleton.getInstance().ResetI();


    }

    /**
     * Revisa si todos los ladrillos están destruidos.
     * @return
     */
    public Boolean CheckLadrillos(){

        for (Integer i = 0; i < ladrillosList.getSize(); i++) {
            if (ladrillosList.Acceder(i).getLadrillo().getX() != -100) {
                return false;
            }
        }
        return true;

    }

    /**
     * Incrementa la puntuación del jugador.
     * @param pts
     */
    public void IncScore(Integer pts){
        score += pts;
        scoretxt.setText(String.valueOf(score));
        manejoJson.SetPuntos(score);
    }

    /**
     * Incrementa la vida del jugador.
     */
    public void IncLives(){
        vidas += 1;
        livescount.setText(String.valueOf(vidas));
        manejoJson.SetVidas(vidas);
    }

    /**
     * Incrementa el nivel del jugador.
     */
    public void IncLevel(){
        nivel += 1;
        levelcount.setText(String.valueOf(nivel));
    }


    /**
     * Incrementa la velocidad de la bola
     */
    public void IncSpeed(){
        velocidad += 1;
        speed.setText(String.valueOf(velocidad));
    }

    /**
     * Reduce la velocidad de la bola
     */
    public void DecSpeed(){
        velocidad -= 1;
        speed.setText(String.valueOf(velocidad));
    }

    /**
        * Getters y Setters para las listas de ladrillos y bolas, junto con los componentes.
     */
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
