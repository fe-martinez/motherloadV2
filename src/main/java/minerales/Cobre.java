package minerales;

public class Cobre extends Mineral {
	private static final char LETRA = 'C';
	private static int PRECIO = 30;
	private static TipoDeBloque TIPO = TipoDeBloque.COBRE;
	
	public Cobre() {
		super(Cobre.PRECIO,Cobre.LETRA, Cobre.TIPO);
	}
}
