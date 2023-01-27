package minerales;

public class Bronce extends Mineral {
	private static final char LETRA = 'B';
	private static int PRECIO = 60;
	private static TipoDeBloque TIPO = TipoDeBloque.BRONCE;
	private static String NOMBRE = "Bronce";
	
	public Bronce() {
		super(PRECIO,LETRA,TIPO,NOMBRE);
	}
}
