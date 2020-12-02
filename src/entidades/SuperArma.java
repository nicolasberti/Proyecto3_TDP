package entidades;

import java.awt.Image;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import aplicacion.GUI_nueva;
import logica.*;

public class SuperArma extends EfectoTemporal {

	public SuperArma(int x, int y) {
		Random rnd = new Random();
		super.posicion = new Posicion(rnd.nextInt(x), rnd.nextInt(y),15);
		super.velocidad = 4;
		this.setBounds(posicion.getX(), posicion.getY(), 35, 35);
		ImageIcon image = new ImageIcon(GUI_nueva.class.getResource("/img/superArma.png"));
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
