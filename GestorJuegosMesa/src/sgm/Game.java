package sgm;

import java.util.ArrayList;
import java.util.Random;

public abstract class Game extends Element{
	int minPlayers;
	int maxPlayers;
	static ArrayList <Game> GAMES = new ArrayList <>();
	double price;
	boolean delate;

	//API JAVA : Creates a new random number generator.
	static Random random = new Random();
	
	abstract void delElement ();
	
	public Game(String name, double price, int minPlayers, int maxPlayers) {
		super(name);
		this.price = price;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
		GAMES.add(this);
	}

	public String getName() {
		return name;
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setDelate(boolean delate) {
		this.delate = delate;
	}

	@Override
	public String toString() {
		return String.format("%s: \n"
				+ "· De %d a %d jugadores\n"
				+ "%s",name,minPlayers,maxPlayers,
				price>0 ? (String.format("· Precio por partida %.1f€\n",price)) : "");
	}
}
