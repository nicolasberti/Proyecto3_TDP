package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entidades.*;

public class Nivel {

	private int infectados;
	private List<Infectado> misInfectados;
	private String grafico;
	
	public Nivel(int infectados, int nivel, String grafico) {
		this.infectados = infectados;
		misInfectados = new ArrayList<Infectado>();
		Random rnd = new Random();
		for(int i = 0; i < infectados; i++) {
			int random = rnd.nextInt(2);
			if(random == 0) {
				misInfectados.add(new Alpha());
			} else {
				misInfectados.add(new Beta());
			}
		}
		this.grafico = grafico; // nombre de la imagen del nivel
	}
	
	public List<Infectado> getInfectados() { return misInfectados; }
	
	public String getGrafico() {
		return grafico;
	}
	
}
