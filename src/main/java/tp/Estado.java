package tp;

import java.util.ArrayList;

import jugador.Accion;
import jugador.Interacciones;
import jugador.Jugador;

public interface Estado {
	public Estado update(ArrayList<Accion> acciones, Jugador pj, Interacciones interacciones);
}
