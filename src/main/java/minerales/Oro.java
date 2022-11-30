package minerales;

public class Oro extends Mineral {
	private static final char LETRA = 'O';
	private static int PRECIO = 300;
	private static TipoDeBloque TIPO = TipoDeBloque.ORO;
	
	public Oro() {
		super(Oro.PRECIO,Oro.LETRA, Oro.TIPO);
	}
}
