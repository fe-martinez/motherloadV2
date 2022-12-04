package algo3.motherloadV2;

import java.io.File;

import javafx.scene.media.AudioClip;

public class Sonidos {
	AudioClip sonidoTaladro;
	AudioClip sonidoHelicoptero;

	public Sonidos() {
		this.sonidoTaladro = new AudioClip(new File("../motherloadV2/src/rsc/Sonidos/Taladro.wav").toURI().toString());
		this.sonidoHelicoptero = new AudioClip(new File("../motherloadV2/src/rsc/Sonidos/Helicoptero.wav").toURI().toString());
	}
	
	
	public void reproducirSonidoTaladro() {
		if(!sonidoTaladro.isPlaying()) {
			sonidoTaladro.play();			
		}
	}
	
	public void pararSonidoTaladro() {
		sonidoTaladro.stop();
	}
	
	public void reproducirSonidoHelicoptero() {
		if(!sonidoHelicoptero.isPlaying()) {
			sonidoHelicoptero.play();			
		}
	}
	
	public void pararSonidoHelicoptero() {
		sonidoHelicoptero.stop();
	}
	
}
