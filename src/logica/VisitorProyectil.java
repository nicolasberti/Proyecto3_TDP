package logica;

import entidades.*;

public class VisitorProyectil extends Visitor {

	@Override
	public boolean visit(Infectado infectado, int cargaViral) {
		boolean esta = false;
		infectado.setCargaViral( infectado.getCargaViral()-cargaViral );
		if(infectado.getCargaViral() <= 0) {
			esta = true;
			infectado.setJugando(false);
		}
		return esta;
	}

	@Override
	public boolean visit(Jugador jugador, int cargaViral) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
