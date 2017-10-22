package interfaz;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


public class PanelDescarga extends JPanel implements ActionListener {
	
	
	 private JList filesList;
	    private JButton btnDescarga;
	    private JButton btnDetener;
	    private ClientInterface ci;
	    public static String DETENER="Detener";
	    public static String DESCARGAR="Descargar";
	    

	 
	    public PanelDescarga( ClientInterface ci )
	    {
	        this.ci = ci;

	        setBorder( BorderFactory.createTitledBorder( "Archivos" ) );
	        setLayout( new BorderLayout( ) );
	        
	        filesList = new JList( );
	        JScrollPane scroll = new JScrollPane( );
	        scroll.setViewportView( filesList );
	        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
	        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
	        add( scroll, BorderLayout.CENTER );

	        btnDescarga = new JButton( );
	        btnDescarga.setText( "Descargar" );
	        btnDescarga.setActionCommand( DESCARGAR );
	        btnDescarga.addActionListener( this );

	        btnDetener = new JButton( );
	        btnDetener.setText( "Detener" );
	        btnDetener.setActionCommand( DETENER );
	        btnDetener.addActionListener( this );
	        
	        JPanel panel = new JPanel( );
	        setPreferredSize(new Dimension(300, 350));
	        panel.add( btnDescarga );
	        panel.add( btnDetener );
	        add( panel, BorderLayout.SOUTH );
	    }

	  
	    public void actualizarArchivos( String[] files )
	    {
	    	DefaultListModel listModel = new DefaultListModel();
	        for (String st : files)
	            listModel.addElement(st);
	        filesList.setModel(listModel);
	        filesList.setSelectedIndex(0);
	        repaint();
	    }

	   
	    public int getIndex( )
		{
			int c = filesList.getSelectedIndex( );
			if( c < 0 )
				return -1;
			return c;
		}
		public String getSelectedFile( )
		{
			int c = filesList.getSelectedIndex( );
			if( c < 0 )
				return "";
			String archivo = ( String )filesList.getSelectedValue( );
			return archivo;
		}

	    public void actionPerformed( ActionEvent event )
	    {
	        String command = event.getActionCommand( );
	        String fileName = getSelectedFile( );
	        if( command.equals( DESCARGAR ) )
	        {
	            if( fileName == null )
	            {
	                JOptionPane.showMessageDialog( this, "Escojer archivo", "Abrir archivo", JOptionPane.ERROR_MESSAGE );
	                return;
	            }
	            System.out.println(fileName);
	            ci.descargar();
	        }
	        else if( command.equals( DETENER ) )
	        {
	            ci.stop();
	        }
	    }
}
