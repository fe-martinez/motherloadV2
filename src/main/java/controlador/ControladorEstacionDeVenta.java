package controlador;

import java.util.ArrayList;

import java.util.List;

import javafx.scene.control.Label;
import jugador.Jugador;
import tiendas.EstacionDeVenta;
import tp.Sonidos;
import vistas.VistaEstacionDeVenta;

public class ControladorEstacionDeVenta {
	EstacionDeVenta tienda;
	VistaEstacionDeVenta vista;
	Jugador pj;
	List<Label> labels;
	
	public ControladorEstacionDeVenta(EstacionDeVenta tienda, VistaEstacionDeVenta vista,Jugador pj) {
		this.tienda = tienda;
		this.vista = vista;
		this.pj = pj;
	}
	
	public void vender() {
		vista.registrarEscuchaVenta(e ->
		{
			if(pj.getInventario().getCantidadDeMinerales() > 0) {
				tienda.vender(pj);
				this.actualizarVista();
	    		Sonidos.reproducir("Vender");
			}
		});

	}
	
	public void cerrarTienda() {
		vista.registrarEscuchaClose(e ->
		{
			vista.cerrar();
		    Sonidos.stopReproduccion("Tiendas");
	        Sonidos.reproducir("Jugando");
		});
	}
	
	public void actualizarVista() {
		labels = new ArrayList<>();
		if(!this.pj.inventarioVacio()) {
			int contador = 1;
			int i = 0;
			while(i < this.pj.getInventario().getCantidadDeMinerales()-1) {
				if(this.pj.getInventario().getTipoDeMineral(i) == this.pj.getInventario().getTipoDeMineral(i+1)) {
					contador++;
				}
				else {
					labels.add(new Label(this.pj.getInventario().getNombreMineral(i) + " " + contador + " X $ " + this.pj.getInventario().getPrecioMineral(i)+ " = $" + this.pj.getInventario().getPrecioMineral(i) * contador));
					contador = 1;
				}
				i++;
			}
			labels.add(new Label(this.pj.getInventario().getNombreMineral(i) + " " + contador + " X $ " + this.pj.getInventario().getPrecioMineral(i)+ " = $" + this.pj.getInventario().getPrecioMineral(i) * contador));
		}
		else {
			labels.add(new Label("Inventario vacío :("));
		}
		vista.actualizarVista(labels);
	}

}
