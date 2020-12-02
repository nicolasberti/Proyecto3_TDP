package logica;

import entidades.Entidad;

public class AutoAlgoritmo extends Thread {

	private Algoritmo algoritmo;
	private int segundos;
	private Object object; // Lo más abstracto posible
	
	public AutoAlgoritmo(Algoritmo algoritmo, int segundos, Object object) {
		this.algoritmo = algoritmo;
		this.segundos = segundos;
		this.object = object;
	}
	public void run() {
		try {
			Thread.sleep(segundos*1000);
			algoritmo.ejecutar(object);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
