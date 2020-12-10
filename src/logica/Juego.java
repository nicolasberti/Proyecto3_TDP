package logica;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import GUI.GUI_juego;
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
		hilo = new HiloMovimiento(((GUI_juego)frame));
	}
	
	public void empezar() {
		nivelActual = 1; // El nivel actual en el backend es nivelActual-1 y en el frontend simplemente es nivelActual.
		miMapa.empezar(nivelActual-1);
		// Hilo de movimientos
		hilo.empezar();
		for(Infectado infectado : miMapa.getNivelActual().getInfectados()) {
			if(infectado.getJugando())
				hilo.getEntidades().add(infectado);
		}
		jugando = true;
		hilo.getEntidades().add(jugador);
		this.congelarTodos();
		AutoAlgoritmo habilitar = new AutoAlgoritmo(new DescongelarTodos(), 4, this); 
		habilitar.start();
	}
	
	public void congelarTodos() {
		List<Entidad> entidades = new ArrayList<Entidad>();
		entidades.add(jugador);
		for(Entidad entidad : hilo.getEntidades())
			entidades.add(entidad);
		for(Entidad entidad : entidades)
			entidad.congelar();
	}
	
	public void descongelarTodos() {
		List<Entidad> entidades = new ArrayList<Entidad>();
		entidades.add(jugador);
		for(Entidad entidad : hilo.getEntidades())
			entidades.add(entidad);
		for(Entidad entidad : entidades)
			entidad.descongelar();
	}
	
	public void congelarInfectados() {
		for(Entidad entidad : hilo.getEntidades())
			if( esInfectado(entidad))
				entidad.congelar();
	}
	
	public void descongelarInfectados() {
		for(Entidad entidad : hilo.getEntidades())
			if( esInfectado(entidad))
				entidad.descongelar();
	}
	
	public List<Infectado> getInfectadosActuales(){
		List<Infectado> c = new ArrayList<Infectado>();
		for(Entidad entidad : hilo.getEntidades())
			if( esInfectado(entidad))
				c.add(((Infectado)entidad));
		return c;
	}
	
	private boolean esInfectado(Entidad entidad) {
		if( superClase(entidad.getClass()).endsWith("Infectado") )
			return true;
		else 
			return false;
	}
	
	private String superClase(Class clase) {
		if( clase.getSuperclass().toString().endsWith("Entidad"))
			return clase.toString();
		else
			return superClase(clase.getSuperclass());
	}
	
	public boolean getJugando() { return jugando; }
	
	public HiloMovimiento getHilo() {
		return hilo;
	}
	
	public void segundaTanda() {
		miMapa.getNivelActual().segundaTanda();
		for(Infectado infectado : miMapa.getNivelActual().getInfectados()) {
			if(infectado.getJugando())
				hilo.getEntidades().add(infectado);
		}
		this.congelarTodos();
		AutoAlgoritmo habilitar = new AutoAlgoritmo(new DescongelarTodos(), 4, this); 
		habilitar.start();
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
					hilo.getEntidades().add(infectado);
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
