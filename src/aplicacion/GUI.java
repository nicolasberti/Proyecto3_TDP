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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

	private JPanel panelPrincipal;
	private Juego miJuego;
	
	// Tamaño del mapa y linea horizontal donde se restablecen los infectados y los premios.
	private static int x = 600;
	private static int y = 500;
	private static int lineaY = 470;
	
	private JPanel panelJuego;
	private JLabel mapaGrafico;
	private JLabel linea;
	private Jugador jugador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	public GUI() {
		setTitle("Vertical shooter");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 644);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		miJuego = new Juego(x, y, lineaY, 3, this);
		
		panelJuego = new JPanel();
		panelJuego.setBounds(10, 11, x, y);
		panelJuego.setLayout(null);
		panelPrincipal.add(panelJuego);
		
		mapaGrafico = new JLabel("");
		mapaGrafico.setBounds(0, 0, x, y);
		panelJuego.add(mapaGrafico);
		
		linea = new JLabel("");
		linea.setBounds(0, lineaY, 600, 7);
		panelJuego.add(linea);
		
		JPanel panelScore = new JPanel();
		panelScore.setBounds(10, 522, 600, 72);
		panelPrincipal.add(panelScore);
		panelScore.setLayout(null);
		
		JButton botonEmpezar = new JButton("Empezar");
		botonEmpezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				empezar();
				botonEmpezar.setVisible(false);
			}
		});
		botonEmpezar.setBounds(501, 38, 89, 23);
		panelScore.add(botonEmpezar);
		
		}
	
	private void empezar() {
		miJuego.empezar();
		agregarInfectados();
		agregarJugador();
		agregarMapa();
		panelJuego.repaint();
	}
	
	private void agregarJugador() {
		jugador = miJuego.getJugador();
		panelJuego.add(jugador);
		Particula particula = new Particula(10);
		panelJuego.add(particula);
		miJuego.getHilo().getParticulas().add(particula);
	}
	
	private void agregarInfectados() {
		List<Infectado> infectados = miJuego.getInfectadosActuales();
		for(Infectado infectado : infectados) {
			if(infectado.getJugando())
				panelJuego.add(infectado);
		}
	}
	
	private void agregarMapa() {
		Image lineaImg = new ImageIcon(GUI.class.getResource("/img/linea.png")).getImage();
		ImageIcon lineaImg2 =new ImageIcon(lineaImg.getScaledInstance(x, y, Image.SCALE_SMOOTH));
		linea.setIcon(lineaImg2);
		// Actualiza el mapa
		Image mapa = new ImageIcon(GUI.class.getResource("/img/"+miJuego.getMapa().getNivelActual().getGrafico())).getImage();
		ImageIcon mapa2 =new ImageIcon(mapa.getScaledInstance(x, y, Image.SCALE_SMOOTH));
		mapaGrafico.setIcon(mapa2);
		panelJuego.add(mapaGrafico);
	}
	
	public void repintar() {
		repintarInfectados();
		this.repaint();
	}
	
	private void repintarInfectados() {
		panelJuego.repaint();
	}
}
