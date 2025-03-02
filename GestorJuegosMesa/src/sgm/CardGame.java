package sgm;

import java.util.HashMap;
import java.util.ArrayList;

public class CardGame extends Game implements Playable{
	
	int deck;
	int handSize;
	
	static ArrayList <Element> CARDGAMES = new ArrayList <>();
	
	public CardGame(String name, double price,  int minPlayers, int maxPlayers, int deck, int handSize) {
		super(name, price, minPlayers, maxPlayers);
		this.deck = deck;
		this.handSize = handSize;
		CARDGAMES.add(this);
	}
	
	/* Elimina el elemento actual */
	void delElement () {
		delete = true;
		CARDGAMES.remove(this);
	}

	@Override
	public String toString() {
		return String.format("JUEGO DE CARTAS %s"
				+ "· Baraja de %d cartas\n"
				+ "· Cada jugador empieza con %d cartas\n",super.toString(),deck,handSize);
	}

	/*Simula partida. Todos tiran cartas aleatoriamente
	 *  gana el primero en vaciar su mano */
	@Override
	public void playGame(ArrayList <Player> players) {
		
		System.out.println("Jugando a... " + name);
		
		//Recoger dinero
		double totalBet = Player.payGame(price, players);
		System.out.printf("Los jugadores apuestan %.1f€ por cabeza\n",price);
		
		HashMap <Player,Integer> numCards = new HashMap <>();
		
		for (Player p : players) {
			numCards.put(p, handSize);
		}
		boolean winner = false;
		while (!winner) {
			for (Player p : players) {
				// Cartas a tirar >=1 según las que tenga en la mano.
				int dropCards = 1 + random.nextInt(numCards.get(p));
				
				System.out.printf("%s se deshace de %d carta%s\n",p.name,dropCards,dropCards!=1 ? "s" : "");
				numCards.put(p, numCards.get(p) - dropCards);
				if (numCards.get(p)<=0) {
					System.out.printf("%s ha ganado %.1f€!!\n",p.name,totalBet);
					p.win(totalBet);
					winner = true;
					break;
				}
			}
		}
	}
	
	
	
}
