package logica;

public class Descongelar extends Algoritmo {

	@Override
	public void ejecutar(Object object) {
		((Juego)object).descongelarInfectados();
	}

}
