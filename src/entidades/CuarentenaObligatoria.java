package entidades;

import java.awt.Image;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import aplicacion.GUI_nueva;
import logica.AutoAlgoritmo;
import logica.Descongelar;
import logica.Juego;

public class CuarentenaObligatoria extends EfectoTemporal {

	public CuarentenaObligatoria(int x, int y) {
		Random rnd = new Random();
		super.posicion = new Posicion(rnd.nextInt(x), rnd.nextInt(y),15);
		super.velocidad = 2;
		this.setBounds(posicion.getX(), posicion.getY(), 35, 35);
		ImageIcon image = new ImageIcon(GUI_nueva.class.getResource("/img/cuarentenaObligatoria.png"));
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		this.setIcon(icon);
	}

	@Override
	public void utilizar() {
		if(!usado) {
			Juego.get().congelarInfectados();
			int duracion = 5; // Duración en segundos del efecto.
			AutoAlgoritmo habilitar = new AutoAlgoritmo(new Descongelar(), duracion, Juego.get()); 
			habilitar.start();
			usado = true;
		}
	}

}
