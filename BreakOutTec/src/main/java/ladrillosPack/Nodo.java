package ladrillosPack;

import javafx.scene.shape.Rectangle;

public class Nodo {

    private ladrillos ladrillo;
    private Nodo siguiente;

    public Nodo(ladrillos ladrillo) {
        this.ladrillo = ladrillo;
    }

    public ladrillos getLadrillo() {
        return ladrillo;
    }

    public void setLadrillo(ladrillos ladrillo) {
        this.ladrillo = ladrillo;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }
}
