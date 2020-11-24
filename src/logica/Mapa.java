package logica;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Mapa extends JPanel {

	private Nivel nivelActual;
	private List<Nivel> misNiveles;
	private int x,y,lineaY;
	
	public Mapa(int x, int y, int lineaY, int niveles) {
		super();
		nivelActual = null; // Cuando se comience el juego se establece el nivel
		misNiveles = new ArrayList<Nivel>();
		this.x = x;
		this.y = y;
		this.lineaY = lineaY;
		
		/*
		 * Creaci�n de niveles.
		 * Patr�n para los infectados: cada nivel se incrementan 4 infectados.
		 */
		int infectados = 4;
		for(int i = 0; i < niveles; i++) {
			System.out.println(infectados);
			misNiveles.add(new Nivel(infectados, i, "nivel_"+ (i+1) +".png"));
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
