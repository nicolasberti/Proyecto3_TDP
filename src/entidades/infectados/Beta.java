package entidades.infectados;

import entidades.Infectado;
import entidades.Posicion;
@SuppressWarnings("serial")
public class Beta extends Infectado {

	public Beta(int nivel) {
		super.nivel = nivel;
		super.rangoDeInfeccion = 30;
		super.cargaDesinfeccion = 2;
		super.danio = 2;
		int cordenadas[] = super.generarCordenadas();
		super.posicion = new Posicion(cordenadas[0], cordenadas[1],rangoDeInfeccion);
		super.velocidad = 1;
		super.posInicial = new int[2];
		super.posInicial[0] = posicion.getX();
		super.posInicial[1] = posicion.getY();
		super.girando = false;
		this.setBounds(posicion.getX(), posicion.getY(), 50, 50);
		actualizarImagen();
	}

}
