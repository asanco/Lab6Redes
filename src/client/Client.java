package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Client {

	private ArrayList<Object> packages;	
	
	public Client(String dir, String puerto, int cant) throws Exception{
		
		packages = new ArrayList<Object>();
		Timestamp marcaTiempoActual = new Timestamp(System.currentTimeMillis());
		long tiempoArchivo = marcaTiempoActual.getTime();
		
		for (int i = 1; i <= cant; i++) {
			Object actual = new Object(i, cant, tiempoArchivo, new Timestamp(System.currentTimeMillis()));
			packages.add(actual);
			System.out.println("En el constructor: "+ i +" "+actual.getFechaCreacion());
		}
		
		DatagramSocket socketCliente = new DatagramSocket();		
		InetAddress DireccionIP = InetAddress.getByName(dir);		
		byte[] enviarDatos = new byte[1024];
		byte[] recibirDatos = new byte[1024];
		
		for (int i = 0; i < this.darPaquetes().size(); i++) {
			
			Object actual = this.darPaquetes().get(i);
			int num = actual.getNumSecuencia();
			int tot = actual.getTotal();
			long tiempoArc = actual.getTiempoArchivo();
			Timestamp tim = actual.getFechaCreacion();
			
			String frase = num + ";"+ tot + ";" + tiempoArc + ";"+tim;			
			enviarDatos = frase.getBytes();			
			int port = Integer.parseInt(puerto);		
			DatagramPacket enviarPaquete = new DatagramPacket(enviarDatos, enviarDatos.length, DireccionIP, port);			
			socketCliente.send(enviarPaquete);
			
			DatagramPacket recibirPaquete = new DatagramPacket(recibirDatos, recibirDatos.length);	
			socketCliente.receive(recibirPaquete);			
			String fraseModificada = new String(recibirPaquete.getData());			
			System.out.println("DEL SERVIDOR: " + fraseModificada);
		}	
		socketCliente.close();
	}
	
	private ArrayList<Object> darPaquetes(){
		return packages;
	}

}