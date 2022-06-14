package componentes;

public class ListaBolas {

    private NodoBola primero;
    private NodoBola ultimo;
    private Integer cantidad = 0;

    public ListaBolas() {
        primero = null;
        ultimo = null;
    }

    public void Insertar(Bola bola) {
        NodoBola nuevo = new NodoBola(bola);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }
        cantidad++;
    }

    public Bola getBola(Integer posicion) {
        NodoBola actual = primero;
        for (Integer i = 0; i < posicion; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getBola();
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void Eliminar(Integer posicion) {
        NodoBola actual = primero;
        NodoBola anterior = null;
        for (Integer i = 0; i < posicion; i++) {
            anterior = actual;
            actual = actual.getSiguiente();
        }
        if (anterior == null) {
            primero = actual.getSiguiente();
        } else {
            anterior.setSiguiente(actual.getSiguiente());
        }
        cantidad--;
    }
}
