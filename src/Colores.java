import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.Stack;

/**
 * Write a description of class Colores here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Colores
{
    // variables estaticas
    private static final int ESPACIO = 10;
    private static final int COLOR_INICIAL = 100;//Valor inicial de los colores
    private static final int ALTO = 5;//Alto del display
    private static final int ANCHO = 15;//Ancho del display
    private static final int MIN = 0;//Valor minimo de los colores
    private static final int MAX = 255;//Valor maximo de los colores
    private static final int TICKS = 25;//Espacio entre ticks
    
    // variables de instancia
    private JFrame paleta;
    private JTextArea display;
    
    private JTextArea rojo;
    private JLabel verde;
    private JLabel azul;
    
    private JLabel valorRojo;
    private JLabel valorVerde;
    private JLabel valorAzul;
    
    private JSlider deslizadorRojo;
    private JSlider deslizadorVerde;
    private JSlider deslizadorAzul;

    /**
     * Constructor for objects of class Colores
     */
    public Colores()
    {
        construirVentana();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        paleta.setLocation(d.width/2 - paleta.getWidth()/2, d.height/2 - paleta.getHeight()/2);
        paleta.setVisible(true);
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    private void construirVentana()
    {
        paleta = new JFrame("Colores");
        
        JPanel contenidoVentana = (JPanel)paleta.getContentPane();
        contenidoVentana.setLayout (new BorderLayout(ESPACIO, ESPACIO));
        contenidoVentana.setBorder (new EmptyBorder(ESPACIO, ESPACIO, ESPACIO, ESPACIO));
        
        //Panel arriba
        display = new JTextArea(ALTO,ANCHO);
        display.setBackground(new Color(COLOR_INICIAL,COLOR_INICIAL,COLOR_INICIAL));
        display.setOpaque(true);
        display.setEditable(false);
        contenidoVentana.add (display, BorderLayout.NORTH);
        
        //panel izquierdo
        JPanel panelColores = new JPanel (new GridLayout(3, 2, ESPACIO, ESPACIO));
        
        valorRojo = new JLabel("" + COLOR_INICIAL, SwingConstants.RIGHT);
        panelColores.add(valorRojo);
        rojo = new JTextArea(1,2);
        rojo.setEditable(false);
        rojo.setBackground(new Color(COLOR_INICIAL,0,0));
        rojo.setOpaque(true);
        panelColores.add(rojo);
        
        valorVerde = new JLabel("" + COLOR_INICIAL, SwingConstants.RIGHT);
        panelColores.add(valorVerde);
        verde = new JLabel("");
        verde.setBackground(new Color(0,COLOR_INICIAL,0));
        verde.setOpaque(true);
        panelColores.add(verde);
        
        valorAzul = new JLabel("" + COLOR_INICIAL, SwingConstants.RIGHT);
        panelColores.add(valorAzul);
        azul = new JLabel("");
        azul.setBackground(new Color(0,0,COLOR_INICIAL));
        azul.setOpaque(true);
        panelColores.add(azul);
        
        contenidoVentana.add (panelColores, BorderLayout.WEST);
        
        //Panel central
        JPanel panelBotones = new JPanel (new GridLayout(3, 1, ESPACIO, ESPACIO));
        
        //Deslizador Rojo
        deslizadorRojo = new JSlider (0,MIN,MAX,COLOR_INICIAL);
        deslizadorRojo.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent evt)
            {
                valorRojo.setText ("" + deslizadorRojo.getValue());
                rojo.setBackground (new Color (deslizadorRojo.getValue(),0,0));
                combinarColores();
            }
        });
        deslizadorRojo.setMajorTickSpacing(TICKS);
        deslizadorRojo.setPaintTicks(true);
        panelBotones.add(deslizadorRojo);
        
        //Deslizador verde
        deslizadorVerde = new JSlider (0,MIN,MAX,COLOR_INICIAL);
        deslizadorVerde.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent evt)
            {
                valorVerde.setText ("" + deslizadorVerde.getValue());
                verde.setBackground (new Color (0,deslizadorVerde.getValue(),0));
                combinarColores();
            }
        });
        deslizadorVerde.setMajorTickSpacing(TICKS);
        deslizadorVerde.setPaintTicks(true);
        panelBotones.add(deslizadorVerde);
        
        //Deslizador azul
        deslizadorAzul = new JSlider (0,MIN,MAX,COLOR_INICIAL);
        deslizadorAzul.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent evt)
            {
                valorAzul.setText ("" + deslizadorAzul.getValue());
                azul.setBackground (new Color (0,0,deslizadorAzul.getValue()));
                combinarColores();
            }
        });
        deslizadorAzul.setMajorTickSpacing(TICKS);
        deslizadorAzul.setPaintTicks(true);
        panelBotones.add(deslizadorAzul);
        
        contenidoVentana.add (panelBotones, BorderLayout.CENTER);
        
        paleta.pack();
    }
    
    private void combinarColores()
    {
        display.setBackground (new Color (deslizadorRojo.getValue(),deslizadorVerde.getValue(),deslizadorAzul.getValue()));
    }
}
