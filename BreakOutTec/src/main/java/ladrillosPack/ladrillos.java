package ladrillosPack;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ladrillos {

    private Integer x;
    private Integer y;

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

}
