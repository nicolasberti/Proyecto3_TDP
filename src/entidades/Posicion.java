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
	
	public void actualizar(Entidad e) {
		e.setBounds(x, y, 70, 70);
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getR() { return r; }
	
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public void setR(int r) { this.r = r; }
	
}
