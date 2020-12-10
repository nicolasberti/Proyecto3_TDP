package logica.visitors;

import entidades.*;
import logica.Juego;
import logica.Visitor;

public class VisitorInfectado extends Visitor {

	private Infectado infectado;
	
	public VisitorInfectado(Infectado infectado) {
		this.infectado = infectado;
	}
	
	@Override
	public void visit(Jugador jugador) {
		boolean esta = false;
		jugador.setCargaViral( jugador.getCargaViral() + infectado.getDanio());
		if(jugador.getCargaViral() >= 100) {
			esta = true;
		}
		if(esta)
			Juego.get().getHilo().perder();
	}

	@Override
	public void visit(Infectado infectado) {
		
	}
	

}
