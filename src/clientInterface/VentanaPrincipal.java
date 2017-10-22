package clientInterface;

import java.awt.BorderLayout;
import client.*;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;



public class VentanaPrincipal extends JFrame {
	
	private UDPClient client;
	private PanelConexion panelConexion;
	private PanelDescarga panelDescarga;
	
	 public VentanaPrincipal( )
	    {
	        setLayout( new GridLayout(2,1 ) );

	        panelConexion = new PanelConexion( this );
	        add( panelConexion);
	        panelDescarga = new PanelDescarga( this );
	        add(panelDescarga);
	       

	      

	        pack( );
	        setTitle( "Interfaz Cliente" );
	        setResizable( true );
	        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

	    }
	 
	 public UDPClient darCliente(){
		 return client;
	 }
	 
	 public void inciarEnvio(){
		 int puerto = panelConexion.darPuerto();
		 String IP = panelConexion.darIp();
		 int objetos = panelDescarga.darObjects();
		 client = new UDPClient(this,puerto,IP,objetos);
		 
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
        VentanaPrincipal i = new VentanaPrincipal( );
        i.setVisible( true );
    }

}
