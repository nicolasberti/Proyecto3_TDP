package logica;

import entidades.*;

public class VisitorProyectil extends Visitor {

	@Override
	public boolean visit(Infectado infectado, int desinfeccion) {
		boolean esta = false;
		infectado.setCargaViral( infectado.getCargaViral()-desinfeccion );
		if(infectado.getCargaViral() <= 0) {
			esta = true;
			infectado.setJugando(false);
		}
		return esta;
	}
	

}
