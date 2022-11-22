package terreno;

import jugador.Jugador;

import java.util.Scanner;

import jugador.Inventario;
import jugador.Posicion;
import mejoras.Usable;
import minerales.Mineral;

public class Vista {
	private PisoSuperior tiendas;
	private Suelo suelo;
	private Jugador pj;
	private Inventario inventario;
	private int ancho;
	private int alto;
	private Scanner sc;

	public Vista(PisoSuperior tiendas, Suelo suelo, Jugador pj, Inventario inventario, int ancho, int alto) {
		if(tiendas == null || suelo == null || pj == null) {
			//throw an exception
		}
		this.tiendas = tiendas;
		this.suelo = suelo;
		this.pj = pj;
		this.inventario = inventario;
		this.ancho = ancho;
		this.alto = alto;
	}

	//Imprime el terreno y los datos del jugador recibido.
	public void imprimir(Jugador pj) {
		int x = (int)pj.getX();
		int y = (int)pj.getY();
		
		for(int i = 0; i < this.ancho; i++) {
			System.out.print(' ');
			if(pj.getPosicion().getX() == i && pj.getPosicion().getY() == 0) {
				System.out.print(this.pj.getBloqueID());
			} else{
				Entidad tiendaActual = tiendas.getTiendaPos(i);
				if(tiendaActual != null) {
					System.out.print(tiendaActual.getEntidadID());
				} else {
					System.out.print(' ');
				}
			}
			System.out.print(' ');
		}
		System.out.print('\n');
		
		for(int i = 1; i < this.alto; i++) {
			for(int j = 0; j < this.ancho; j++) {
				if(x > 0 && (j == x && i == y)) {
					System.out.print(' ');
					System.out.print(pj.getBloqueID());
					System.out.print(' ');
				} else {
					System.out.print(' ');
					System.out.print(suelo.getBloque(new Posicion(j, i)).getBloqueID());
					System.out.print(' ');
				}
			}
			System.out.print("\n");
		}
		System.out.print('\n');
		
		System.out.print("Nivel de nafta: " + String.format("%.02f", pj.getNave().getNivelDeCombustible()));
		System.out.println("\t\t Nivel de vida: " + pj.getNave().getHP());
		System.out.println("Dinero: $" + pj.getDinero());
		System.out.print("Minerales recolectados: ");
		for(Mineral actual: inventario.getMinerales()) {
			System.out.print('|');
			System.out.print(actual.getBloqueID());
			System.out.print('|');
		}
		System.out.print('\n');
		
		System.out.print("Consumibles en el inventario: ");
		for(Usable actual: inventario.getUsables()) {
			System.out.print('|');
			System.out.print(actual.getMejoraID());
			System.out.print('|');
		}
		System.out.print('\n');
	}
	
	public int tiendasPorConsola(String llamado) {
		if(llamado == "nafta") {
			System.out.println("---------------------------");
			System.out.println("Indique cuanto quiere cargar: 5, 10, 25, 50 (Litros)");
			System.out.println("o indique 100 para llenar el tanque.");
			System.out.println("Cantidad: ");
			this.sc = new Scanner(System.in);
			int cantidad = sc.nextInt();
			return cantidad;
		}
		return -1;
	}
}
