package ladrillosPack;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Ladrillos {

    private Integer x;
    private Integer y;

    private String efecto;

    private Rectangle ladrillo;

    /**
     * Constructor de la clase ladrillos
     * @param x Posición en x
     * @param y Posición en y
     * @param color Color del ladrillo
     */
    public Ladrillos(Integer x, Integer y, Color color) {
        this.x = x;
        this.y = y;

        this.ladrillo = new Rectangle(x, y, 100, 15);
        this.ladrillo.setFill(color);
    }

    /**
     * Elimina el ladrillo si fue colisionado y activa su efecto en caso de tener alguno
     */
    public void Colision(){
        //TODO Colisión no funciona bien
        this.ActivarEfecto();
        //Jugador.getInstance().getGroup().getChildren().remove(this.getLadrillo());
    }

    /**
     * Activa el efecto del ladrillo
     */
    private void ActivarEfecto() {

        System.out.println("Activando " + this.getEfecto());
    }

    /*
        * Getters y Setters
     */
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

    public Float[] getPosicion(){
        return new Float[]{(float) this.getLadrillo().getX(), (float) this.getLadrillo().getY()};
    }

}
