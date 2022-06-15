package ladrillosPack;

public class Nodo {

    private Ladrillos ladrillo;
    private Nodo siguiente;

    public Nodo(Ladrillos ladrillo) {
        this.ladrillo = ladrillo;
    }

    public Ladrillos getLadrillo() {
        return ladrillo;
    }

    public void setLadrillo(Ladrillos ladrillo) {
        this.ladrillo = ladrillo;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }
}
