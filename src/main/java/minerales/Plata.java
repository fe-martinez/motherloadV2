package minerales;

public class Plata extends Mineral {
	private static final char LETRA = 'P';
	private static int PRECIO = 150;
	private static TipoDeBloque TIPO = TipoDeBloque.PLATA;
	private static String NOMBRE = "Plata";
	
	public Plata() {
		super(PRECIO,LETRA,TIPO,NOMBRE);
	}
}
