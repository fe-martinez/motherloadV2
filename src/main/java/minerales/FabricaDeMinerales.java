package minerales;

import java.util.HashMap;
import java.util.Map;

public class FabricaDeMinerales {
	
	static Map<String, Mineral> instancias = new HashMap<String, Mineral>();
	
	//Crea el Mineral especificado según el String recibido.
	public static Mineral crear(String tipoMineral) {
		var mineral = instancias.get(tipoMineral);
		if(mineral == null) {
			if(tipoMineral == "Cobre") {
				mineral = new Cobre();
				
			}
			else if(tipoMineral == "Bronce") {
				mineral = new Bronce();
			}
			else if(tipoMineral == "Hierro") {
				mineral = new Hierro();
			}
			else if(tipoMineral == "Plata") {
				mineral = new Plata();
			}
			else if(tipoMineral == "Oro") {
				mineral = new Oro();
			}
			else if(tipoMineral == "Diamante") {
				mineral = new Diamante();
			} else {
				return null;
			}
			instancias.put(tipoMineral, mineral);
		}
		return mineral;
	}
}
