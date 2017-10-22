package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class PanelPrincipal extends JPanel implements ActionListener{
	
	private ClientInterface principal;
		
	private JLabel dirIp;
	private JTextField dir;
	
	private JLabel puerto;
	private JTextField port;
	
	private JLabel obj;
	private JTextField numObj;
	
	private JButton enviar;
	
	private static final String ENVIAR = "Enviar";
	
	public PanelPrincipal(ClientInterface pr){
		principal = pr;
		
		setLayout(new GridLayout(8,2));
		Border otro = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border borde = BorderFactory.createTitledBorder(otro, "Ajustar parámetros");
		setBorder(borde);
		
		add(new JLabel(""));
		add(new JLabel(""));
		
		dirIp = new JLabel("Dirección IP del servidor");
		add(dirIp);

		dir = new JTextField();
		dir.setEditable(true);
		add(dir);
		
		add(new JLabel(""));
		add(new JLabel(""));
		
		puerto = new JLabel("Puerto del servidor");
		add(puerto);

		port = new JTextField();
		port.setEditable(true);
		add(port);
		
		add(new JLabel(""));
		add(new JLabel(""));
		
		obj = new JLabel("Número de objetos");
		add(obj);

		numObj = new JTextField();
		numObj.setEditable(true);
		add(numObj);
		
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		
		enviar = new JButton(ENVIAR);
		enviar.addActionListener(this);
		enviar.setActionCommand(ENVIAR);
		add(enviar);
	}

	public void vaciarCampos(){
		dir.setText("");
		port.setText("");
		numObj.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando.equals(ENVIAR)){
			
			String direccion = dir.getText();
			String puertoS = port.getText();
			String objetos = numObj.getText();
			
			principal.enviarParametros(direccion, puertoS, objetos);
		}
	}
		
}