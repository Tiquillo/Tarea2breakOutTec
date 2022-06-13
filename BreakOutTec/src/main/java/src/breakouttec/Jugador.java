package src.breakouttec;

import javafx.application.Application;
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

    private Integer score = 0;

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

//        Button btn = new Button("Jugador");
//        btn.setLayoutX(325);
//        btn.setLayoutY(190);
//        btn.setMinWidth(150);
//        btn.setMinHeight(100);
//        group.getChildren().add(btn);
//        btn.setOnAction(e -> {
//            this.score += 1;
//
//        });

        win.setScene(scene);
        win.show();
    }
}
