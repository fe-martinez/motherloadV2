package minerales;

public class Hierro extends Mineral {
	private static final char LETRA = 'H';
	private static int PRECIO = 50;
	private static TipoDeBloque TIPO = TipoDeBloque.HIERRO;
	
	public Hierro() {
		super(Hierro.PRECIO,Hierro.LETRA, Hierro.TIPO);
	}
}
