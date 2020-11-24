package logica;

import entidades.Entidad;

public class AutoAlgoritmo extends Thread {

	private Algoritmo algoritmo;
	private int segundos;
	private Entidad entidad;
	
	public AutoAlgoritmo(Algoritmo algoritmo, int segundos, Entidad entidad) {
		super();
		this.algoritmo = algoritmo;
		this.segundos = segundos;
		this.entidad = entidad;
	}
	
	public void run() {
		try {
			Thread.sleep(segundos*1000);
			algoritmo.ejecutar(entidad);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
