package logica;

import java.util.List;

import entidades.*;

public class Juego {

	private int nivelActual;
	private Mapa miMapa; // La clase encargada de dar las ImageIcon es la clase "EntidadGrafica" con el metodo getGrafico();
	private Jugador jugador;
	
	/**
	 * Creación del juego.
	 * @param niveles Cantidad de niveles.
	 */
	public Juego(int x, int y, int lineaY, int niveles) {
		miMapa = new Mapa(x,y,lineaY,niveles);
		nivelActual = 0;
		jugador = new Jugador(3);
	}
	
	public void empezar() {
		nivelActual = 1; // El nivel actual en el backend es nivelActual-1 y en el frontend simplemente es nivelActual.
		miMapa.empezar();
	}
	
	public List<Infectado> getInfectadosActuales(){
		return miMapa.getNivelActual().getInfectados();
	}
	
	public Jugador getJugador() { 
		return jugador;
	}
	
	public void pasarNivel() {
		
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
