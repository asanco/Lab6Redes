package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanelConexion extends JPanel implements ActionListener {
	
	private ClientInterface clientInterface;
	private JLabel pedirConexion;
	private JButton btnConectar;
	public static String CONECTAR="Conectar";
	private JLabel estado;
	private JTextField estadoConexion;
	
	public PanelConexion(ClientInterface ci){
		
		this.clientInterface = ci;
	    setLayout(new GridLayout(2,2));

        setBorder( BorderFactory.createTitledBorder( "Conexion con servidor" ) );

        pedirConexion = new JLabel( "Pedir conexion" );
        add( pedirConexion );
        
        btnConectar = new JButton( );
        btnConectar.setText( "Conectar" );
        btnConectar.setActionCommand( CONECTAR );
        btnConectar.addActionListener( this );
        add( btnConectar );
		
        estado = new JLabel("Estado de conexion: ");
        add(estado);
        
        estadoConexion = new JTextField();
        estadoConexion.setEditable(false);
        estadoConexion.setText("Sin conexion");
        add(estadoConexion);
	}
	
	public void changeState(){
		estadoConexion.setText("Conectado");
	}
	public void setDesconnected(){
		estadoConexion.setText("Sin conexion");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if(CONECTAR.equals(command)){
			System.out.println("Conectar");
			clientInterface.startConnection();
		}
		
	}

}
