package entidades.premios;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import entidades.Posicion;
import logica.AutoAlgoritmo;
import logica.Juego;
import logica.Visitor;
import logica.algoritmos.Descongelar;
@SuppressWarnings("serial")
public class CuarentenaObligatoria extends EfectoTemporal {

	
	public CuarentenaObligatoria(int x, int y) {
		super.posicion = new Posicion(x,y,30);
		super.velocidad = 2;
		this.setBounds(posicion.getX(), posicion.getY(), 35, 35);
		ImageIcon image = new ImageIcon(this.getClass().getResource("/img/cuarentenaObligatoria.png"));
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		this.setIcon(icon);
	}

	@Override
	public void utilizar() {
		if(!usado) {
			Juego.get().congelarInfectados();
			int duracion = 5; // Duración en segundos del efecto.
			AutoAlgoritmo habilitar = new AutoAlgoritmo(new Descongelar(Juego.get().getNivelActual(), Juego.get().getTandaActual()), duracion, Juego.get()); 
			habilitar.start();
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
