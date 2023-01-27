package minerales;

public class Cobre extends Mineral {
	private static final char LETRA = 'C';
	private static int PRECIO = 30;
	private static TipoDeBloque TIPO = TipoDeBloque.COBRE;
	private static String NOMBRE = "Cobre";
	
	public Cobre() {
		super(PRECIO,LETRA,TIPO,NOMBRE);
	}
}
