package entidades.infectados;

import entidades.Infectado;
import entidades.Posicion;
@SuppressWarnings("serial")
public class Alpha extends Infectado {

	private boolean efectoAplicado = false;
	
	public Alpha(int nivel) {
		super.nivel = nivel;
		super.rangoDeInfeccion = 20;
		super.cargaDesinfeccion = 4;
		super.danio = 4;
		int cordenadas[] = super.generarCordenadas();
		super.posicion = new Posicion(cordenadas[0], cordenadas[1],rangoDeInfeccion);
		super.velocidad = 2;
		super.posInicial = new int[2];
		super.posInicial[0] = posicion.getX();
		super.posInicial[1] = posicion.getY();
		super.girando = false;
		this.setBounds(posicion.getX(), posicion.getY(), 50, 50);
		actualizarImagen();
	}
	
	public void aplicarEfecto() {
		if(!efectoAplicado) {
			velocidad = velocidad *2;
			efectoAplicado = true;
		}
	}

}
