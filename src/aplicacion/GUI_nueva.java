package aplicacion;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.Infectado;
import entidades.Jugador;
import entidades.Proyectil;
import logica.AutoAlgoritmo;
import logica.AutoRemove;
import logica.DescongelarTodos;
import logica.Disparo;
import logica.HiloTeclado;
import logica.Juego;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class GUI_nueva extends JFrame {

	private JPanel panelPrincipal;
	private static int x = 600;
	private static int y = 500;
	private static int lineaY = 470;
	private Juego miJuego;
	private JLabel corazon;
	private JPanel panelScore;
	private JLabel linea;
	private Jugador jugador;
	private JButton botonEmpezar;
	private Image fondo; // Mapa.
	// Panel con imagen de fondo.
	private PanelJuego panelJuego = new PanelJuego();
	private class PanelJuego extends JPanel{
		public PanelJuego(){
			super();
		}
		
		public void paint(Graphics g) {
					setOpaque(false);
					g.drawImage(fondo, 0, 0, x, y, this);
					super.paint(g);
		}
	}
	private JPanel panelVida;
	
	
	
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
		setBackground(Color.WHITE);
		this.setFocusable(true);
		miJuego = Juego.get();
		miJuego.crear(x, y, lineaY, 3, this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 646);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.WHITE);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		panelJuego.setBounds(10, 11, x, y);
		panelPrincipal.add(panelJuego);
		panelJuego.setLayout(null);
		
		panelScore = new JPanel();
		panelScore.setBackground(Color.WHITE);
		panelScore.setBounds(10, 522, 600, 74);
		panelPrincipal.add(panelScore);
		panelScore.setLayout(null);
		
		linea = new JLabel("");
		linea.setBounds(0, lineaY, 600, 7);
		panelJuego.add(linea);
		
		botonEmpezar = new JButton("Empezar");
		botonEmpezar.setBackground(SystemColor.inactiveCaption);
		botonEmpezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					empezarJuego();
					botonEmpezar.setVisible(false);
			}
		});
		botonEmpezar.setBounds(501, 40, 89, 23);
		panelScore.add(botonEmpezar);
		
		panelVida = new JPanel();
		panelVida.setBounds(10, 11, 154, 52);
		panelScore.add(panelVida);
		panelVida.setLayout(new BoxLayout(panelVida, BoxLayout.X_AXIS));
		
		
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
		
		this.cartel();
		
		
		
	}
	
	public void perder() {
		JLabel cartel = new JLabel();
		cartel.setBounds(x/2-120, y/2-120, 250, 250);
		ImageIcon img = new ImageIcon(GUI_nueva.class.getResource("/img/gamerOver.gif"));
		Icon icon = new ImageIcon(img.getImage().getScaledInstance(cartel.getWidth(), cartel.getHeight(), Image.SCALE_DEFAULT));
		cartel.setIcon(icon);
		panelJuego.add(cartel);
	}
	
	public void cartel() {
		JLabel go = new JLabel();
		go.setBounds(x/2-120, y/2-120, 250, 250);
		ImageIcon img = new ImageIcon(GUI_nueva.class.getResource("/img/go.gif"));
		Icon icon = new ImageIcon(img.getImage().getScaledInstance(go.getWidth(), go.getHeight(), Image.SCALE_DEFAULT));
		go.setIcon(icon);
		panelJuego.add(go);
		AutoRemove ar = new AutoRemove(go, panelJuego, 4);
		ar.start();
		miJuego.congelarTodos();
		
		AutoAlgoritmo habilitar = new AutoAlgoritmo(new DescongelarTodos(), 4, miJuego); 
		habilitar.start();
	}
	private void agregarJugador() {
		jugador = miJuego.getJugador();
		panelJuego.add(jugador);
	}
	
	public void pasarNivel() {
		miJuego.pasarNivel();
		fondo = new ImageIcon(GUI_nueva.class.getResource("/img/"+miJuego.getMapa().getNivelActual().getGrafico())).getImage();
		agregarInfectados(miJuego.getHilo().getInfectados());
		cartel();
		repintar();
	}
	
	public void agregarInfectados(List<Infectado> infectados) {
		for(Infectado infectado : infectados) {
			if(infectado.getJugando())
				panelJuego.add(infectado);
		}
	}
	
	public void disparo(Proyectil disparo) {
		panelJuego.add(disparo);
		repintar();
	}
	
	
	public void repintar() {
		this.repaint();
	}
	

	
	public JPanel getPanel() { return panelJuego; }
	
	public Juego getJuego() { return miJuego; }
}
