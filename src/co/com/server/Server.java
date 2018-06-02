package co.com.server;

import co.com.controlador.Controlador;
import co.com.entidad.Parcial;
import java.io.IOException;
import static java.lang.Thread.yield;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private static final int PUERTO = 12345;
    static String numero = "1985";
    static String resp = "";
    static Random random = new Random();
    static int aleatorio;
    static int nMenor;
    static int promedio;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatagramSocket conexion;
        DatagramPacket entrada;
        DatagramPacket salida;
        Controlador controlador = new Controlador();
        byte[] datoEntrada;
        byte[] datoSalida;
        String mensaje;

        System.out.println("Inicializando servidor. . .");

        try {
            conexion = new DatagramSocket(PUERTO);
            while (true) {
                datoEntrada = new byte[1024];
                entrada = new DatagramPacket(datoEntrada, datoEntrada.length);
                conexion.receive(entrada);
                mensaje = new String(entrada.getData(), 0, entrada.getLength());
                
                System.out.println("mensaje: " + mensaje);
                
                List<Parcial> lParcial = controlador.recuperarPorIdEstudiante(mensaje);
                
                String resultado = "";
                Iterator<Parcial> it = lParcial.iterator();
                while(it.hasNext()){
                    Parcial parcial = it.next();
                    resultado += "Lugar: " + parcial.getBloque().getCodigo() + " - " + parcial.getSalon().getCodigo() + " Hora: " + parcial.getFecha() + " \n";
                }
                
                datoSalida = (resultado).getBytes();
                /*Parcial parcial = controlador.recuperarPorIdTarea(mensaje);

                datoSalida = ("Lugar: " + parcial.getBloque().getCodigo() + " - " + parcial.getSalon().getCodigo() + " Hora: " + parcial.getFecha()).getBytes();*/

                salida = new DatagramPacket(datoSalida, datoSalida.length, entrada.getAddress(), entrada.getPort());
                conexion.send(salida);
            }
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
