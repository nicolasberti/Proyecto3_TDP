package logica;

import java.util.ArrayList;
import java.util.List;

import entidades.Infectado;

public class Nivel {

	private int infectados;
	private List<Infectado> misInfectados;
	private String grafico;
	
	public Nivel(int infectados, int nivel, String grafico) {
		this.infectados = infectados;
		misInfectados = new ArrayList<Infectado>();
		this.grafico = grafico; // nombre de la imagen del nivel
	}
	
	public String getGrafico() {
		return grafico;
	}
	
}
