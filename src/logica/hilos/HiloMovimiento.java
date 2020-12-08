package logica.hilos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import GUI.*;
import entidades.*;
import entidades.premios.CuarentenaObligatoria;
import entidades.premios.RecuperarVida;
import entidades.premios.SuperArma;
import logica.Juego;
import logica.Mapa;
import logica.visitors.VisitorInfectado;
import logica.visitors.VisitorParticula;
import logica.visitors.VisitorProyectil;

public class HiloMovimiento extends Thread {
	
	private List<Infectado> infectados;
	private List<Premio> premios;
	private List<Proyectil> proyectiles;
	private List<Particula> particulas;
	private GUI_juego frame;
	private boolean jugando = false;
	
	
	public HiloMovimiento(GUI_juego frame) {
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
			Jugador jugador = Juego.get().getJugador();
			Mapa mapa = Juego.get().getMapa();
			Juego juego = Juego.get();
			while(jugando) {
				HiloMovimiento.sleep(tiempo); 
				List<Infectado> removerInfectados = new ArrayList<Infectado>();
				List<Particula> removerParticulas = new ArrayList<Particula>();
				List<Premio> removerPremios = new ArrayList<Premio>();
				List<Proyectil> removerProyectiles = new ArrayList<Proyectil>();
				// Si bien I�aki en el segundo Sprint nos dijo que podiamos hacer directamente una lista que simplemente tenga Entidades
				// Prefieremos hacerlo as� porque algunas listas interact�an de diferente manera en el hilo.
				
					// ************************* Movimiento de los infectados ************************* 
					
					for(Infectado infectado : infectados) {
						
						if(infectado.getY() >= mapa.getLinea()) { // El hilo se encarga de comprobar si las entidades pasan la l�nea de abajo o arriba seg�n el tiempo de entidad que se est� moviendo.
							infectado.volverPos();
						} else {
							infectado.moverse();
							
						}
						
						
						// Cada 2 segundos, un infectado puede o no, expandir una particula. Cae en forma vertical.
						if( contadorSegundos >= 2 && (contadorSegundos*tiempo)%2000 == 0 ) {
							Random rnd = new Random();
							int random = rnd.nextInt(2);
							if(random == 0) {
								if(!infectado.getCongelado()) {
									// El da�o de las particulas es el da�o directo del Infectado divido por dos.
									Particula particulaNueva = new Particula(infectado.getDanio()/2, infectado.getX(), infectado.getY(), 4);
									particulas.add(particulaNueva);
									frame.agregarPanel(particulaNueva);
								}
							}
						}
						
						// Colisi�n entre infectado y jugador
						if(jugador.estaEnElRadio(infectado)) {
							boolean perdio = jugador.accept(new VisitorInfectado(infectado));
							if(perdio) {
								this.perder();
								break;
							}
						}
						
						// Colisi�n entre un infectado y un proyectil.
						for(Proyectil proyectil : proyectiles) {
							if(infectado.estaEnElRadio(proyectil)) {
								// Crea una explosi�n en el panel del Juego.
								frame.crearExplosion(proyectil.getX(), proyectil.getY());
								//
								boolean borrar = infectado.accept(new VisitorProyectil(proyectil));
								if(borrar) {
									removerInfectados.add(infectado);
									Random rnd = new Random();
									int random = rnd.nextInt(2);
									if(random == 0) {
										Premio premio = this.premioRandom(infectado.getX(), infectado.getY());
										premios.add(premio);
										frame.agregarPanel(premio);
									}
									juego.addDesinfectado();
								}
								removerProyectiles.add(proyectil);
								
							}
						}
						removerProyectiles(removerProyectiles);
					}
					removerInfectados(removerInfectados);
					
					// Chequeo si termina el nivel
					if(infectados.size()==0) {
						if(juego.getMapa().getNivelActual().getTandaActual() == 1) {
							juego.segundaTanda();
							frame.segundaTanda();
							frame.agregarInfectados(infectados);
							removerProyectiles(agregarProyectiles());
							removerParticulas(agregarParticulas());
						} else {
							removerProyectiles(agregarProyectiles());
							removerParticulas(agregarParticulas());
							if(juego.getUltimoNivel()) {
								// Gana el juego;
								this.ganar();
								break;
							} else {
								frame.pasarNivel();
							}
						}
					}
					//******************* FIN MOVIMIENTO INFECTADOS *************************
					
					
					// ************************* Movimiento de las particulas *************************
					
					for(Particula particula : particulas) {
						if(particula.getTiempo() <= 4) {
							if(particula.getY() >= mapa.getLinea()) {
								removerParticulas.add(particula);
							} else {
								particula.moverse();
								// Colisi�n entre jugador y particula
								if(jugador.estaEnElRadio(particula)) {
									boolean perdio = jugador.accept(new VisitorParticula(particula));
									if(perdio) {
										this.perder();
										break;
									}
									// Si colisiona contra un particula, la particula se borra.
									removerParticulas.add(particula);
								}
								// Fin colisi�n
								if( (contadorSegundos*tiempo)%1000 == 0 )
									if(!particula.getCongelado()) // Si no est� congelada suma tiempo.
										particula.sumarTiempo();
							}
						} else {
							removerParticulas.add(particula);
						}
					}
					removerParticulas(removerParticulas);
					// ************************* FIN MOVIMIENTO PARTICULAS *************************
					
					// ************************* Movimiento premios *************************
					
					for(Premio premio : premios) {
						if(premio.getY() >= juego.getMapa().getLinea()) { 
							removerPremios.add(premio);
						} else {
							premio.moverse();
						}
						// Colisi�n entre el premio y el jugador.
						if(jugador.estaEnElRadio(premio)) {
							removerPremios.add(premio);
							Juego.get().getJugador().usarPremio(premio);
							frame.notificacion("�Has utilizado un premio!");
						}
					}
					removerPremios(removerPremios);
					// ************************* FIN MOVIMIENTO PREMIOS *************************
					
					
					// ************************* Movimiento proyectiles *************************
					
					for(Proyectil proyectil : proyectiles) {
						if(proyectil.getY() <= 0) { 
							removerProyectiles.add(proyectil);
						} else {
							proyectil.moverse();
						}
					}
					removerProyectiles(removerProyectiles);
					// ************************ FIN MOVIMIENTO PROYECTILES *************************
					
					contadorSegundos++; // es segundo si y solo si (contadorSegundos*tiempo)%1000=0
					if(contadorSegundos >= 10000) // En caso de que la variable tome valores muy grandes, se restablece cuando es mayor o igual a 10000.
						contadorSegundos = 0;
						
					frame.repintar();
				}
		
		
		} 
		catch (InterruptedException e1) { e1.printStackTrace(); }
		
		
	}
	
	private Premio premioRandom(int x, int y) {
		Premio premio = null;
		Random rnd = new Random();
		int premioNum = rnd.nextInt(3);
		switch(premioNum) {
			case 0: {
				premio = new CuarentenaObligatoria(x, y);
				break;
			}
			case 1: {
				premio = new RecuperarVida(x,y);
				break;
			}
			case 2: {
				premio = new SuperArma(x,y);
				break;
			}
		}
		return premio;
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
				frame.removerPanel(proyectil);
			}
		}
	}
	
	private void removerParticulas(List<Particula> c) {
		if(c.size() > 0) {
			for(Particula particula : c) {
				particulas.remove(particula);
				frame.removerPanel(particula);
			}
		}
	}
	
	private void removerInfectados(List<Infectado> c) {
		if(c.size() > 0) {
			for(Infectado infectado : c) {
				infectados.remove(infectado);
				frame.removerPanel(infectado);
			}
		}
	}
	
	private void removerPremios(List<Premio> c) {
		if(c.size() > 0) {
			for(Premio premio : c) {
				premios.remove(premio);
				frame.removerPanel(premio);
			}
		}
	}
	
	private void ganar() {
		Juego juego = Juego.get();
		juego.ganar();
		frame.ganar();
		jugando = juego.getJugando();
	}
	
	private void perder() {
		Juego juego = Juego.get();
		juego.perder();
		frame.perder();
		jugando = juego.getJugando();
	}

}