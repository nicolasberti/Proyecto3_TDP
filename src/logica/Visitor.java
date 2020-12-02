package logica;

import entidades.*;

public abstract class Visitor {

	// Los métodos que retornan boleanos es porque, retornan TRUE si la Entidad muere. FALSE en caso contrario.
	// Morir es abstracto, es decir, el infectado "muere" al ser desinfectado, y el jugador, al ser infectado al 100%.
	
	public abstract boolean visit(Infectado infectado);
	public abstract boolean visit(Jugador jugador);
}
