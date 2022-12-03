package tiendas;

import java.util.Scanner;

public class VistaTiendasConsola {
	private static final char RESPUESTA_AFIRMATIVA = 'S';
	
	public static int nafta() {
		System.out.println("---------------------------");
		System.out.println("Indique cuanto quiere cargar: 5, 10, 25, 50 (Litros)");
		System.out.println("o indique 100 para llenar el tanque.");
		System.out.println("Cantidad: ");
		
		Scanner scanner = new Scanner(System.in);
		int cantidad = scanner.nextInt();
		scanner.close();
		return cantidad;
	}
	
	public static int repair(int vidaActual) {
		System.out.println("-----------------------------------");
		System.out.println("Su vida actual es " + vidaActual);
		System.out.println("Valor de la reparacion: ");
		System.out.println("50, 100, 200, 500 o 1000(Reparacion completa)");
		System.out.print("Opcion: ");
		
		Scanner scanner = new Scanner(System.in);
		int cantidadPlata = scanner.nextInt();
		scanner.close();
		
		return cantidadPlata;
	}
	
	public static boolean venta() {
		System.out.println("-----------------------------------");
		System.out.println("Desea vender?");
		Scanner scanner = new Scanner(System.in);
		char respuesta = scanner.next().charAt(0);
		scanner.close();
		if(respuesta == RESPUESTA_AFIRMATIVA) return true;
		
		return false;
	}
	
	public static char consumibles() {
		System.out.println("---------------------------------------");
		System.out.println("Consumible a comprar: ");
		System.out.println("D para dinamita, E para explosivos, R para HullRepair, X para tanque extra, T para tp");
		System.out.print("Opcion: ");
		
		Scanner sc = new Scanner(System.in);
		char opcion = sc.next().charAt(0);
		sc.close();
		return opcion;
	}
	
	private static String codigoMejora(char opcion, int tier) {
		String busqueda = "";
		busqueda += opcion;
		busqueda += tier;
		return busqueda;
	}
	
	public static String mejoras() {
		System.out.println("----------------------------------------");
		System.out.println("Mejora a comprar:");
		System.out.println("I para mejoras del inventario\nV para mejoras de vida\nT para mejoras de tanque");
		System.out.print("Opcion: ");
		
		Scanner scanner = new Scanner(System.in);
		char opcion = scanner.next().charAt(0);
		scanner.close();
		
		if(opcion == 'I') {
			System.out.println("Seleccione el tier de mejora: ");
			System.out.println("(1)+15 por $750\n(2)+25 por $2000\n(3)+40 por $5000\n(4)+60 por $20000\n(5)+70 por $100000\n(6)+120 por $150000");
			System.out.print("Opcion: ");
		} else if(opcion == 'V') {
			System.out.println("Seleccione el tier de mejora: ");
			System.out.println("(1)+17 por $750\n(2)+30por $2000\n(3)+50 por $5000\n(4)+80 por $20000\n(5)+120 por $100000\n(6)+180 por $150000");
			System.out.print("Opcion: ");
		} else if(opcion == 'T') {
			System.out.println("Seleccione el tier de mejora: ");
			System.out.println("(1)+15 por $750\n(2)+25 por $2000\n(3)+40 por $5000\n(4)+60 por $20000\n(5)+70 por $100000\n(6)+120 por $150000");
			System.out.print("Opcion: ");
		} else {
			System.out.println("Todavia no tenemos ese item a la venta :P");
		}
		
		int tier = scanner.nextInt();
		String codigo = codigoMejora(opcion, tier);
		
		return codigo;
	}
	
}
