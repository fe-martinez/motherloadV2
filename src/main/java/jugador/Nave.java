package jugador;

public class Nave {
	private static final int COMBUSTIBLE_INICIAL = 6;
	private static final int MAX_COMBUSTIBLE_INICIAL = 10;
	private static final int HP_INICIAL = 10;
	private static final int MAX_HP_INICIAL = 10;
	
	private int hp;
	private double nivelCombustible;
	private double capacidadTanque;
	private int maxHP;
	
	public Nave() {
		this.nivelCombustible = COMBUSTIBLE_INICIAL;
		this.capacidadTanque = MAX_COMBUSTIBLE_INICIAL;
		this.hp = HP_INICIAL;
		this.maxHP = MAX_HP_INICIAL;
	}
	
	//Devuelve true si el nivel de combustible es menor o igual que 0.
	public boolean seQuedoSinCombustible() {
		return this.getNivelDeCombustible() <= 0;
	}
	
	//Devuelve el nivel de combustible actual.
	public double getNivelDeCombustible() {
		return this.nivelCombustible;
	}

	//Devuelve la capacidad máxima del tanque actual.
	public double getCapacidadTanque() {
		return this.capacidadTanque;
	}
	
	//Permite aumentar la cantidad de combustible.
	public void setNivelDeCombustible(double cantidad) {
		if(cantidad < 0) {
			return;
		}
		if(cantidad > this.capacidadTanque) {
			this.nivelCombustible = capacidadTanque;
		}
		
		this.nivelCombustible = cantidad;
	}
	
	//Permite cargar una cantidad de combustible deseada por la cantidad de plata especificada.
	public boolean cargarCombustible(double cantidadCombustible, double cantidadDePlata) {
		if(this.tanqueLleno()) {
			return false;
		}
		this.nivelCombustible += cantidadCombustible;
		if(this.nivelCombustible > this.capacidadTanque) {
			this.nivelCombustible = this.capacidadTanque;
		}
		
		return true;
	}
	
	//Aumenta la capacidad máxima del tanque, seteando el nivel actual al máximo.
	public void agregarCapacidadAlTanque(double cantidad) {
		if(cantidad < 0) {
			return;
		}
		this.setCapacidadDelTanque(cantidad);
		this.setNivelDeCombustible(cantidad);
	}
	
	//Aumenta la capacidad del tanque si la capacidad recibida es mayor a la actual.
	public void setCapacidadDelTanque(double capacidad) {
		if(capacidad < 0) {
			return;
		}
		if(capacidad < this.capacidadTanque) {
			return;
		}
		this.capacidadTanque = capacidad;
	}
	
	//Permite gastar combustible en una cantidad dada.
	public void gastarCombustible(double cantidad) {
		if(cantidad <= 0) {
			return;
		}
		this.nivelCombustible -= cantidad;
	}
	
	//------------------------------------------------
	//          		HP
	//------------------------------------------------
	
	//Devuelve true si el hp es menor o igual que 0.
	public boolean seEstrello() {
		return this.hp <= 0;
	}

	//Devuelve el maxHP.
	public int getMaxHP() {
		return maxHP;
	}
	
	//Recibe la cantidad de vida máxima y la extiende si el valor recibido es mayor que el actual.
	public void setMaxHP(int maxVida) {
		if(maxVida < this.maxHP) {
			return;
		}
		this.maxHP = maxVida;
	}
	
	//Devuelve el hp actual.
	public int getHP() {
		return this.hp;
	}
	
	//Setea el hp en un hp dado.
	public void setHP(int hp) {
		if(hp < 0) {
			return;
		}
		if(hp > this.maxHP){
			this.hp = maxHP;
		}
		else{
			this.hp = hp;
		}
		
	}
	
	//Permite reparar la nave según la cantidad de vida especificada.
	public boolean repararDmg(int vidaSumar) {
		if(this.naveSana()) {
			return false;
		}
		this.hp += vidaSumar;
		if(this.hp > this.maxHP) {
			this.hp = this.maxHP;
		}
		return true;
	}
	
	//Recibe daño mientras este sea un valor positivo.
	public void recibirDanio(int dmg) {
		if(dmg < 0) {
			return;
		}
		
		this.hp -= dmg;
		if(this.hp < 0) {
			this.hp = 0;
		}
	}
	
	public boolean tanqueLleno() {
		return this.nivelCombustible == this.capacidadTanque;
	}
	
	public boolean naveSana() {
		return this.hp == this.maxHP;
	}
}
