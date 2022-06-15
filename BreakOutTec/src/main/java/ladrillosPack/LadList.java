package ladrillosPack;

public class LadList {

    private Nodo inicio;
    private Nodo fin;
    private int size;

    public LadList() {
        this.inicio = null;
        this.fin = null;
        this.size = 0;
    }

    public void Insertar(Ladrillos ladrillo) {
        Nodo nuevo = new Nodo(ladrillo);
        if (this.inicio == null) {
            this.inicio = nuevo;
            this.fin = nuevo;
        } else {
            this.fin.setSiguiente(nuevo);
            this.fin = nuevo;
        }
        this.size++;
    }

    public void Eliminar(Ladrillos ladrillo) {
        Nodo actual = this.inicio;
        Nodo anterior = null;
        while (actual != null) {
            if (actual.getLadrillo().equals(ladrillo)) {
                if (anterior == null) {
                    this.inicio = actual.getSiguiente();
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }
                this.size--;
                break;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
    }

    public int Size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public Ladrillos Acceder(int index) {
        Nodo temp = this.inicio;
        for (int i = 0; i < index; i++) {
            temp = temp.getSiguiente();
        }
        return temp.getLadrillo();
    }

    public Integer getIndice(Ladrillos lad){
        Nodo temp = this.inicio;
        Integer i = 0;
        while(temp != null){
            if(temp.getLadrillo().equals(lad)){
                return i;
            }
            temp = temp.getSiguiente();
            i++;
        }
        return -1;
    }
}
