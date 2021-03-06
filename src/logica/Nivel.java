package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entidades.*;
import entidades.infectados.Alpha;
import entidades.infectados.Beta;

public class Nivel {

	private int infectados;
	private List<Infectado> misInfectados;
	private int tandaActual; // Si tandaActual >= 3 es porque el nivel termin�.
	
	public Nivel(int infectados, int nivel) {
		this.infectados = infectados;
		misInfectados = new ArrayList<Infectado>();
		Random rnd = new Random();
		for(int i = 0; i < infectados; i++) {
			int random = rnd.nextInt(2);
			Infectado infectado;
			if(random == 0) {
				infectado = new Alpha(nivel);
				
			} else {
				infectado = new Beta(nivel);
			}
			if(i < infectados/2)
				infectado.setJugando(true);
			else
				infectado.setJugando(false);
			misInfectados.add(infectado);
		}
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
	
}
