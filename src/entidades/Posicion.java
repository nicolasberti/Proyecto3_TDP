package entidades;

public class Posicion {

	private int x;
	private int y;
	private int r;
	
	public Posicion(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getR() { return r; }
	
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public void setR(int r) { this.r = r; }
	
	public boolean estaEnElRadio(Posicion p) {
		boolean esta = false;
			if(p.getX() >= x-30 && p.getX() <= x+30)
				if(p.getY() >= y-30 && p.getY() <= y+30)
					esta = true;
		// posible algoritmo -> deja a r sin ninguna acción de momento.
		return esta;
		
		
	}
	
}
