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

public class Jugador extends Application {

    private static Jugador instance = null;

    private Font font = new Font("Liberation Sans Italic", 16);
    private Group group = new Group();

    private ListaBolas listaBolas;

    private Integer score = 0;

    private Raqueta raq = Raqueta.getInstance();

    public static Jugador getInstance() {
        if (instance == null) {
            instance = new Jugador();
        }
        return instance;
    }

    private Jugador() {


        LadList ladrillosList = new LadList();
        Integer x = 3;
        Integer y = 55;
        for (Integer i = 0; i < 30; i++) {
            ladrillos temp = new ladRojo(x, y);
            ladrillosList.Insertar(temp);
            group.getChildren().add(temp.getLadrillo());
            if (x+53 < 795){
                x +=53;
            } else {
                x = 3;
                y += 20;
            }
        }

        for (Integer i = 0; i < 30; i++) {
            ladrillos temp = new ladNaranja(x, y);
            ladrillosList.Insertar(temp);
            group.getChildren().add(temp.getLadrillo());
            if (x+53 < 795){
                x +=53;
            } else {
                x = 3;
                y += 20;
            }
        }

        for (Integer i = 0; i < 30; i++) {
            ladrillos temp = new ladAmarillo(x, y);
            ladrillosList.Insertar(temp);
            group.getChildren().add(temp.getLadrillo());
            if (x+53 < 795){
                x +=53;
            } else {
                x = 3;
                y += 20;
            }
        }

        for (Integer i = 0; i < 30; i++) {
            ladrillos temp = new ladVerde(x, y);
            ladrillosList.Insertar(temp);
            group.getChildren().add(temp.getLadrillo());
            if (x+53 < 795){
                x +=53;
            } else {
                x = 3;
                y += 20;
            }
        }

        listaBolas = new ListaBolas();
        Bola principal = new Bola();
        listaBolas.Insertar(principal);
        group.getChildren().add(principal.getBola());

    }

    public void start(Stage win) {
        win.setTitle("BreakOutTec!-Jugador");
        Scene scene = new Scene(group, 800, 600, Color.BLACK);

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

        CheckEvent();

        win.setScene(scene);
        win.show();
    }

    private void CheckEvent(){

        group.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                //System.out.println(event.getCode());
                if (event.getCode() == javafx.scene.input.KeyCode.A) {
                    //System.out.println("Izquierda");
                    raq.moverIzquierda();
                } else if (event.getCode() == javafx.scene.input.KeyCode.D) {
                    //System.out.println("Derecha");
                    raq.moverDerecha();
                }
            }
        });

    }

    public Group getGroup() {
        return group;
    }

    public ListaBolas getListaBolas(){
        return listaBolas;
    }
}
