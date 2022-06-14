package ladrillosPack;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import src.breakouttec.Jugador;

public class ladrillos {

    private Integer x;
    private Integer y;

    private String efecto;

    private Rectangle ladrillo;

    public ladrillos(Integer x, Integer y, Color color) {
        this.x = x;
        this.y = y;

        this.ladrillo = new Rectangle(x, y, 50, 15);
        this.ladrillo.setFill(color);
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Rectangle getLadrillo() {
        return ladrillo;
    }

    public String getEfecto(){
        return efecto;
    }

    public void setEfecto(String efecto){
        this.efecto = efecto;
    }

    public void Colision(){

        Jugador.getInstance().getGroup().getChildren().remove(this.getLadrillo());

    }

    public void ActivarEfecto() {

        System.out.println("Activando efecto");

    }

    public float[] getPosicion(){
        float[] posicion = new float[2];
        posicion[0] = (float) this.getLadrillo().getX();
        posicion[1] = (float) this.getLadrillo().getY();
        return posicion;
    }

}
