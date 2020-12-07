package logica;

import java.util.ArrayList;
import java.util.List;

// El mapa hace uso del patrón Singleton.

public final class Mapa{

	private static Mapa mapa;
	private Nivel nivelActual;
	private List<Nivel> misNiveles;
	private int x,y,lineaY;
	
	public static Mapa get() {
		if(mapa == null) {
			mapa = new Mapa();
		} 
		return mapa;
	}
	
	private Mapa() { }
	
	// Fin patrón
	public void crear(int x, int y, int lineaY, int niveles) {
		nivelActual = null; // Cuando se comience el juego se establece el nivel
		misNiveles = new ArrayList<Nivel>();
		this.x = x;
		this.y = y;
		this.lineaY = lineaY;
		
		/*
		 * Creación de niveles.
		 * Patrón para los infectados: cada nivel se incrementan 4 infectados.
		 */
		int infectados = 4;
		for(int i = 0; i < niveles; i++) {
			misNiveles.add(new Nivel(infectados, i+1));
			infectados += 4;
		}
		
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getLinea() { return lineaY; }
	
	public List<Nivel> getNiveles() { return misNiveles; }
	
	public Nivel getNivelActual() {
		return nivelActual;
	}
	
	/**
	 * Comienza un nivel determinado.
	 * @param nivel Nivel a empezar.
	 */
	public void empezar(int nivel) {
		nivelActual = misNiveles.get(nivel);
	}
}
