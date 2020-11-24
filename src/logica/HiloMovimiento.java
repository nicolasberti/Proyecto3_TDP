package logica;

import java.util.ArrayList;
import java.util.Iterator;
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
	private GUI_nueva frame;
	private boolean jugando = false;
	
	public HiloMovimiento(Juego miJuego, GUI_nueva frame) {
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
			int contadorSegundos = 0; // Contador de segundos.
			
			while(jugando) {
				HiloMovimiento.sleep(tiempo); 
				
				// ************************* Movimiento de los infectados ************************* 
				List<Infectado> removerInfectados = new ArrayList<Infectado>();
				for(Infectado infectado : infectados) {
					if(infectado.getY() >= miJuego.getMapa().getLinea()) { // El hilo se encarga de comprobar si las entidades pasan la línea de abajo o arriba según el tiempo de entidad que se esté moviendo.
						infectado.volverPos();
					} else {
						infectado.moverse();
					}

					// Cada 2 segundos, un infectado puede o no, expandir una particula. Cae en forma vertical.
					if( (contadorSegundos*tiempo)%2000 == 0 ) {
						Random rnd = new Random();
						int random = rnd.nextInt(2);
						if(random == 0) {
							Particula particulaNueva = new Particula(infectado.getX(), infectado.getY(), 4);
							particulas.add(particulaNueva);
							frame.getPanel().add(particulaNueva);
						}
					}
					
					// Colisión entre infectado y jugador
					if(miJuego.getJugador().estaEnElRadio(infectado)) {
						
					}
					
					// Colisión entre un infectado y un proyectil.
					ArrayList<Proyectil> removerProyectiles = new ArrayList<Proyectil>();
					for(Proyectil proyectil : proyectiles) {
						if(infectado.estaEnElRadio(proyectil)) {
							boolean borrar = infectado.accept(new VisitorProyectil(), proyectil.getCargaDesinfeccion());
							if(borrar)
								removerInfectados.add(infectado);
							removerProyectiles.add(proyectil);
						}
					}
					removerProyectiles(removerProyectiles);
				}
				removerInfectados(removerInfectados);
				
				// Chequeo si termina el nivel
				if(infectados.size()==0) {
					if(frame.getJuego().getMapa().getNivelActual().getTandaActual() == 1) {
						frame.getJuego().segundaTanda();
						frame.agregarInfectados(infectados);
						removerProyectiles(agregarProyectiles());
						removerParticulas(agregarParticulas());
					} else {
						removerProyectiles(agregarProyectiles());
						removerParticulas(agregarParticulas());
						if(frame.getJuego().getUltimoNivel()) {
							// Gana el juego;
							miJuego.ganar();
							jugando = miJuego.getJugando();
							break;
						} else {
							frame.pasarNivel();
						}
					}
				}
				//******************* FIN MOVIMIENTO INFECTADOS *************************
				
				
				// ************************* Movimiento de las particulas *************************
				List<Particula> listaRParticula = new ArrayList<Particula>();
				for(Particula particula : particulas) {
					if(particula.getTiempo() <= 4) {
						if(particula.getY() >= miJuego.getMapa().getLinea()) {
							listaRParticula.add(particula);
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
						listaRParticula.add(particula);
					}
				}
				removerParticulas(listaRParticula);
				// ************************* FIN MOVIMIENTO PARTICULAS *************************
				
				// ************************* Movimiento premios *************************
			/*	for(Premio premio : premios) {
					if(miJuego.getJugador().estaEnElRadio(premio)) {
						
					}
				}*/
				// ************************* FIN MOVIMIENTO PREMIOS *************************
				
				
				// ************************* Movimiento proyectiles *************************
				List<Proyectil> listaRProyectil = new ArrayList<Proyectil>();
				for(Proyectil proyectil : proyectiles) {
					if(proyectil.getY() <= 0) { 
						listaRProyectil.add(proyectil);
					} else {
						proyectil.moverse();
					}
				}
				if(listaRProyectil.size() > 0) {
					for(Proyectil proyectil : listaRProyectil) {
						proyectiles.remove(proyectil);
						frame.getPanel().remove(proyectil);
					}
				}
				// ************************ FIN MOVIMIENTO PROYECTILES *************************
				
				contadorSegundos++; // es segundo si y solo si (contadorSegundos*tiempo)%1000=0
				if(contadorSegundos >= 10000) // En caso de que la variable tome valores muy grandes, se restablece cuando es mayor o igual a 10000.
					contadorSegundos = 0;
					
				frame.repaint();
			}
		
		
		} 
		catch (InterruptedException e1) { e1.printStackTrace(); }
		
		
	}
	
	private List<Proyectil> agregarProyectiles() {
		List<Proyectil> lista = new ArrayList<Proyectil>();
		for(Proyectil item : proyectiles)
			lista.add(item);
		return lista;
	}
	
	private List<Particula> agregarParticulas() {
		List<Particula> lista = new ArrayList<Particula>();
		for(Particula item : particulas)
			lista.add(item);
		return lista;
	}
	
	private void removerProyectiles(List<Proyectil> c) {
		if(c.size() > 0) {
			for(Proyectil proyectil : c) {
				proyectiles.remove(proyectil);
				frame.getPanel().remove(proyectil);
			}
		}
	}
	
	private void removerParticulas(List<Particula> c) {
		if(c.size() > 0) {
			for(Particula particula : c) {
				particulas.remove(particula);
				frame.getPanel().remove(particula);
			}
		}
	}
	
	private void removerInfectados(List<Infectado> c) {
		if(c.size() > 0) {
			for(Infectado infectado : c) {
				infectados.remove(infectado);
				frame.getPanel().remove(infectado);
			}
		}
	}

}
