package logica;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import GUI.GUI;
import entidades.*;
import logica.algoritmos.DescongelarTodos;
import logica.hilos.HiloMovimiento;

public class Juego {

	private static Juego juego;
	private static Mapa miMapa;
	private int nivelActual;
	private Jugador jugador;
	private HiloMovimiento hilo;
	private boolean jugando;
	private int desinfectados;
	
	public static Juego get() {
		if(juego == null) {
			juego = new Juego();
			miMapa = Mapa.get();
		} 
		return juego;
	}
	
	private Juego() { }
	
	// Fin patrón
	
	public void crear(int x, int y, int lineaY, int niveles, JFrame frame) {
		miMapa.crear(x,y,lineaY,niveles);
		nivelActual = 0;
		jugador = Jugador.get(3);
		hilo = new HiloMovimiento(((GUI)frame));
	}
	
	public void empezar() {
		nivelActual = 1; // El nivel actual en el backend es nivelActual-1 y en el frontend simplemente es nivelActual.
		miMapa.empezar(nivelActual-1);
		// Hilo de movimientos
		hilo.empezar();
		for(Infectado infectado : miMapa.getNivelActual().getInfectados()) {
			if(infectado.getJugando())
				hilo.getInfectados().add(infectado);
		}
		jugando = true;
		
		this.congelarTodos();
		AutoAlgoritmo habilitar = new AutoAlgoritmo(new DescongelarTodos(), 4, this); 
		habilitar.start();
	}
	
	public void congelarTodos() {
		List<Entidad> entidades = new ArrayList<Entidad>();
		entidades.add(jugador);
		// Agrega todos los infectados, premios, particulas y/o proyectiles para congelar.
		for(Infectado infectado : hilo.getInfectados())
			entidades.add(infectado);
		for(Premio premio : hilo.getPremio())
			entidades.add(premio);
		for(Particula particula : hilo.getParticulas())
			entidades.add(particula);
		for(Proyectil proyectil : hilo.getProyectiles())
			entidades.add(proyectil);
		for(Entidad entidad : entidades)
			entidad.congelar();
	}
	
	public void descongelarTodos() {
		List<Entidad> entidades = new ArrayList<Entidad>();
		entidades.add(jugador);
		// Agrega todos los infectados, premios, particulas y/o proyectiles para descongelar.
		for(Infectado infectado : hilo.getInfectados())
			entidades.add(infectado);
		for(Premio premio : hilo.getPremio())
			entidades.add(premio);
		for(Particula particula : hilo.getParticulas())
			entidades.add(particula);
		for(Proyectil proyectil : hilo.getProyectiles())
			entidades.add(proyectil);
		for(Entidad entidad : entidades)
			entidad.descongelar();
	}
	
	public void congelarInfectados() {
		for(Infectado infectado : hilo.getInfectados())
			infectado.congelar();
	}
	
	public void descongelarInfectados() {
		for(Infectado infectado : hilo.getInfectados())
			infectado.descongelar();
	}
	
	public boolean getJugando() { return jugando; }
	
	public HiloMovimiento getHilo() {
		return hilo;
	}
	
	public void segundaTanda() {
		miMapa.getNivelActual().segundaTanda();
		for(Infectado infectado : miMapa.getNivelActual().getInfectados()) {
			if(infectado.getJugando())
				hilo.getInfectados().add(infectado);
		}
		this.congelarTodos();
		AutoAlgoritmo habilitar = new AutoAlgoritmo(new DescongelarTodos(), 4, this); 
		habilitar.start();
	}
	
	public List<Infectado> getInfectados(){
		return miMapa.getNivelActual().getInfectados();
	}
	
	public List<Infectado> getInfectadosJugando(){
		return hilo.getInfectados();
	}
	
	public Jugador getJugador() { 
		return jugador;
	}
	
	public int getTandaActual() {
		return miMapa.getNivelActual().getTandaActual();
	}
	
	public void pasarNivel() {
		nivelActual++;
		if(nivelActual-1 < miMapa.getNiveles().size()) {
			miMapa.empezar(nivelActual-1);
			for(Infectado infectado : miMapa.getNivelActual().getInfectados()) {
				if(infectado.getJugando())
					hilo.getInfectados().add(infectado);
			}
		}
		this.congelarTodos();
		AutoAlgoritmo habilitar = new AutoAlgoritmo(new DescongelarTodos(), 4, this); 
		habilitar.start();
	}
	
	public void addDesinfectado() {
		desinfectados++;
	}
	
	public int getDesinfectadosTotales() {
		return desinfectados;
	}
	
	public boolean getUltimoNivel() {
		return miMapa.getNivelActual() == miMapa.getNiveles().get(miMapa.getNiveles().size()-1);
	}
	
	public void ganar() {
		jugando = false;
	}
	
	public void perder() {
		jugando = false;
	}
	
	public Mapa getMapa() {
		return miMapa;
	}
	
	public int getNivelActual() {
		return nivelActual;
	}

	
}
