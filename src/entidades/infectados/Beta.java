package entidades.infectados;

import java.util.Random;

import entidades.Infectado;
import entidades.Posicion;
import logica.Juego;
import logica.Mapa;
@SuppressWarnings("serial")
public class Beta extends Infectado {

	public Beta(int nivel) {
		super.nivel = nivel;
		Mapa mapa = Juego.get().getMapa();
		Random rnd = new Random();
		super.rangoDeInfeccion = 30;
		super.cargaDesinfeccion = 2;
		super.danio = 2;
		super.posicion = new Posicion(rnd.nextInt(mapa.getX()), rnd.nextInt(mapa.getY()/100),rangoDeInfeccion);
		super.velocidad = 1;
		super.posInicial = new int[2];
		super.posInicial[0] = posicion.getX();
		super.posInicial[1] = posicion.getY();
		super.girando = false;
		this.setBounds(posicion.getX(), posicion.getY(), 50, 50);
		actualizarImagen();
	}

}
