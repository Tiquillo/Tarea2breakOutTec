package serverSockets;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import json.ManejoJsonSingleton;

import static java.lang.Thread.sleep;

public class PruebaSockets {

    public static void main(String[] args) throws ParseException {

        SocketClient cliente = new SocketClient("127.0.0.1", 8080); //se conectan al servidor
        SocketClient observador = new SocketClient("127.0.0.1", 8080);

        //Envia instruccion de generar ladrillos
        JSONObject obj = new JSONObject();
        obj.put("accion", 1);
        String json = obj.toJSONString();
        cliente.sentString(json);

        String respuesta = cliente.receiveString(); //Devuelve los ladrillos

        System.out.println("primer respuesta: " + respuesta);

        //Crea el manejador de json y setea el formato de la respuesta
        ManejoJsonSingleton manejoJson = ManejoJsonSingleton.getInstance();
        manejoJson.setDatos(respuesta);
        //Recorre Ladrillos y setea podereos (agregar)
        manejoJson.AddAction(2);// accion para enviar al observador
        manejoJson.SetPuntos(0);//puntos para enviar al observador
        manejoJson.SetVidas(3);
        manejoJson.SetRaqueta(0,0,10);
        manejoJson.AddBolas(0,0); //Con su lista de bolas


        while(true){

            cliente.sentString(manejoJson.GetJsonString()); //Envia al servidor el json
            String respuesta2 = observador.receiveString(); //Recibe la respuesta del servidor

            System.out.println(respuesta2);

            while (true) {

                if(manejoJson.i > manejoJson.size){
                    break;
                } else {
                    System.out.println("----------------------------------------------------");
                    System.out.println(manejoJson.i);
                    System.out.println(manejoJson.getXPos());
                    System.out.println(manejoJson.getYPos());
                    System.out.println(manejoJson.getPower());
                    System.out.println(manejoJson.getPts());
                    System.out.println("----------------------------------------------------");
                    manejoJson.Next();
                }

            }

            manejoJson.ResetI();
      }


    }



}
