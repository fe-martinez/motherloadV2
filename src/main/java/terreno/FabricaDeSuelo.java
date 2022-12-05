package terreno;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import minerales.FabricaDeMinerales;

public class FabricaDeSuelo {
	
	private static Map<String, Bloque> instancias;

	private static Bloque ponerBloque(int altura, int maxAlto, Map<String, Bloque> instancias) {
		int rangoTierra, rangoHierro, rangoCobre, rangoBronce, rangoOro;
		if(altura < maxAlto/5) {
			rangoTierra = 875;
			rangoHierro = 930;
			rangoCobre = 975;
			rangoBronce = 990;
			rangoOro = 998;
		} else if(altura < maxAlto/4) {
			rangoTierra = 830;
			rangoHierro = 890;
			rangoCobre = 935;
			rangoBronce = 978;
			rangoOro = 995;
		} else if(altura < maxAlto/3) {
			rangoTierra = 790;
			rangoHierro = 850;
			rangoCobre = 900;
			rangoBronce = 950;
			rangoOro = 980;
		} else if(altura < maxAlto/2) {
			rangoTierra = 790;
			rangoHierro = 850;
			rangoCobre = 900;
			rangoBronce = 950;
			rangoOro = 980;
		} else{
			rangoTierra = 700;
			rangoHierro = 750;
			rangoCobre = 830;
			rangoBronce = 900;
			rangoOro = 960;
		}
		
		var rand = new Random();
		int valor = rand.nextInt(1000);
		if(valor > 0 && valor < rangoTierra) {
			var tierra = instancias.get("Tierra");
			if(tierra == null) {
				tierra = new Tierra();
				instancias.put("Tierra", tierra);
			}
			return tierra;
		} else if(valor >= rangoTierra && valor < rangoHierro) {
			var hierro = instancias.get("Hierro");
			if(hierro == null) {
				hierro = FabricaDeMinerales.crear("Hierro");
				instancias.put("Hierro", hierro);
			}
			return hierro;
		} else if(valor >= rangoHierro && valor < rangoCobre) {
			var cobre = instancias.get("Cobre");
			if(cobre == null) {
				cobre = FabricaDeMinerales.crear("Cobre");
				instancias.put("Cobre", cobre);
			}
			return cobre;
		} else if(valor >= rangoCobre && valor < rangoBronce) {
			var bronce = instancias.get("Bronce");
			if(bronce == null) {
				bronce = FabricaDeMinerales.crear("Bronce");
				instancias.put("Bronce", bronce);
			}
			return bronce;
		} else if(valor >= rangoBronce && valor < rangoOro) {
			var oro = instancias.get("Oro");
			if(oro == null) {
				oro = FabricaDeMinerales.crear("Oro");
				instancias.put("Oro", oro);
			}
			return oro;
		} else {
			var diamante = instancias.get("Diamante");
			if(diamante == null) {
				diamante = FabricaDeMinerales.crear("Diamante");
				instancias.put("Diamante", diamante);
			}
			return diamante;
		}
	}
	
	
	public static Bloque[][] crear(int alto, int ancho) {
		
		instancias = new HashMap<String, Bloque>();
		
		var bloques = new Bloque[alto][ancho];
		for(int k = 0; k < ancho; k++) {
			bloques[0][k] = new Aire();
			bloques[1][k] = new Aire();
			bloques[2][k] = new Aire();
			bloques[3][k] = new Aire();
			bloques[4][k] = new Aire();
			bloques[5][k] = new Aire();
			bloques[6][k] = new Aire();
			bloques[7][k] = new Aire();
			bloques[8][k] = new Aire();
			bloques[9][k] = new Tierra();
		}
		for(int i = 10; i < alto; i++) {
			for(int j = 0; j < ancho; j++) {
				bloques[i][j] = ponerBloque(i, alto, instancias);
			}
		}
		return bloques;
	}


	private static Bloque convertirChar(char id) {
		if(id == 'T') {
			return new Tierra();
		} else if(id == ' ') {
			return new Aire();
		} else if(id == 'B') {
			return FabricaDeMinerales.crear("Bronce");
		} else if(id == 'H') {
			return FabricaDeMinerales.crear("Hierro");
		} else if(id == 'P') {
			return FabricaDeMinerales.crear("Plata");
		} else if(id == 'O') {
			return FabricaDeMinerales.crear("Oro");
		} else if(id == 'C') {
			return FabricaDeMinerales.crear("Cobre");
		} else if(id == 'D') {
			return FabricaDeMinerales.crear("Diamante");
		}
		return new Tierra();
	}


	public static Bloque[][] crearDesdeChars(int alto, int ancho, char[][] mapa){
		var bloques = new Bloque[alto][ancho];

		for(int i = 0; i < alto; i++) {
			for(int j = 0; j < ancho; j++) {
				bloques[i][j] = convertirChar(mapa[i][j]);
			}
		}

		return bloques;
	}
}
