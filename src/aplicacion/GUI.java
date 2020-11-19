package aplicacion;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.*;
import entidades.*;
import javax.swing.JLabel;

public class GUI extends JFrame {

	private JPanel panelPrincipal;
	private Juego miJuego;
	private static int x = 600;
	private static int y = 500;
	private static int lineaY = 440;
	private JLabel mapaGrafico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 868, 644);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		miJuego = new Juego(x, y, lineaY, 3);
		miJuego.empezar();
		
		JPanel panelJuego = new JPanel();
		panelJuego.setBounds(10, 11, x, y);
		panelPrincipal.add(panelJuego);
		panelJuego.setLayout(null);
		
		mapaGrafico = new JLabel("");
		mapaGrafico.setBounds(0, 0, x, y);
		panelJuego.add(mapaGrafico);
		rePaint();
		
		}
	
	private void rePaint() {
	
		// Actualiza el mapa
		Image mapa = new ImageIcon(GUI.class.getResource("/img/"+miJuego.getMapa().getNivelActual().getGrafico())).getImage();
		ImageIcon mapa2 =new ImageIcon(mapa.getScaledInstance(x, y, Image.SCALE_SMOOTH));
		mapaGrafico.setIcon(mapa2);
	}
		
		
}
