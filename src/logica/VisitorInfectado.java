package logica;

import entidades.*;

public class VisitorInfectado extends Visitor {

	@Override
	public boolean visit(Jugador jugador, int cargaViral) {
		boolean esta = false;
		jugador.setCargaViral( jugador.getCargaViral() + cargaViral);
		if(jugador.getCargaViral() >= 100) {
			esta = true;
		}
		return esta;
	}

	@Override
	public boolean visit(Infectado infectado, int cargaViral) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
