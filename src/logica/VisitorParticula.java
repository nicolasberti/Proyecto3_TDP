package logica;

import entidades.Infectado;
import entidades.Jugador;
import entidades.Particula;

public class VisitorParticula extends Visitor {

	@Override
	public void visit(Particula particula) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Jugador jugador) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Infectado infectado) {
		// Pruebas
		System.out.println("Una particula visitó a un Infectado.");
	}

}
