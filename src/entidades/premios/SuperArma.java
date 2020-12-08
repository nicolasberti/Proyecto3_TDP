package entidades.premios;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import entidades.Jugador;
import entidades.Posicion;
import entidades.armas.ArmaDuplicada;
import logica.*;
import logica.algoritmos.DevolverArma;
@SuppressWarnings("serial")
public class SuperArma extends EfectoTemporal {

	public SuperArma(int x, int y) {
		super.posicion = new Posicion(x,y,30);
		super.velocidad = 4;
		this.setBounds(posicion.getX(), posicion.getY(), 35, 35);
		ImageIcon image = new ImageIcon(this.getClass().getResource("/img/superArma.png"));
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		this.setIcon(icon);
	}

	@Override
	public void utilizar() {
		if(!usado) {
			Jugador jugador = Juego.get().getJugador();
			int duracion = 7; // Duración en segundos del efecto.
			AutoAlgoritmo habilitar = new AutoAlgoritmo(new DevolverArma(jugador.getArma()), duracion, jugador); 
			jugador.setArma(new ArmaDuplicada());
			habilitar.start();
			usado = true;
		}
	}
	

}
