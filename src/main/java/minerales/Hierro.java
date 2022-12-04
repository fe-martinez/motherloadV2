package minerales;

public class Hierro extends Mineral {
	private static final char LETRA = 'H';
	private static int PRECIO = 50;
	private static TipoDeBloque TIPO = TipoDeBloque.HIERRO;
	private static String NOMBRE = "Hierro";
	
	public Hierro() {
		super(PRECIO,LETRA,TIPO,NOMBRE);
	}
}
