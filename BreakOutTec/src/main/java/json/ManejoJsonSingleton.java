package json;

import java.lang.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ManejoJsonSingleton {

    public Integer i;
    public Integer size;
    public Integer bolas = 0;
    public String datos;
    public JSONObject json;
    public JSONParser parser;


    static public ManejoJsonSingleton singleton = null;

    private ManejoJsonSingleton() {
        json = new JSONObject();
        parser = new JSONParser();
    }

    static public ManejoJsonSingleton getInstance() {
        if (singleton == null) {
            singleton = new ManejoJsonSingleton();
        }
        return singleton;
    }

    public void setDatos(String datos) throws ParseException {
        this.datos = datos;
        this.json = (JSONObject) parser.parse(datos);
        String temp = (String) json.get("largo");
        this.size = Integer.parseInt(temp);
        this.i = 0;
    }

    /**
     * Resetea el json con los datos
     * @throws ParseException
     */
    public void ResetJson() throws ParseException {
        this.json = (JSONObject) parser.parse(datos);
    }

    /**
     * Obtiene el poder del ladrillo
     * @return Integer
     */
    public Integer getPower() {

        if(i > size){
            return -1;
        }

        String labrillo = i.toString();
        JSONObject temporal = (JSONObject) json.get(labrillo);
        Long poder = (Long) temporal.get("poder");
        return poder.intValue();
    }

    /**
     * Obtiene el efecto del ladrillo
     * @return Integer
     */
    public Integer getPts() {

        if(i > size){
            return -1;
        }

        String labrillo = i.toString();
        JSONObject temporal = (JSONObject) json.get(labrillo);
        Long pts = (Long) temporal.get("pts");
        return pts.intValue();
    }

    /**
     * Obtiene la pos x del ladrillo
     * @return Integer
     */
    public Integer getXPos(){
        if(i > size){
            return -1;
        }
        String labrillo = i.toString();
        JSONObject temporal = (JSONObject) json.get(labrillo);
        Long xPos = (Long) temporal.get("x");
        return xPos.intValue();
    }

    /**
     * Obtiene la pos y del ladrillo
     * @return Integer
     */
    public Integer getYPos() {
        if (i > size) {
            return -1;
        }
        String labrillo = i.toString();
        JSONObject temporal = (JSONObject) json.get(labrillo);
        Long yPos = (Long) temporal.get("y");
        return yPos.intValue();
    }

    /**
     * Aumenta la posicion del puntero de los ladrillos del json, esto es para los metodos getPower y getPts. Simula el recorrido de los ladrillos paso a paso.
     */
    public void Next() {

        this.i++;

    }

    /**
     * Resetea el puntero de los ladrillos del json, esto es para los metodos getPower y getPts.
     */
    public void Reset() {
        i = 0;
    }

    /**
     * Setea la cantidad de ladrillos en el json
     */
    public void setSize(){
        String largoStr = (String) json.get("largo");
        this.size = Integer.parseInt(largoStr);
    }

    /**
     * Aumenta la cantidad de bolas en el json
     * @param x Integer
     * @param y Integer
     */
    public void AddBolas(Integer x, Integer y){
        String bolasStr = "bola" + bolas.toString();
        JSONObject temp = new JSONObject();
        temp.put("x", x);
        temp.put("y", y);
        json.put(bolasStr, temp);
        this.bolas++;

        SetSizeBola();
    }

    /**
     * Actuliza los valores de las bolas en el json
     * @param x Integer
     * @param y Integer
     * @param pos Integer. Posicion de la bola en el json (0,1,2,3,4...)
     */
    public void UpdateBall(Integer x, Integer y, Integer pos){

        String bolasStr = "bola" + pos.toString();
        JSONObject temp = new JSONObject();
        temp.put("x", x);
        temp.put("y", y);
        json.put(bolasStr, temp);

    }

    /**
     * Obtiene la posicion x de la bola en el json
     * @param bola
     * @return Integer
     */
    public Integer getXbola(Integer bola){
        String bolasStr = "bola" + bola.toString();
        JSONObject temp = (JSONObject) json.get(bolasStr);
        Long x = (Long) temp.get("x");
        return x.intValue();
    }

    /**
     * Obtiene la posicion y de la bola en el json
     * @param bola
     * @return Integer
     */
    public Integer getYbola(Integer bola){
        String bolasStr = "bola" + bola.toString();
        JSONObject temp = (JSONObject) json.get(bolasStr);
        Long y = (Long) temp.get("y");
        return y.intValue();
    }

    /**
     * Obtiene la cantidad de bolas en el json
     */
    public void SetSizeBola(){
        json.put("CantidadBolas", this.bolas);
    }

    /**
     * Obtiene la cantidad de ladrillos en el json
     */
    public void SetLargo(){
        json.put("largo", this.size);
    }

    /**
     * Actualiza/agrega la raqueta al json. Mismo metodo para ambas funcionalidades
     * @param x Integer
     * @param y Integer
     * @param largo Integer
     */
    public void SetRaqueta(Integer x, Integer y, Integer largo){
        JSONObject temp = new JSONObject();
        temp.put("x", x);
        temp.put("y", y);
        temp.put("largo", largo);
        json.put("raqueta", temp);
    }

    /**
     *  Setea las vidas en el json
     * @param vidas Integer
     */
    public void SetVidas(Integer vidas){
        json.put("vidas", vidas);
    }

    /**
     *  Setea las puntos en el json
     * @param puntos Integer
     */
    public void SetPuntos(Integer puntos){
        json.put("puntos", puntos);
    }

    /**
     * Remueve una bola del json, ademas de decrementar la cantidad de bolas en el json y acomodar las bolas del json
     * @param bolaR Integer
     */

    public void RemoveBall(Integer bolaR){

        json.remove("bola" + bolaR.toString());

        for(Integer i = bolaR; i < this.bolas; i++){

            if(i != this.bolas - 1) {
                Integer nuevaPos = i + 1;
                String bolasStr = "bola" + nuevaPos.toString();
                JSONObject temp = (JSONObject) json.get(bolasStr);

                String nuevaBolasStr = "bola" + i.toString();
                json.put(nuevaBolasStr, temp);

                json.remove(bolasStr);
            }
        }
        this.bolas--;
        SetSizeBola();
    }
    /**
     * Remueve un ladrillo del json, ademas de decrementar la cantidad de ladrillos en el json y acomodar las ladrillos del json
     * @param ladrillo Integer
     */

    public void RemoveLadrillo(Integer ladrillo) {

        json.remove(ladrillo.toString());

        for (Integer i = ladrillo; i < this.size; i++) {

            if (i != this.size - 1) {
                Integer nuevaPos = i + 1;
                String ladrillosStr = nuevaPos.toString();

                JSONObject temp = (JSONObject) json.get(ladrillosStr);

                String nuevaLadrillosStr = i.toString();
                json.put(nuevaLadrillosStr, temp);

                json.remove(ladrillosStr);
            }
        }
        this.size--;
        SetLargo();
    }

    /**
     * Convierte el json a un string
     * @return String
     */
    public String GetJsonString(){
        return json.toJSONString();
    }

    /**
     * Agrega la accion al json
     * @param action
     */
    public void AddAction(Integer action){

        this.json.put("accion", action);
    }

    /**
     * Resetea el puntero del json
     */
    public void ResetI(){
        this.i = 0;
    }



}
