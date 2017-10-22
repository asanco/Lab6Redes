package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;

public class Server {
	
	public static void main(String[] args) throws Exception {
		System.out.println("Server Started");
		startServer();
	}
	
	public static void startServer() throws Exception{
		
		DatagramSocket socketServidor = new DatagramSocket(9999);
		
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		
		startCommunication(socketServidor, receiveData, sendData);
	}
	
	public static void startCommunication(DatagramSocket serverSocket, byte[] recieveData, byte[] sendData) throws Exception{
		
		while(true){
			
			DatagramPacket receivePackage = new DatagramPacket(recieveData, recieveData.length);
			
			serverSocket.receive(receivePackage);
			String frase = new String(receivePackage.getData());
			//System.out.println("Frase: "+frase);
			InetAddress DireccionIP = receivePackage.getAddress();
			int puerto = receivePackage.getPort();
			String[] fraseN = frase.split(";");
			String fraseNumero = fraseN[0];
			
			Timestamp marcaTiempoPaquete = Timestamp.valueOf(fraseN[3]);
			//System.out.println("Marca tiempo paquete: "+marcaTiempoPaquete);
			long miliSegundos = marcaTiempoPaquete.getTime();
			
			Timestamp marcaTiempoActual = new Timestamp(System.currentTimeMillis());
			//System.out.println("Marca tiempo actual: "+marcaTiempoActual);
			long mseg = marcaTiempoActual.getTime();
			
			String paraEnviar = fraseNumero+" : "+ (mseg - miliSegundos) +"ms";
			sendData = paraEnviar.getBytes();
			String arch = "DireccionIP: " + DireccionIP + ", Archivo: " + fraseN[2] + ", Paquete numero "+ fraseN[0] 
					+ " de un total de " + fraseN[1] + ", Tiempo: " + (mseg - miliSegundos) + " ms";
						
			DatagramPacket enviarPaquete = new DatagramPacket(sendData, sendData.length,DireccionIP,puerto);
			serverSocket.send(enviarPaquete);
			
			
			String dirIP = ""+DireccionIP;
			String[] nDirIP = dirIP.split("/");
			String ruta = "./data/IP_"+nDirIP[1]+";Archivo_"+fraseN[2]+".txt";
									
			PrintWriter pw = new PrintWriter(new FileWriter(ruta, true));
			pw.println(arch);
			pw.close();
			
			String ruta2 = "./data/IP_"+nDirIP[1]+";Archivo_"+fraseN[2]+";Estadisticas.txt";
			File file = new File(ruta2); 
			if(file.exists()){
				BufferedReader br = new BufferedReader(new FileReader(ruta2));
				String nParq = br.readLine();
				String nPaqFalt = br.readLine();
				String nTotal = br.readLine();
				String tProm = br.readLine();
				
				String[] nParq2 = nParq.split(":");
				String[] nPaqFalt2 = nPaqFalt.split(":");
				String[] nTotal2 = nTotal.split(":");
				String[] tProm2 = tProm.split(":");
				
				br.close();
				
				int nPaquetes = Integer.parseInt(nParq2[1]);
				int falt = Integer.parseInt(nPaqFalt2[1]); 
				double promAnterior = Double.parseDouble(tProm2[1]);
				Long nuevoT = mseg - miliSegundos;
				double nuevoTiempo = (double) (nuevoT*1);
				
				PrintWriter pw2 = new PrintWriter(new FileWriter(ruta2));
				pw2.println("Numero parquetes recibidos:"+(nPaquetes+1));
				pw2.println("Numero parquetes faltantes:"+(falt-1));
				pw2.println("Total Paquetes:" + nTotal2[1]);
				pw2.println("Tiempo Promedio (ms):" + (((nPaquetes*promAnterior)+nuevoTiempo)/(nPaquetes+1)) );
				pw2.close();
			}
			else{
				PrintWriter pw2 = new PrintWriter(new FileWriter(ruta2));
				int total = Integer.parseInt(fraseN[1]);
				pw2.println("Numero parquetes recibidos:1");
				pw2.println("Numero parquetes faltantes:"+(total-1));
				pw2.println("Total Paquetes:" + fraseN[1]);
				pw2.println("Tiempo Promedio (ms):" + (mseg - miliSegundos));
				pw2.close();
			}
			
		}		
		
	}	
	
}