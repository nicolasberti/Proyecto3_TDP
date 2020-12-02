package logica;

import entidades.*;

public class VisitorProyectil extends Visitor {

	private Proyectil proyectil;
	
	public VisitorProyectil(Proyectil proyectil) {
		this.proyectil = proyectil;
	}
	@Override
	public boolean visit(Infectado infectado) {
		boolean esta = false;
		int desinfeccion = proyectil.getCargaDesinfeccion() / infectado.getCargaDesinfeccion();
		infectado.setCargaViral( infectado.getCargaViral()- desinfeccion );
		if(infectado.getCargaViral() <= 0) {
			esta = true;
			infectado.setJugando(false);
		}
		return esta;
	}

	@Override
	public boolean visit(Jugador jugador) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
