package GUI;

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
import logica.Juego;
import logica.hilos.HiloTeclado;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Font;

public class GUI_juego extends JFrame {

	private JPanel panelPrincipal;
	private static int x = 600;
	private static int y = 500;
	private static int lineaY = 470;
	private Juego miJuego; 
	private JPanel panelScore; 
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
	private int exVida;
	private JLabel vida_0;
	private JLabel vida_1;
	private JLabel vida_2;
	private JLabel vida_3;
	private JLabel desinfectados;
	private JLabel nivelActual;
	private JLabel cargaViralJ;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_juego frame = new GUI_juego();
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
	public GUI_juego() {
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
		
		vida_0 = new JLabel("");
		vida_0.setBounds(10, 11, 52, 52);
		ImageIcon image = new ImageIcon(GUI_juego.class.getResource("/img/heart.gif"));
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(vida_0.getWidth(), vida_0.getHeight(), Image.SCALE_DEFAULT));
		vida_0.setIcon(icon);
		panelScore.add(vida_0);
		
		vida_1 = new JLabel("");
		vida_1.setBounds(72, 11, 52, 52);
		vida_1.setIcon(icon);
		panelScore.add(vida_1);
		
		vida_2 = new JLabel("");
		vida_2.setBounds(134, 11, 52, 52);
		vida_2.setIcon(icon);
		panelScore.add(vida_2);
		
		vida_3 = new JLabel("");
		vida_3.setBounds(196, 11, 52, 52);
		vida_3.setIcon(icon);
		panelScore.add(vida_3);
		
		vida_0.setVisible(false);
		vida_1.setVisible(false);
		vida_2.setVisible(false);
		vida_3.setVisible(false);
		
		desinfectados = new JLabel("Desinfectados totales: 9999");
		desinfectados.setBounds(304, 11, 286, 14);
		panelScore.add(desinfectados);
		desinfectados.setVisible(false);
		
		nivelActual = new JLabel("Nivel actual: 9999 - Primer tanda");
		nivelActual.setBounds(304, 32, 286, 14);
		panelScore.add(nivelActual);
		
		 cargaViralJ = new JLabel("Carga viral: 0%");
		cargaViralJ.setBounds(458, 11, 257, 14);
		panelScore.add(cargaViralJ);
		
		
		
		nivelActual.setVisible(false);
		cargaViralJ.setVisible(false);
		
	}
	
	// Noficaciones
	
	public void notificacion(String texto) {
		JLabel notificaciones = new JLabel(texto);
		notificaciones.setFont(new Font("Tahoma", Font.BOLD, 12));
		notificaciones.setVerticalAlignment(SwingConstants.BOTTOM);
		notificaciones.setBounds(306, 51, 276, 14);
		panelScore.add(notificaciones);
		AutoRemove ar = new AutoRemove(notificaciones, panelScore, 2);
		ar.start();
	}
	
	// Comenzar el juego
	public void empezarJuego() {
		miJuego.empezar();
		// Mapa
		fondo = new ImageIcon(GUI_juego.class.getResource("/img/niveles/nivel_"+miJuego.getNivelActual()+".png")).getImage();
		jugador = miJuego.getJugador();
		agregarJugador();
		agregarInfectados(miJuego.getHilo().getInfectados());
		this.addKeyListener(new HiloTeclado(this)); // Movimientos del jugador.
		// Panel score
		this.cartel();
		this.actualizarScore();
		desinfectados.setVisible(true);
		nivelActual.setVisible(true);
		cargaViralJ.setVisible(true);
	}
	
	// Carteles
	public void ganar() {
		JLabel cartel = new JLabel();
		cartel.setBounds((x/2)-140, (y/2)-150, 400, 200);
		ImageIcon img = new ImageIcon(GUI_juego.class.getResource("/img/juegoGanado.gif"));
		Icon icon = new ImageIcon(img.getImage().getScaledInstance(cartel.getWidth(), cartel.getHeight(), Image.SCALE_DEFAULT));
		cartel.setIcon(icon);
		panelJuego.add(cartel);
		this.repintar();
	}
	
	public void perder() {
		JLabel cartel = new JLabel();
		cartel.setBounds(x/2-140, y/2-150, 300, 400);
		ImageIcon img = new ImageIcon(GUI_juego.class.getResource("/img/fin.png"));
		Icon icon = new ImageIcon(img.getImage().getScaledInstance(cartel.getWidth(), cartel.getHeight(), Image.SCALE_DEFAULT));
		cartel.setIcon(icon);
		panelJuego.add(cartel);
		this.repintar();
	}
	
	public void segundaTanda() {
		JLabel go = new JLabel();
		go.setBounds((x/2-120)-70, (y/2-120)-140, 400, 250);
		ImageIcon img = new ImageIcon(GUI_juego.class.getResource("/img/segundaTanda.png"));
		Icon icon = new ImageIcon(img.getImage().getScaledInstance(go.getWidth(), go.getHeight(), Image.SCALE_DEFAULT));
		go.setIcon(icon);
		panelJuego.add(go);
		AutoRemove ar = new AutoRemove(go, panelJuego, 4);
		ar.start();
		this.cartel();
	}
	
	public void cartel() {
		JLabel go = new JLabel();
		go.setBounds(x/2-120, y/2-120, 250, 250);
		ImageIcon img = new ImageIcon(GUI_juego.class.getResource("/img/go.gif"));
		Icon icon = new ImageIcon(img.getImage().getScaledInstance(go.getWidth(), go.getHeight(), Image.SCALE_DEFAULT));
		go.setIcon(icon);
		panelJuego.add(go);
		AutoRemove ar = new AutoRemove(go, panelJuego, 4);
		ar.start();
		this.repintar();
	}
	private void agregarJugador() {
		jugador = miJuego.getJugador();
		panelJuego.add(jugador);
	}
	
	public void pasarNivel() {
		miJuego.pasarNivel();
		fondo = new ImageIcon(GUI_juego.class.getResource("/img/niveles/nivel_"+miJuego.getNivelActual()+".png")).getImage();
		agregarInfectados(miJuego.getHilo().getInfectados());
		cartel();
		this.notificacion("¡Has pasado un nivel!");
		repintar();
	}
	
	public void agregarInfectados(List<Infectado> infectados) {
		for(Infectado infectado : infectados) {
			if(infectado.getJugando())
				panelJuego.add(infectado);
		}
	}
	
	public void agregarPanel(JLabel entidad) {
		panelJuego.add(entidad);
	}
	
	public void crearExplosion(int x, int y) {
		JLabel explosion = new JLabel();
		explosion.setBounds(x, y, 40, 40);
		ImageIcon img = new ImageIcon(GUI_juego.class.getResource("/img/boom.gif"));
		Icon icon = new ImageIcon(img.getImage().getScaledInstance(explosion.getWidth(), explosion.getHeight(), Image.SCALE_DEFAULT));
		explosion.setIcon(icon);
		this.autoRemover(explosion, 1);
		this.agregarPanel(explosion);
	}
	
	public void removerPanel(JLabel entidad) {
		panelJuego.remove(entidad);
	}
	
	public void disparo(Proyectil disparo) {
		panelJuego.add(disparo);
		repintar();
	}
	
	public void autoRemover(JLabel entidad, int segundos) {
		AutoRemove ar = new AutoRemove(entidad, panelJuego, segundos);
		ar.start();
	}
	
	public void repintar() {
		if(100-jugador.getCargaViral() != exVida)
			actualizarVida(100-jugador.getCargaViral());
		actualizarScore();
		this.repaint();
	}
	
	private void actualizarScore() {
		desinfectados.setText("Desinfectados totales: "+miJuego.getDesinfectadosTotales());
		if(miJuego.getTandaActual() == 1)
			nivelActual.setText("Nivel actual: "+miJuego.getNivelActual()+" | Primer tanda");
		else
			nivelActual.setText("Nivel actual: "+miJuego.getNivelActual()+" | Segunda tanda");
		cargaViralJ.setText("Carga viral: "+miJuego.getJugador().getCargaViral()+"%");
	}
	
	private void actualizarVida(int vida) {
		exVida = vida;
		if(vida <= 0) {
			ImageIcon image = new ImageIcon(GUI_juego.class.getResource("/img/heartGreen.gif"));
			Icon icon = new ImageIcon(image.getImage().getScaledInstance(vida_0.getWidth(), vida_0.getHeight(), Image.SCALE_DEFAULT));
			vida_0.setIcon(icon);
			vida_1.setIcon(icon);
			vida_2.setIcon(icon);
			vida_3.setIcon(icon);
			vida_0.setVisible(true);
			vida_1.setVisible(true);
			vida_2.setVisible(true);
			vida_3.setVisible(true);
		} else if(vida > 0 && vida <= 25) {
			vida_0.setVisible(true);
			vida_1.setVisible(false);
			vida_2.setVisible(false);
			vida_3.setVisible(false);
		} else if(vida > 25 && vida <= 50) {
			vida_0.setVisible(true);
			vida_1.setVisible(true);
			vida_2.setVisible(false);
			vida_3.setVisible(false);
		} else if(vida > 50 && vida <= 75) {
			vida_0.setVisible(true);
			vida_1.setVisible(true);
			vida_2.setVisible(true);
			vida_3.setVisible(false);
		} else if(vida > 75) {
			vida_0.setVisible(true);
			vida_1.setVisible(true);
			vida_2.setVisible(true);
			vida_3.setVisible(true);
		}
	}
}
