package logica;

import java.util.List;

import javax.swing.JFrame;

import aplicacion.GUI_nueva;
import entidades.*;

public class Juego {

	private int nivelActual;
	private Mapa miMapa; // La clase encargada de dar las ImageIcon es la clase "EntidadGrafica" con el metodo getGrafico();
	private Jugador jugador;
	private HiloMovimiento hilo;
	private boolean jugando;
	
	/**
	 * Creación del juego.
	 * @param niveles Cantidad de niveles.
	 */
	public Juego(int x, int y, int lineaY, int niveles, JFrame frame) {
		miMapa = new Mapa(x,y,lineaY,niveles);
		nivelActual = 0;
		jugador = new Jugador(3);
		hilo = new  HiloMovimiento(this,((GUI_nueva)frame));
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
	}
	
	public List<Infectado> getInfectadosActuales(){
		return miMapa.getNivelActual().getInfectados();
	}
	
	public Jugador getJugador() { 
		return jugador;
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
