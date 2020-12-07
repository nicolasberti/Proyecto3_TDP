package entidades.premios;

import java.awt.Image;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import GUI.GUI;
import entidades.Jugador;
import entidades.Posicion;
import logica.*;
@SuppressWarnings("serial")
public class RecuperarVida extends ObjetoPreciso {

	public RecuperarVida(int x, int y) {
		Random rnd = new Random();
		super.posicion = new Posicion(rnd.nextInt(x), rnd.nextInt(y),15);
		super.velocidad = 4;
		this.setBounds(posicion.getX(), posicion.getY(), 35, 35);
		ImageIcon image = new ImageIcon(GUI.class.getResource("/img/pocion.png"));
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		this.setIcon(icon);
	}

	@Override
	public void utilizar() {
		if(!usado) {
			Jugador jugador = Juego.get().getJugador();
			jugador.setCargaViral(jugador.getCargaViral()/2);
			usado = true;
		}
	}


}
