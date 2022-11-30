package terreno;
import jugador.Posicion;

public class Suelo {
	private Bloque[][] bloques;
	private int alto;
	private int ancho;

	public Suelo(int ancho, int alto) {
		if(alto < 0 || ancho < 0) {
			return; //Excepcion.
		}
		this.alto = alto;
		this.ancho = ancho;
		this.bloques = FabricaDeSuelo.crear(alto, ancho);
	}
	
	//Devuelve el bloque de la posicion dada
	public Bloque getBloque(Posicion pos) {
		return(bloques[(int)pos.getY()][(int)pos.getX()]);
	}
	
	
	//Destruye el bloque de la posicion dada
	public boolean destruirBloque(Posicion pos) {
		if(bloques[(int)pos.getY()][(int)pos.getX()].getBloqueID() != ' ') {
			bloques[(int)pos.getY()][(int)pos.getX()] = new Aire();
			return true;
		}
		return false;
	}
	
	//Devuelve true si el casillero de la posicion dada está vacío y false en caso contrario.
	public boolean casilleroVacio(Posicion posicion) {
		if(posicion.getY() == this.alto - 1) {
			return false;
		}
		
		return(bloques[(int)posicion.getY()][(int)posicion.getX()].getBloqueID() == ' ');
	}

	public int getAlto() {
		return this.alto;
	}
}
