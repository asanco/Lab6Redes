package client;
import java.io.*;
import java.net.*;
import java.util.Date;

import clientInterface.VentanaPrincipal;

public class UDPClient {
	
	private static int PORT;
	private static String IP;
	private static int objects;
	private VentanaPrincipal app;
	
	public UDPClient(VentanaPrincipal app1, int PORT1, String IP1, int objects1){
		this.app = app1;
		objects=objects1;
		IP=IP1;
		PORT=PORT1;
		System.out.println(this.PORT + " " + this.IP+ " " + this.objects);
		try {
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void sendObjects(int n, InetAddress IPAddress, DatagramSocket clientSocket) throws IOException{
		for (int i = 0; i < n; i++) {
			Object o = new Object(i, new Date());
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(bs);
			os.writeObject(o);
			os.close();
			byte[] sendData = bs.toByteArray();
			DatagramPacket dp = new DatagramPacket(sendData, sendData.length, IPAddress, PORT);
			clientSocket.send(dp);
		}
	}
	public static void setObjects(int i){
		objects=i;
	}
	public static void init() throws Exception
	{
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName(IP);
		byte[] receiveData = new byte[1024];
		sendObjects(objects, IPAddress, clientSocket);
		
		/*DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);
		//response debe contener: nuÌ�mero de objetos recibidos, numero de objetos faltantes, tiempo promedio de envioÌ� de objetos
		String response = new String(receivePacket.getData());
		System.out.println("FROM SERVER:" + response);
		*/
			
		
		clientSocket.close();
	}
}