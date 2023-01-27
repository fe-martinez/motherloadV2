package minerales;

public class Diamante extends Mineral{
	private static final char LETRA = 'D';
	private static int PRECIO = 650;
	private static TipoDeBloque TIPO = TipoDeBloque.DIAMANTE;
	private static String NOMBRE = "Diamante";
	
	public Diamante() {
		super(PRECIO,LETRA,TIPO,NOMBRE);
	}
}
