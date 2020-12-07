package logica.visitors;

import entidades.*;
import entidades.infectados.Alpha;
import logica.Visitor;

public class VisitorProyectil extends Visitor {

	private Proyectil proyectil;
	
	public VisitorProyectil(Proyectil proyectil) {
		this.proyectil = proyectil;
	}
	@Override
	public boolean visit(Infectado infectado) {
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
		return esta;
	}

	@Override
	public boolean visit(Jugador jugador) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
