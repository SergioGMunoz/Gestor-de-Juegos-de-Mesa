package sgm;

import java.util.ArrayList;

public class Player extends Element {
	double money;
	static ArrayList <Element> PLAYERS = new ArrayList <>();
	int gamesWon=0;
	
	public Player(String name, double money) {
		super(name);
		this.money = money;
		PLAYERS.add(this);
	}
	
	/*Elimina un jugador del programa. */
	void delElement () {
		delete = true;
		PLAYERS.remove(this);
	}

	public String getName() {
		return name;
	}
 
	public double getMoney() {
		return money;
	}

	public int getGamesWon() {
		return gamesWon;
	}

	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}

	@Override
	public String toString() {
		return String.format("JUGADOR %s: \n"
				+ " · Tiene %.1f€\n"
				+ " · Ha ganado %d partida%s\n",name,money,gamesWon, gamesWon !=1 ? "s" : "");
	}
	
	/*Comprueba que un jugador tenga 
	 * dinero suficiente para pagar un precio. */
	boolean haveMoney(double price) {
		return price<=money;
	}
	
	/*Comprueba aquí una lista de jugadores tenga dinero 
	 * suficiente para pagar un precio */
	static boolean allHaveMoney(double price, ArrayList <Player> players) {
		for (Player p : players) {
			if (!p.haveMoney(price)) {
				System.out.println(p.name +" no tiene dinero suficiente.");
				return false;
			}
		}
		return true;
	}
	
	/*Incremento de dinero */
	void earnMoney (double money) {
		this.money += money;
	}
	
	/*Resta de dinero.  */
	void looseMoney (double money) {
		if (haveMoney(money)) {
			this.money -=money;
		}else {
			System.out.println(name + " no puede perder el dinero");
		}
	}
	
	/*Hace que una lista de jugadores reste a cada 1 de ellos 
	 * un precio y devuelve el total. */
	static double payGame (double price, ArrayList <Player> players) {
		double total=0;
		for (Player p:players) {
			p.looseMoney(price);
			total += price;
		}
		return total;
	}
	
	/*Suma una cantidad de dinero y incrementa 
	 * juegos ganados en 1 */
	void win (double money) {
		earnMoney(money);
		gamesWon += 1;
	}
	
	
	
	
	
}
