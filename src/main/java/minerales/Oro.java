package minerales;

public class Oro extends Mineral {
	private static final char LETRA = 'O';
	private static int PRECIO = 300;
	private static TipoDeBloque TIPO = TipoDeBloque.ORO;
	private static String NOMBRE = "Oro";
	
	public Oro() {
		super(PRECIO,LETRA,TIPO,NOMBRE);
	}
}
