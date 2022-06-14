package componentes;

/*
esta clase es temporal, solo para ejemplificar
el funcionamiento de las colisiones
*/

public class Caja {
    private final Float[] posicion;
    private final Integer cajaNum;

    public Caja(Float posicionX, Float posicionY, Integer cajaNum) {
        this.posicion = new Float[]{posicionX, posicionY};
        this.cajaNum = cajaNum;
    }

    public Float[] getPosicion() {
        return posicion;
    }

    public Integer getCajaNum() {
        return cajaNum;
    }
}