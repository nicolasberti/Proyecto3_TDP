package logica.visitors;

import entidades.*;
import logica.Visitor;

public class VisitorInfectado extends Visitor {

	private Infectado infectado;
	
	public VisitorInfectado(Infectado infectado) {
		this.infectado = infectado;
	}
	
	@Override
	public boolean visit(Jugador jugador) {
		boolean esta = false;
		jugador.setCargaViral( jugador.getCargaViral() + infectado.getDanio());
		if(jugador.getCargaViral() >= 100) {
			esta = true;
		}
		return esta;
	}

	@Override
	public boolean visit(Infectado infectado) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
