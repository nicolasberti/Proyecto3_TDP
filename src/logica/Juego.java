package logica;

import java.util.List;

import javax.swing.JFrame;

import entidades.*;

public class Juego {

	private int nivelActual;
	private Mapa miMapa; // La clase encargada de dar las ImageIcon es la clase "EntidadGrafica" con el metodo getGrafico();
	private Jugador jugador;
	private HiloMovimiento hilo;
	
	/**
	 * Creación del juego.
	 * @param niveles Cantidad de niveles.
	 */
	public Juego(int x, int y, int lineaY, int niveles, JFrame frame) {
		miMapa = new Mapa(x,y,lineaY,niveles);
		nivelActual = 0;
		jugador = new Jugador(3);
		hilo = new  HiloMovimiento(this,frame);
	}
	
	public void empezar() {
		nivelActual = 1; // El nivel actual en el backend es nivelActual-1 y en el frontend simplemente es nivelActual.
		miMapa.empezar(0);
		// Hilo de movimientos
		hilo.empezar();
		for(Infectado infectado : miMapa.getNivelActual().getInfectados()) {
			if(infectado.getJugando())
				hilo.getInfectados().add(infectado);
		}
		
	}
	
	public HiloMovimiento getHilo() {
		return hilo;
	}
	
	public void segundaTanda() {
		miMapa.getNivelActual().segundaTanda();
	}
	
	public List<Infectado> getInfectadosActuales(){
		return miMapa.getNivelActual().getInfectados();
	}
	
	public Jugador getJugador() { 
		return jugador;
	}
	
	public void pasarNivel() {
		nivelActual++;
		miMapa.empezar(nivelActual);
	}
	
	public void ganar() {
		
	}
	
	public void perder() {
		
	}
	
	public Mapa getMapa() {
		return miMapa;
	}
	
	public int getNivelActual() {
		return nivelActual;
	}

	
}
