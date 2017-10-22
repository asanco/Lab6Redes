package interfaz;


import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import client.Client;

public class ClientInterface extends JFrame {
	
	private PanelPrincipal panelPrincipal;
	private Client udpCliente;
	
	public ClientInterface(){
		setTitle( "UDP" );
		setLayout( new BorderLayout( ) );
		setSize( 460, 330 );
		setResizable( false );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocationRelativeTo( null );
				
		panelPrincipal = new PanelPrincipal(this);
		add(panelPrincipal, BorderLayout.NORTH);
	}
	
	public void enviarParametros(String dir, String puerto, String obj){		
		JFrame frame = new JFrame("JOptionPane showMessageDialog example");
		JOptionPane.showMessageDialog(frame,
				"Información enviada",
			    "Información",
			    JOptionPane.INFORMATION_MESSAGE);
		 
		panelPrincipal.vaciarCampos();
		int cant = Integer.parseInt(obj);
		try {
			 udpCliente = new Client(dir, puerto, cant);
		} catch (Exception e) {
			e.printStackTrace();
			JFrame frame1 = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame1,
					"Error al leer los datos",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void main(String[] args) {		
		try{
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
		}
		catch( Exception e ){
			e.printStackTrace( );
		}
		
		ClientInterface interfaz = new ClientInterface( );
		interfaz.setVisible( true );
	}

}