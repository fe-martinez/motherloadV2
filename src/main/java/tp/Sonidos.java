package tp;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javafx.scene.media.AudioClip;

public class Sonidos {
static HashMap<String,AudioClip> sonidos;
	
	static List<String> pathSonidos = List.of("src/rsc/Sonidos/Taladro.wav",
									"src/rsc/Sonidos/Helicoptero.wav",
									"src/rsc/Sonidos/Golpe2.wav",
									"src/rsc/Sonidos/Muerte2.wav",
									"src/rsc/Sonidos/Comprar.wav",
									"src/rsc/Sonidos/Vender.mp3",
									"src/rsc/Sonidos/Alerta2.wav",
									"src/rsc/Sonidos/Teleport2.wav",
									"src/rsc/Sonidos/Ready1.wav",
									"src/rsc/Sonidos/PowerUp1.wav",
									"src/rsc/Sonidos/Click.wav",
									"src/rsc/Sonidos/GameOver9.wav",
									"src/rsc/Sonidos/Menu.mp3",
									"src/rsc/Sonidos/Jugando.mp3",
									"src/rsc/Sonidos/LostGame.mp3",
									"src/rsc/Sonidos/WinGame.mp3",
									"src/rsc/Sonidos/Tiendas.mp3",
									"src/rsc/Sonidos/Error.mp3",
									"src/rsc/Sonidos/Landed.wav",
									"src/rsc/Sonidos/Credits.mp3",
									"src/rsc/Sonidos/musica-historia.mp3",
									"src/rsc/Sonidos/suspenso-2.mp3",
									"src/rsc/Sonidos/sad.mp3",
									"src/rsc/Sonidos/audio-explosion.mp3");
	
	static List<String> keySonidos = List.of("Taladro",
									"Helicoptero",
									"Golpe",
									"Muerte",
									"Comprar",
									"Vender",
									"Alerta",
									"Teleport",
									"Get ready",
									"PowerUp",
									"Elegir opcion",
									"Game over",
									"Menu",
									"Jugando",
									"Lost Game",
									"Win Game",
									"Tiendas",
									"Error",
									"Landed",
									"Credits",
									"Inicio",
									"Suspenso",
									"Sad",
									"Explosion");
	public Sonidos() {
		cargarSonidos();
	}
	
	public static void cargarSonidos() {
		sonidos = new HashMap<>();
		for(int i = 0; i < pathSonidos.size(); i++) {
			sonidos.put(keySonidos.get(i),new AudioClip(new File(pathSonidos.get(i)).toURI().toString()));
		}
	}
	
	public static void setVolumenGeneral(double volumen) {
		sonidos.forEach((key, value) -> value.setVolume(volumen));
		sonidos.get("Jugando").setVolume(0.1);
	}

	
	public static void reproducir(String sonido) {
		if(sonidos.containsKey(sonido) && !sonidos.get(sonido).isPlaying()) {
			sonidos.get(sonido).play();
		}
	}
	
	public static void stopReproduccion(String sonido) {
		if(sonidos.containsKey(sonido) && sonidos.get(sonido).isPlaying()) {
			sonidos.get(sonido).stop();
		}
	}
	
	public static void setVolumen(String sonido,double valor) {
		if(sonidos.containsKey(sonido) && sonidos.get(sonido).isPlaying()) {
			sonidos.get(sonido).setVolume(valor);
		}
	}
	
	public static boolean existe(String sonido) {
		return sonidos.containsKey(sonido);
	}
	
	public static boolean isPlaying(String sonido) {
		if(sonidos.containsKey(sonido)) {
			return sonidos.get(sonido).isPlaying();
			
		}
		return false;
	}


}
