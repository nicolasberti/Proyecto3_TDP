package entidades;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import aplicacion.GUI;
import logica.Visitor;

public class Particula extends Entidad {

	
	public Particula(int velocidad) {
		// Cordenadas preterminadas
		super.posicion = new Posicion(250,250,5);
		super.velocidad = velocidad; // Velocidad según el arma que la dispara
				
		this.setBounds(posicion.getX(), posicion.getY(), 70, 70);
		ImageIcon jugadorImg = new ImageIcon(GUI.class.getResource("/img/disparo.png"));
		Icon jugadorIcon = new ImageIcon(jugadorImg.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		this.setIcon(jugadorIcon);
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
