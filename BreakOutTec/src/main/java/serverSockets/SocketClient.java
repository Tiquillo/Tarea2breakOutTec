package serverSockets;

import java.net.*;
import java.io.*;
import java.lang.*;

public class SocketClient {

    private Socket socket;

    /**
     * Constructor
     */

    public SocketClient(String ip, Integer port)
    {
        try
        {
            //Se crea el socket cliente
            socket = new Socket(ip, port);
            System.out.println(("Conectado"));

            socket.setSoLinger(true, 10);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Desconecta el socket
     */

    public void disconnect() {
        try{
            socket.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Envia un string al servidor en C.
     * @param message String
     */
    public void sentString(String message)
    {
        try
        {
            DataOutputStream bufferOut = new DataOutputStream(socket.getOutputStream());
            SocketData aux = new SocketData(message);
            aux.writeObject(bufferOut);
            //System.out.println("Cliente Java: Enviado " + aux.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para recibir un string de un programa en C.
     * @return String recibido.
     */

    public String receiveString()
    {
        String receiveString = "";
        try {
            //Se obtiene un flujo de datos para recibir datos del servidor.
            DataInputStream bufferIn = new DataInputStream(socket.getInputStream());;
            SocketData dato = new SocketData("");
            dato.readObject(bufferIn);

            receiveString = dato.toString();
            //System.out.println("Cliente Java: Recibido " + dato.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return receiveString;
    }

}