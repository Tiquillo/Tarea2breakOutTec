package componentes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Raqueta {
    
    private Rectangle raqueta;
    
    private static Raqueta instance = null;

    private Integer speed = 10;
    
    private Raqueta(){
        raqueta = new Rectangle(340, 500, 120, 20);
        raqueta.setFill(Color.BLUE);
    }
    
    public static Raqueta getInstance() {
        if (instance == null) {
            instance = new Raqueta();
        }
        return instance;
    }

    public Rectangle getRaqueta() {
        return raqueta;
    }

    public void moverIzquierda() {
        raqueta.setX(raqueta.getX() - speed);
    }

    public void moverDerecha() {
        raqueta.setX(raqueta.getX() + speed);
    }

    public Integer getX() {
        return (int) raqueta.getX();
    }

    public Integer getY() {
        return (int) raqueta.getY();
    }

    public void setWidth(Integer width) {
        raqueta.setWidth(width);
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public float[] getPosicion(){
        float[] posicion = new float[2];
        posicion[0] = (float) raqueta.getX();
        posicion[1] = (float) raqueta.getY();
        return posicion;
    }
    
}
