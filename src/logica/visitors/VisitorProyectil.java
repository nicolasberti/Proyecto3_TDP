package logica.visitors;

import java.util.Random;

import GUI.GUI_juego;
import entidades.*;
import entidades.infectados.Alpha;
import entidades.premios.CuarentenaObligatoria;
import entidades.premios.RecuperarVida;
import entidades.premios.SuperArma;
import logica.Juego;
import logica.Visitor;

public class VisitorProyectil extends Visitor {

	private Proyectil proyectil;
	private GUI_juego frame;
	
	public VisitorProyectil(Proyectil proyectil, GUI_juego frame) {
		this.proyectil = proyectil;
		this.frame = frame;
	}
	@Override
	public void visit(Infectado infectado) {
		if(proyectil.habilitado()) {
			boolean esta = false;
			int desinfeccion = proyectil.getCargaDesinfeccion() / infectado.getCargaDesinfeccion();
			// Efecto extraño en Alpha.
			if(infectado.getClass().toString().endsWith("Alpha"))
				if(infectado.getCargaViral() <= 20)
					((Alpha)infectado).aplicarEfecto();
			infectado.setCargaViral( infectado.getCargaViral()- desinfeccion );
			if(infectado.getCargaViral() <= 0) {
				esta = true;
				infectado.setJugando(false);
			}
			frame.crearExplosion(proyectil.getX(), proyectil.getY());
			if(esta) {
				Juego.get().addDesinfectado();
				Premio premio = this.premioRandom(infectado.getX(), infectado.getY());
				Juego.get().getHilo().add(premio);
				Juego.get().getHilo().remover(infectado);
			}
			Juego.get().getHilo().remover(proyectil);
			proyectil.deshabilitar();
		}
	}

	@Override
	public void visit(Jugador jugador) {
		// TODO Auto-generated method stub
	
	}
	
	private Premio premioRandom(int x, int y) {
		Premio premio = null;
		Random rnd = new Random();
		int premioNum = rnd.nextInt(3);
		switch(premioNum) {
			case 0: {
				premio = new CuarentenaObligatoria(x, y);
				break;
			}
			case 1: {
				premio = new RecuperarVida(x,y);
				break;
			}
			case 2: {
				premio = new SuperArma(x,y);
				break;
			}
		}
		return premio;
	}

}
