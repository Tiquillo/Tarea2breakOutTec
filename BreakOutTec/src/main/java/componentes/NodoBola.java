package componentes;

public class NodoBola {

    private Bola bola;
    private NodoBola siguiente;

    public NodoBola(Bola bola) {
        this.bola = bola;
        this.siguiente = null;
    }

    public Bola getBola() {
        return bola;
    }

    public void setBola(Bola bola) {
        this.bola = bola;
    }

    public NodoBola getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoBola siguiente) {
        this.siguiente = siguiente;
    }

}
