package entidades;

import java.awt.Image;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import aplicacion.GUI_nueva;
import logica.Visitor;

public class Alpha extends Infectado {

	public Alpha() {
		Random rnd = new Random();
		super.posicion = new Posicion(rnd.nextInt(500), rnd.nextInt(100),3);
		super.cargaViral = 10;
		super.velocidad = 2;
		super.posInicial = new int[2];
		super.posInicial[0] = posicion.getX();
		super.posInicial[1] = posicion.getY();
		
		this.setBounds(posicion.getX(), posicion.getY(), 70, 70);
		ImageIcon jugadorImg = new ImageIcon(GUI_nueva.class.getResource("/img/infectadoAlpha.gif"));
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
