package componentes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ladrillosPack.LadList;
import org.json.simple.parser.ParseException;

import java.util.Objects;

public class Bola {
    private Integer velocidad = 3;
    private Float direccion;
    private String direccionRebote = "\0";

    private Boolean colision = false;
    private Float[] posicion;

    private final Integer radioBola = 10;

    private static final Integer anchoLadrillo = 100;
    private static final Integer altoLadrillo = 15;

    private static final Integer anchoPantalla = 800;
    private static final Integer altoPantalla = 600;

    private final Circle bola;

    /**
        * Constructor de la clase
     */
    public Bola() {

        bola = new Circle(400, 450, radioBola);
        bola.setFill(Color.WHITE);
        posicion = new Float[2];
        posicion[0] = (float) bola.getCenterX();
        posicion[1] = (float) bola.getCenterY();

    }

    /**
        * Identifica una colisión con un ladrillo y obtiene la dirección del rebote
        * @param posLadrillo Posición del ladrillo
     */
    private void Rebote(Float[] posLadrillo) {

        // revisa si la bola está tocando el ladrillo
        if (posicion[1] + radioBola > posLadrillo[1] &&
                posicion[1] - radioBola < posLadrillo[1] + altoLadrillo &&
                posicion[0] + radioBola > posLadrillo[0] &&
                posicion[0] - radioBola < posLadrillo[0] + anchoLadrillo) {

            if (0 < direccion && direccion < 90) {

                if (posicion[0] + radioBola - posLadrillo[0] > posicion[1] + radioBola - posLadrillo[1] &&
                        posicion[0] + radioBola - posLadrillo[0] < radioBola + 1) {
                    direccionRebote = "I";
                } else if (posicion[0] + radioBola - posLadrillo[0] ==
                        posicion[1] + radioBola - posLadrillo[1]) {
                    direccionRebote = "DI";
                } else {
                    direccionRebote = "AR";
                }
            } else if (90 < direccion && direccion < 180) {

                if (posicion[0] + radioBola - posLadrillo[0] + anchoLadrillo > posicion[1] + radioBola - posLadrillo[1] &&
                        posicion[0] + radioBola - posLadrillo[0] + anchoLadrillo < radioBola + 1) {

                    direccionRebote = "D";

                } else if (posicion[0] + radioBola - posLadrillo[0] + anchoLadrillo ==
                        posicion[1] + radioBola - posLadrillo[1]) {

                    direccionRebote = "DI";
                } else {
                    direccionRebote = "AR";
                }

            } else if (180 < direccion && direccion < 270) {

                if (posicion[0] + radioBola - posLadrillo[0] + anchoLadrillo >
                        posicion[1] + radioBola - posLadrillo[1] + altoLadrillo &&
                        posicion[0] + radioBola - posLadrillo[0] + anchoLadrillo < radioBola + 1) {

                    direccionRebote = "D";

                } else if (posicion[0] + radioBola - posLadrillo[0] + anchoLadrillo ==
                        posicion[1] + radioBola - posLadrillo[1] + altoLadrillo) {

                    direccionRebote = "DI";

                } else {

                    direccionRebote = "AB";
                }
            } else if (270 < direccion && direccion < 360) {

                if (posicion[0] + radioBola - posLadrillo[0] + anchoLadrillo >
                posicion[1] + radioBola - posLadrillo[1] + altoLadrillo &&
                posicion[0] + radioBola - posLadrillo[0] + anchoLadrillo < radioBola + 1) {

                    direccionRebote = "I";

                } else if (posicion[0] + radioBola - posLadrillo[0] + anchoLadrillo ==
                        posicion[1] + radioBola - posLadrillo[1] + altoLadrillo) {

                    direccionRebote = "DI";

                } else {

                    direccionRebote = "AB";

                }
            } else {

                direccionRebote = "DI";
            }
        }


        if  (!Objects.equals(direccionRebote, "\0")) {
            this.colision = true;
            CambiarDireccion();
        }
    }

    /**
        * Cambia la dirección de la bola cuando toca la raqueta
        * @param raqueta del juego
     */
    private void ColisionConRaqueta(Raqueta raqueta) {
        if (posicion[1] + radioBola > 500 && posicion[0] + radioBola > raqueta.getX() &&
                posicion[0] - radioBola < raqueta.getX() + raqueta.getWidth()) {
            Integer ancho = raqueta.getWidth();
            Integer distanciaConOrigen = (int) (posicion[0] - raqueta.getX());
            direccion = 210 + (distanciaConOrigen / (ancho / 120f));
        }
    }

    /**
        * Determina dirección de la bola cuando rebota con los bordes de la pantalla
     */
    private void ColisionConEscenario() {
        if (posicion[0] - radioBola < 0) {
            this.direccionRebote = "D";
        } else if (posicion[0] + radioBola > anchoPantalla) {
            this.direccionRebote = "I";
        }

        if (posicion[1] - radioBola < 0) {
            this.direccionRebote = "AB";
        } else if (posicion[1] + radioBola > altoPantalla) {
            this.direccionRebote = "AR";
        }

        if (!Objects.equals(direccionRebote, "\0")) {

            CambiarDireccion();
        }

    }

    /**
        * Cambia la dirección de la bola
     */
    private void CambiarDireccion() {

        if (direccion == 0f || direccion == 360f || direccion == 180f || direccion == 90f || direccion == 270f || direccionRebote.equals("DI")) {
            direccion -= 180;
            direccionRebote = "\0";
        }

        switch (direccionRebote) {
            case "I", "D" -> direccion = 180 - direccion;
            case "AB", "AR" -> direccion = 360 - direccion;
            case "IAR", "IAB", "DAR", "DAB" -> direccion = 180 + direccion;
        }

        if (direccion > 360) {
            direccion -= 360;
        } else if (direccion < 0) {
            direccion += 360;
        }
        this.direccionRebote = "\0";
    }

    /**
        * Mueve la bola y consulta si la bola colisionó con algo
        * @param raqueta del juego
        * @param ladrillos del juego
     */
    public void Mover(LadList ladrillos, Raqueta raqueta) throws ParseException {
        posicion[0] += velocidad * (float) Math.cos(Math.toRadians(direccion));
        posicion[1] += velocidad * (float) Math.sin(Math.toRadians(direccion));

        bola.setCenterX(posicion[0]);
        bola.setCenterY(posicion[1]);

        //Colision con ladrillos
        for (Integer i = 0; i < ladrillos.Size(); i++) {
            Rebote(ladrillos.Acceder(i).getPosicion());

            if (colision) {
                ladrillos.Acceder(i).Colision();
                this.colision = false;
            }

        }

        ColisionConRaqueta(raqueta);

        ColisionConEscenario();
    }


    /*
        * getters y setters
     */

    public void setDireccion(Float direccion) {
        this.direccion = direccion;
    }

    public Float getDireccion() {
        return direccion;
    }

    public Float[] getPosicion() {
        return posicion;
    }

    public void setPosicion(Float[] newPosicion) {
        posicion = newPosicion;
    }

    public void setVelocidad(Integer velocidad) {
        this.velocidad = velocidad;
    }

    public Integer getVelocidad() {
        return velocidad;
    }

    public Circle getBola() {
        return bola;
    }
}