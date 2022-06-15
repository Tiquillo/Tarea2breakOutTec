package componentes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Raqueta {
    
    private final Rectangle raqueta;
    
    private static Raqueta instance = null;

    private Integer speed = 10;

    /**
     * Constructor de la clase Raqueta
     */
    private Raqueta(){
        raqueta = new Rectangle(340, 500, 120, 20);
        raqueta.setFill(Color.BLUE);
    }
    /**
     * Retorna la instancia de la clase Raqueta
     * @return Instancia de la clase Raqueta
     */
    public static Raqueta getInstance() {
        if (instance == null) {
            instance = new Raqueta();
        }
        return instance;
    }

    /**
     * Mueve la raqueta a la izquierda
     */
    public void MoverIzquierda() {
        raqueta.setX(raqueta.getX() - speed);
    }

    /**
     * Mueve la raqueta a la derecha
     */
    public void MoverDerecha() {
        raqueta.setX(raqueta.getX() + speed);
    }

    /*
     * Getters y Setters
     */
    public Rectangle getRaqueta() {
        return raqueta;
    }

    public Integer getX() {
        return (int) raqueta.getX();
    }

    public Integer getY() {
        return (int) raqueta.getY();
    }

    public Integer getWidth() {
        return (int) raqueta.getWidth();
    }

    public void setWidth(Integer width) {
        raqueta.setWidth(width);
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Float[] getPosicion(){

        return new Float[]{(float) raqueta.getX(), (float) raqueta.getY()};
    }
    
}
