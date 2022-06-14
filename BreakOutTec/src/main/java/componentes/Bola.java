package componentes;

import java.util.Objects;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Bola {
    private final Integer velocidad = 5;
    private Float direccion;
    private String direccionRebote = "\0";

    private Boolean colision = false;
    private Float[] posicion = {0f,0f};

    private final Integer radioBola = 10;

    private static final Integer anchoCaja = 50;
    private static final Integer altoCaja = 15;

    private static final Integer anchoPantalla = 800;
    private static final Integer altoPantalla = 600;

    private Circle bola;

    public Bola() {

        bola = new Circle(400, 450, radioBola);
        bola.setFill(Color.WHITE);
        posicion[0] = (float) bola.getCenterX();
        posicion[1] = (float) bola.getCenterY();

    }

    private void Rebote(Float[] posCaja) {
        if (posCaja[1] < posicion[1] + radioBola && posicion[1] < posCaja[1] + altoCaja) {
            System.out.println("Dentro de línea horizontal");
            if (posCaja[0] < posicion[0] + radioBola) {
                direccionRebote = "I";
            } else if (posCaja[0] + anchoCaja > posicion[0] - radioBola) {
                direccionRebote = "D";
            }
        }

        if (posCaja[0] < posicion[0] + radioBola && posCaja[1] < posicion[1] + radioBola) {
            System.out.println("Dentro de línea vertical");
            System.out.println(posCaja[0] < posicion[0] + radioBola);
            System.out.println(posCaja[1] < posicion[1] + radioBola);
            if (posCaja[1] < posicion[1] + radioBola) {
                direccionRebote = "AR";
            } else if (posCaja[1] + altoCaja > posicion[1] - radioBola) {
                direccionRebote = "AB";
            }
        }


        if  (!Objects.equals(direccionRebote, "\0")) {
            this.colision = true;
            CambiarDireccion();
            System.out.println("Posicion bola: " + posicion[0] + ", " + posicion[1]);
            System.out.println("Posicion caja: " + posCaja[0] + ", " + posCaja[1]);
        }

    }



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
            System.out.println("Colision con escenario");
            System.out.println("Posicion: " + posicion[0] + ", " + posicion[1]);
            System.out.println("Direccion: " + direccion);
            CambiarDireccion();
        }

    }

    private void CambiarDireccion() {

        if (direccion == 0f || direccion == 360f || direccion == 180f || direccion == 90f || direccion == 270f) {
            direccion -= 180;
            direccionRebote = "\0";
        }

        switch (direccionRebote) {
            case "I", "D":
                direccion = 180 - direccion;

                break;
            case "AB", "AR":
                if (direccion == 90 || direccion == 270) {
                    direccion -= 180;
                } else {
                    direccion = 360 - direccion;
                }

                break;
            case "IAR", "IAB", "DAR", "DAB":
                direccion = 180 + direccion;

                break;
        }

        if (direccion > 360) {
            direccion -= 360;
        } else if (direccion < 0) {
            direccion += 360;
        }
        System.out.println("Direccion de rebote: " + direccionRebote);
        this.direccionRebote = "\0";
    }

    public void Mover(Caja[] cajas) {
        posicion[0] += velocidad * (float) Math.cos(Math.toRadians(direccion));
        posicion[1] += velocidad * (float) Math.sin(Math.toRadians(direccion));

        //Colision con cajas
        for (Caja caja : cajas) {
            System.out.println("Caja #" + caja.getCajaNum());
            Rebote(caja.getPosicion());

            /*
            if (colision) {
                EventoEspecial(caja.getEvento());
                this.colision = false;
            }
            */
        }
        ColisionConEscenario();
    }

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

    public Circle getBola() {
        return bola;
    }
}