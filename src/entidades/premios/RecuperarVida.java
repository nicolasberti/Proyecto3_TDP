package entidades.premios;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import entidades.Jugador;
import entidades.Posicion;
import logica.*;
@SuppressWarnings("serial")
public class RecuperarVida extends ObjetoPreciso {

	public RecuperarVida(int x, int y) {
		super.posicion = new Posicion(x,y,30);
		super.velocidad = 4;
		this.setBounds(posicion.getX(), posicion.getY(), 30, 40);
		ImageIcon image = new ImageIcon(this.getClass().getResource("/img/pocion.png"));
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

	@Override
	public void accept(Visitor visit) {
		// TODO Auto-generated method stub
 
	}

	@Override
	public void disparar() {
		// TODO Auto-generated method stub
		
	}


}
