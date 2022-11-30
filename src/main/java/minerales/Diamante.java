package minerales;

public class Diamante extends Mineral{
	private static final char LETRA = 'D';
	private static int PRECIO = 650;
	private static TipoDeBloque TIPO = TipoDeBloque.DIAMANTE;
	
	public Diamante() {
		super(Diamante.PRECIO,Diamante.LETRA, Diamante.TIPO);
	}
}
