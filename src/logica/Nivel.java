package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entidades.*;

public class Nivel {

	private int infectados;
	private List<Infectado> misInfectados;
	private String grafico;
	private int tandaActual; // Si tandaActual >= 3 es porque el nivel terminó.
	
	public Nivel(int infectados, int nivel, String grafico, Mapa miMapa) {
		this.infectados = infectados;
		misInfectados = new ArrayList<Infectado>();
		Random rnd = new Random();
		for(int i = 0; i < infectados; i++) {
			int random = rnd.nextInt(2);
			Infectado infectado;
			if(random == 0) {
				infectado = new Alpha(miMapa);
				
			} else {
				infectado = new Beta(miMapa);
			}
			if(i < infectados/2)
				infectado.setJugando(true);
			else
				infectado.setJugando(false);
			misInfectados.add(infectado);
		}
		this.grafico = grafico; // nombre de la imagen del nivel
		tandaActual = 1;
	}
	
	public List<Infectado> getInfectados() { return misInfectados; }
	
	public int infectadosJugando() {
		int cont = 0;
		for(Infectado infectado : misInfectados) {
			if(infectado.getJugando())
				cont++;
		}
		return cont;
	}
	
	public int getTandaActual() { return tandaActual; }
	
	public void segundaTanda() {
		int cont = 0;
		for(Infectado infectado : misInfectados) {
			if(cont >= infectados/2) {
				infectado.setJugando(true);
			}
			cont++;
		}
		tandaActual = 2;
	}
	
	public String getGrafico() {
		return grafico;
	}
	
}
