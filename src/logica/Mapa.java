package logica;

import java.util.ArrayList;
import java.util.List;

public class Mapa {

	private Nivel nivelActual;
	private List<Nivel> misNiveles;
	private int x,y,lineaY;
	
	public Mapa(int x, int y, int lineaY, int niveles) {
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
			misNiveles.add(new Nivel(infectados, i, "nivel_"+ (i+1) +".png"));
			infectados += 4;
		}
		
	}
	
	public Nivel getNivelActual() {
		return nivelActual;
	}
	
	public void empezar() {
		nivelActual = misNiveles.get(0);
	}
}
