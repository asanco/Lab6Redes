package interfaz;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import client.*;

public class ClientInterface extends JFrame {

	private Client cliente; 

	PanelArchivos panelArchivos;
	PanelDescarga panelDescarga;
	PanelConexion panelConexion;
	private String[] listaArchivos;

	public ClientInterface( )
	{
		setLayout( new BorderLayout( ) );
		setPreferredSize(new Dimension(640, 480));
		panelConexion = new PanelConexion( this );
		add( panelConexion, BorderLayout.SOUTH );
		setResizable( true );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		panelDescarga = new PanelDescarga( this );
		panelArchivos = new PanelArchivos( this );
		setTitle( "Transferencia de archivos" );
		JPanel panelTransferencia = new JPanel( new BorderLayout( ) );
		panelTransferencia.add( panelDescarga, BorderLayout.WEST );
		panelTransferencia.add( panelArchivos, BorderLayout.EAST );
		add( panelTransferencia, BorderLayout.NORTH );
		pack( );
		

	}


	public void startConnection()
	{
		JOptionPane.showMessageDialog(null, "Estableciendo conexion");
		cliente = new Client();
		listaArchivos = cliente.receiveFilesList();
		JOptionPane.showMessageDialog(null, "Conexion establecida");
		panelConexion.changeState();
		panelDescarga.actualizarArchivos(listaArchivos);
		pack();
		repaint();
		setVisible(true);
		System.out.println("Archivos estan al dia");
	}
	
	public void actualizarDescargas(ArrayList<String> files)
	{
		panelArchivos.actualizarArchivos(files);
		pack();
		repaint();
		setVisible(true);
	}
	

	public void closeConnection(){
		//TODO client.closeConn();
		panelArchivos.cerrarConexion();
		panelConexion.setDesconnected();
		JOptionPane.showMessageDialog(null, "Conexion terminada");
	}

	public Client getClient(){
		return cliente;
	}

	public void descargar(){
		String name = panelDescarga.getSelectedFile();
		cliente.downloadFile(name);


	}

	public void displayDownloads(){
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File("./downloads"));
		int result = fc.showOpenDialog(this);
		if(result == JFileChooser.APPROVE_OPTION){
			File selectedFile = fc.getSelectedFile();
			System.out.println("Archivo: " + selectedFile.getAbsolutePath());
			try {
				java.awt.Desktop.getDesktop().open(selectedFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void stop(){
		cliente.stopDownload();
		JOptionPane.showMessageDialog(null, "Descarga detenida");
		panelArchivos.cerrarConexion();
	}

	public void downloadFinished(){
		JOptionPane.showMessageDialog(null, "El archivo se descargo exitosamente");
	}

	
	public static void main( String[] args )
	{
		try
		{
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName( ) );
		}
		catch( Exception e )
		{
	
		}
		ClientInterface ci = new ClientInterface( );
		ci.setVisible( true );
	}

}
