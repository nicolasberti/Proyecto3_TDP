package logica;

public class Juego {

	private int nivelActual;
	private Mapa miMapa; // La clase encargada de dar las ImageIcon es la clase "EntidadGrafica" con el metodo getGrafico();
	
	/**
	 * Creaci�n del juego.
	 * @param niveles Cantidad de niveles.
	 */
	public Juego(int niveles) {
		// tama�o del mapa
		int x = 200;
		int y = 200;
		miMapa = new Mapa(x,y,niveles);
		nivelActual = 0;
	}
	
	public void empezar() {
		nivelActual = 1; // Los niveles actuales en el backend es nivelActual-1 y en el frontend simplemente es nivelActual
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
