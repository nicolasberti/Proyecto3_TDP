package aplicacion;

import java.awt.EventQueue;
import java.awt.Image;
import java.util.List;

import javax.swing.Icon;
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
	
	// Tamaño del mapa y linea horizontal donde se restablecen los infectados y los premios.
	private static int x = 600;
	private static int y = 500;
	private static int lineaY = 470;
	private JLabel mapaGrafico;
	private JLabel linea;
	private Jugador jugador;
	private JLabel lblNewLabel;
	private List<Infectado> infectados;

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
		
		jugador = miJuego.getJugador();
		ImageIcon jugadorImg = new ImageIcon(GUI.class.getResource("/img/jugador.gif"));
		Icon jugadorIcon = new ImageIcon(jugadorImg.getImage().getScaledInstance(jugador.getWidth(), jugador.getHeight(), Image.SCALE_DEFAULT));
		jugador.setIcon(jugadorIcon);
		panelJuego.add(jugador);

		
		mapaGrafico = new JLabel("");
		mapaGrafico.setBounds(0, 0, x, y);
		
		linea = new JLabel(" ");
		linea.setBounds(0, lineaY, 600, 7);
		panelJuego.add(linea);
		
		rePaint();
		
		// Siempre a lo último para que lo puedan sobreponer.
		panelJuego.add(mapaGrafico);
		}
	
	private void rePaint() {
	
		Image lineaImg = new ImageIcon(GUI.class.getResource("/img/linea.png")).getImage();
		ImageIcon lineaImg2 =new ImageIcon(lineaImg.getScaledInstance(x, y, Image.SCALE_SMOOTH));
		linea.setIcon(lineaImg2);
		
		// Actualiza el mapa
		Image mapa = new ImageIcon(GUI.class.getResource("/img/"+miJuego.getMapa().getNivelActual().getGrafico())).getImage();
		ImageIcon mapa2 =new ImageIcon(mapa.getScaledInstance(x, y, Image.SCALE_SMOOTH));
		mapaGrafico.setIcon(mapa2);
		
		
		
		
	}
}
