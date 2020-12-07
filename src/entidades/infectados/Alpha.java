package entidades.infectados;

import java.util.Random;

import entidades.Infectado;
import entidades.Posicion;
import logica.Juego;
import logica.Mapa;
@SuppressWarnings("serial")
public class Alpha extends Infectado {

	private boolean efectoAplicado = false;
	
	public Alpha(int nivel) {
		super.nivel = nivel;
		Mapa mapa = Juego.get().getMapa();
		Random rnd = new Random();
		super.rangoDeInfeccion = 20;
		super.cargaDesinfeccion = 4;
		super.danio = 4;
		super.posicion = new Posicion(rnd.nextInt(mapa.getX()), rnd.nextInt(mapa.getY()/100),rangoDeInfeccion);
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
