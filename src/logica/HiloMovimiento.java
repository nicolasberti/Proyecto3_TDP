package logica;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aplicacion.*;

import javax.swing.JFrame;

import entidades.*;

public class HiloMovimiento extends Thread {

	private Juego miJuego;
	private List<Infectado> infectados;
	private List<Premio> premios;
	private List<Proyectil> proyectiles;
	private List<Particula> particulas;
	private JFrame frame;
	private boolean jugando = false;
	
	public HiloMovimiento(Juego miJuego, JFrame frame) {
		this.miJuego = miJuego;
		this.infectados = new ArrayList<Infectado>();
		this.premios =  new ArrayList<Premio>();
		this.proyectiles =  new ArrayList<Proyectil>();
		this.particulas = new ArrayList<Particula>();
		this.frame = frame;
	}
	
	public List<Infectado> getInfectados(){ return infectados; }
	public List<Premio> getPremio(){ return premios; }
	public List<Proyectil> getProyectiles(){ return proyectiles; }
	public List<Particula> getParticulas(){ return particulas; }
	
	public void empezar() {
		jugando = true;
		this.start();
	}
	
	public void run() {
		try {
			
			int tiempo = 200; // Tiempo del algoritmo en milisegundos.
			int contadorSegundos = 0; // Contador de segundos. (Se lee
			
			
			while(jugando) {
				HiloMovimiento.sleep(tiempo); 
				
				
				// Movimiento de los infectados
				for(Infectado infectado : infectados) {
					if(infectado.getY() >= miJuego.getMapa().getLinea()) { // El hilo se encarga de comprobar si las entidades pasan la línea de abajo o arriba según el tiempo de entidad que se esté moviendo.
						infectado.volverPos();
					} else {
						infectado.moverse();
					}

					// Cada 2 segundos, un infectado puede o no, expandir una particula.
					if( (contadorSegundos*tiempo)%2000 == 0 ) {
						Random rnd = new Random();
						int random = rnd.nextInt(2);
						if(random == 0) {
							// Hacer que la particula se genere entre X=[infectado.getX()-30, infectado.getX()+30]
							// Si está muy cerca de la izquierda ( infectado.getX()-30 < 0 ) simplemente X=[infectado.getX(), infectado.getX()+30]
							// Si está muy cerca de la derecha ( infectado.getX()+30 > mapa.getX() ) simplemente X=[infectado.getX()-30, infectado.getX()]
							
							Particula particulaNueva = new Particula(infectado.getX(), infectado.getY(), 4);
							particulas.add(particulaNueva);
							
							// Agregar particula al panel.
						}
					}
					
					// Colisión entre infectado y jugador
					if(miJuego.getJugador().estaEnElRadio(infectado)) {
						
					}
					
					// Colisión entre un infectado y un proyectil.
					for(Proyectil proyectil : proyectiles) {
						if(infectado.estaEnElRadio(proyectil)) {
							
						}	
					}
					
				}
				
				List<Particula> listaRemove = new ArrayList<Particula>();
				// Movimiento de las particulas
				for(Particula particula : particulas) {
					if(particula.getTiempo() <= 4) {
						if(particula.getY() >= miJuego.getMapa().getLinea()) {
							listaRemove.add(particula);
						} else {
							particula.moverse();
							// Colisión entre jugador y particula
							if(miJuego.getJugador().estaEnElRadio(particula)) {
									
							}
							// Fin colisión
							if( (contadorSegundos*tiempo)%1000 == 0 )
								particula.sumarTiempo();
						}
					} else {
						listaRemove.add(particula);
					}
				}
				if(listaRemove.size() > 0)
					// También hacer que las particulas se eliminel del panel. -> método getPanel() en la GUI.
					particulas.removeAll(listaRemove);
				
				
				// Movimiento premios
				for(Premio premio : premios) {
					if(miJuego.getJugador().estaEnElRadio(premio)) {
						
					}
				}
				
				contadorSegundos++; // es segundo si y solo si (contadorSegundos*tiempo)%1000=0
				if(contadorSegundos >= 10000) // En caso de que la variable tome valores muy grandes, se restablece cuando es mayor o igual a 10000.
					contadorSegundos = 0;
					
				((GUI_nueva) frame).repaint();
			}
		
		
		} 
		catch (InterruptedException e1) { e1.printStackTrace(); }
		
		
	}

}
