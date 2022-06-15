package ladrillosPack;

import componentes.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import json.ManejoJsonSingleton;
import org.json.simple.parser.ParseException;
import src.breakouttec.Jugador;

public class Ladrillos {

    private Integer x;
    private Integer y;

    private Integer pts = 0;

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
    public void Colision() throws ParseException {
        //TODO Colisión no funciona bien
        Jugador.getInstance().IncScore(pts);
        this.ActivarEfecto();
        Jugador.getInstance().getLadrillosList().Eliminar(this);
        Integer indice = Jugador.getInstance().getLadrillosList().getIndice(this);
        if (indice != -1) {
            ManejoJsonSingleton.getInstance().RemoveLadrillo(indice);
        }
        this.getLadrillo().setX(-100);
    }

    /**
     * Activa el efecto del ladrillo
     */
    private void ActivarEfecto() throws ParseException {

        //Jugador.getInstance().SetPoder(this.efecto);
        if (efecto == ""){

        } else if (efecto == "dobleraq"){

            if (Jugador.getInstance().getRaqueta().getWidth()<400) {
                Jugador.getInstance().getRaqueta().setWidth(Jugador.getInstance().getRaqueta().getWidth()*2);
            }
        } else if (efecto == "mitadraq"){
            if (Jugador.getInstance().getRaqueta().getWidth()>=20) {
                Jugador.getInstance().getRaqueta().setWidth(Jugador.getInstance().getRaqueta().getWidth()/2);
            }

        } else if (efecto == "masvel"){
            Jugador.getInstance().IncSpeed();
            ListaBolas bolas = Jugador.getInstance().getListaBolas();
            NodoBola temp = bolas.getPrimero();
            for (int i = 0; i < bolas.getcantidad(); i++) {
                temp.getBola().setVelocidad(temp.getBola().getVelocidad() + 1);
                temp = temp.getSiguiente();
            }
        } else if (efecto == "menosvel"){
            if (Jugador.getInstance().getListaBolas().getPrimero().getBola().getVelocidad() > 1){
                Jugador.getInstance().DecSpeed();
                ListaBolas bolas = Jugador.getInstance().getListaBolas();
                NodoBola temp = bolas.getPrimero();
                for (int i = 0; i < bolas.getcantidad(); i++) {
                    temp.getBola().setVelocidad(temp.getBola().getVelocidad() - 1);
                    temp = temp.getSiguiente();
                }
            }

        } else if (efecto == "masbolas"){

//            for (int i = 0; i < Jugador.getInstance().getListaBolas().getcantidad(); i++) {
//
//                if (!Jugador.getInstance().getListaBolas().getBola(i).isActive()){
//                    Bola bola = Jugador.getInstance().getListaBolas().getBola(i);
//                    bola.setActive(true);
//                    bola.getBola().setCenterX(400);
//                    bola.getBola().setCenterY(300);
//                    Float[] pos = {Float.valueOf(400), Float.valueOf(300)};
//                    bola.setPosicion(pos);
//                    bola.setDireccion(180f);
//                    ManejoJsonSingleton.getInstance().UpdateBall(400, 300,i);
//                }
//
//            }

        } else if (efecto == "vida"){
            Jugador.getInstance().setVidas(Jugador.getInstance().getVidas() + 1);
            Jugador.getInstance().IncLives();
        }
    }

    /**
     Getters y Setters
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

    public Integer getPts() {
        return pts;
    }

    public void setPts(Integer pts) {
        this.pts = pts;
    }

    public String getEfecto(){
        return efecto;
    }

    /**
     * setEfecto
     * @param efecto
     * Asigna un efecto segun el numero que se le pase
     */
    public void setEfecto(Integer efecto){
        if (efecto == 0){
            this.efecto = "";
        } else if (efecto == 1){
            this.efecto = "dobleraq";
        } else if (efecto == 2){
            this.efecto = "mitadraq";
        } else if (efecto == 3){
            this.efecto = "masvel";
        } else if (efecto == 4){
            this.efecto = "menosvel";
        } else if (efecto == 5){
            this.efecto = "masbolas";
        } else if (efecto == 6){
            this.efecto = "vida";
        }
    }

    public Float[] getPosicion(){
        return new Float[]{(float) this.getLadrillo().getX(), (float) this.getLadrillo().getY()};
    }

}
