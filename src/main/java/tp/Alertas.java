package tp;

import java.util.HashMap;

import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alertas {
	private static HashMap<String,Alert> alertas;
	static List<String> keyAlertas = List.of("Compra exitosa",
										"Fallo compra",
										"Fallo compra mejoras",
										"Fallo compra mantenimiento",
										"Fallo cargar partida");
	
	static List<String> contentAlertas = List.of("Compra exitosa :)\r\n",
												"Dinero insuficiente \r\n No se pudo realizar la compra especificada.\r\n",
												"No se pudo realizar la compra especificada.\r\n"
												+ "Dinero insuficiente o se está intentando comprar una mejora de menor capacidad a la actual.",
												"No se pudo realizar la compra especificada.\r\n"
												+ "Dinero insuficiente o su nivel actual ya es máximo.\r\n",
						    						"No se pudo cargar la partida :(\r\n");
	
	public Alertas() {
		cargarAlertas();
	}
	
	public static void cargarAlertas() {
		alertas = new HashMap<>();
		for(int i = 0; i < keyAlertas.size(); i++) {
			alertas.put(keyAlertas.get(i),new Alert(AlertType.NONE));
			alertas.get(keyAlertas.get(i)).setContentText(contentAlertas.get(i));
		}
		
		alertas.get("Compra exitosa").setAlertType(AlertType.INFORMATION);
		alertas.get("Fallo compra").setAlertType(AlertType.ERROR);
		alertas.get("Fallo compra mejoras").setAlertType(AlertType.ERROR);
		alertas.get("Fallo compra mantenimiento").setAlertType(AlertType.ERROR);
		alertas.get("Fallo cargar partida").setAlertType(AlertType.ERROR);
		alertas.get("Fallo cargar partida").setTitle("Error al cargar");
	}
	
	public static Alert getAlerta(String alerta) {
		if(alertas.containsKey(alerta)) {
			return alertas.get(alerta);
		}
		return null;
	}
	
	public static void showAlerta(String alerta) {
		if(alertas.containsKey(alerta)) {
			alertas.get(alerta).show();
		}
	}
}
