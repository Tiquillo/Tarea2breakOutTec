package src.breakouttec;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import ladrillosPack.*;
import javafx.scene.text.*;


public class main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage win) {

        win.setTitle("BreakOutTec!");
        Group group = new Group();
        Scene scene = new Scene(group, 800, 600, Color.AZURE);
        win.setScene(scene);

        Button btn = new Button("Jugador");
        btn.setLayoutX(325);
        btn.setLayoutY(190);
        btn.setMinWidth(150);
        btn.setMinHeight(100);
        group.getChildren().add(btn);
        btn.setOnAction(e -> {
            win.close();
            Jugador.getInstance().start(new Stage());
        });

        Button btn2 = new Button("Espectador");
        btn2.setLayoutX(325);
        btn2.setLayoutY(310);
        btn2.setMinWidth(150);
        btn2.setMinHeight(100);
        group.getChildren().add(btn2);

        win.show();
    }
}
