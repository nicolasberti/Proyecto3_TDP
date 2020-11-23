package entidades;

import java.awt.Image;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import aplicacion.GUI;
import logica.Visitor;

public class Beta extends Infectado {

	public Beta() {
		Random rnd = new Random();
		super.posicion = new Posicion(rnd.nextInt(500), rnd.nextInt(100),3);
		super.cargaViral = 15;
		super.velocidad = 1;
		super.posInicial = new int[2];
		super.posInicial[0] = posicion.getX();
		super.posInicial[1] = posicion.getY();
		
		this.setBounds(posicion.getX(), posicion.getY(), 70, 70);
		ImageIcon jugadorImg = new ImageIcon(GUI.class.getResource("/img/infectadoBeta.gif"));
		Icon jugadorIcon = new ImageIcon(jugadorImg.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		this.setIcon(jugadorIcon);
	}

	@Override
	public void desaparecer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void congelar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
		
	}
}
