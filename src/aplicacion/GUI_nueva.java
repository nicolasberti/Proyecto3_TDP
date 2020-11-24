package aplicacion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.Infectado;
import entidades.Jugador;
import entidades.Proyectil;
import logica.HiloTeclado;
import logica.Juego;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class GUI_nueva extends JFrame {

	private JPanel panelPrincipal;
	private static int x = 600;
	private static int y = 500;
	private static int lineaY = 470;
	private Juego miJuego;
	
	// Panel con imagen de fondo.
	private PanelJuego panelJuego = new PanelJuego();
	
	public class PanelJuego extends JPanel{
		public PanelJuego(){
			super();
		}
		
		public void paint(Graphics g) {
					setOpaque(false);
					g.drawImage(fondo, 0, 0, x, y, this);
					super.paint(g);
		}
	}
	
	private JLabel linea;
	private Jugador jugador;
	private JButton botonEmpezar;
	private Image fondo; // Mapa.
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_nueva frame = new GUI_nueva();
					frame.setVisible(true);
					frame.setResizable(false); // No deja agrandar ni achicar el frame.
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_nueva() {
		
		this.setFocusable(true);
		
		miJuego = new Juego(x, y, lineaY, 1, this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 646);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		panelJuego.setBounds(10, 11, x, y);
		panelPrincipal.add(panelJuego);
		panelJuego.setLayout(null);
		
		JPanel panelScore = new JPanel();
		panelScore.setBounds(10, 522, 600, 74);
		panelPrincipal.add(panelScore);
		panelScore.setLayout(null);
		
		linea = new JLabel("");
		linea.setBounds(0, lineaY, 600, 7);
		panelJuego.add(linea);
		
		botonEmpezar = new JButton("Empezar");
		botonEmpezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					empezarJuego();
					botonEmpezar.setVisible(false);
			}
		});
		botonEmpezar.setBounds(501, 40, 89, 23);
		panelScore.add(botonEmpezar);
	}
	
	

	public void empezarJuego() {
		miJuego.empezar();
		// Linea
		Image lineaImg = new ImageIcon(GUI_nueva.class.getResource("/img/linea.png")).getImage();
		ImageIcon lineaImg2 =new ImageIcon(lineaImg.getScaledInstance(x, y, Image.SCALE_SMOOTH));
		linea.setIcon(lineaImg2);
		
		// Mapa
		fondo = new ImageIcon(GUI_nueva.class.getResource("/img/"+miJuego.getMapa().getNivelActual().getGrafico())).getImage();
		jugador = miJuego.getJugador();
		agregarJugador();
		agregarInfectados(miJuego.getHilo().getInfectados());
		this.addKeyListener(new HiloTeclado(this)); // Movimientos del jugador.
	}
	
	private void agregarJugador() {
		jugador = miJuego.getJugador();
		panelJuego.add(jugador);
	}
	
	public void pasarNivel() {
		miJuego.pasarNivel();
		fondo = new ImageIcon(GUI_nueva.class.getResource("/img/"+miJuego.getMapa().getNivelActual().getGrafico())).getImage();
		agregarInfectados(miJuego.getHilo().getInfectados());
		repaint();
	}
	
	public void agregarInfectados(List<Infectado> infectados) {
		for(Infectado infectado : infectados) {
			if(infectado.getJugando())
				panelJuego.add(infectado);
		}
	}
	
	public void disparo() {
		Proyectil proyectil = new Proyectil(jugador.getX(), jugador.getY(), jugador.getArma().getVelocidad(), jugador.getArma().getCargaDesinfeccion());
		miJuego.getHilo().getProyectiles().add(proyectil);
		panelJuego.add(proyectil);
		repaint();
	}
	
	public JPanel getPanel() { return panelJuego; }
	
	public Juego getJuego() { return miJuego; }
}
