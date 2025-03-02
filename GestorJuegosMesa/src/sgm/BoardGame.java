package sgm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BoardGame extends Game implements Playable{
	int boardScuares;
	int rounds; 
	int diceSize;
	
	static ArrayList <Element> BOARDGAMES = new ArrayList <>();
	
	public BoardGame(String name, double price, int minPlayers, int maxPlayers, int boardScuares, int rounds, int diceSize) {
		super(name, price, minPlayers, maxPlayers);
		this.boardScuares = boardScuares;
		this.rounds = rounds;
		this.diceSize=diceSize;
		BOARDGAMES.add(this);
	}
	
	/* Elimina el elemento actual */
	void delElement () {
		delete = true;
		BOARDGAMES.remove(this);
	}
	
	@Override
	public String toString() {
		return String.format("JUEGO DE TABLERO %s"
				+ "· Dura %d ronda%s\n"
				+ "· El tablero tiene %d casilla%s\n",super.toString(),rounds, 
				rounds!=1 ? "s":"",boardScuares, boardScuares!=1 ? "s":"");
	}
	
	/* Simula una partida. Gana el que más lejos llegue en el tablero */
	@Override
	public void playGame(ArrayList <Player> players) {
		//Jugador (Lista: 0 Vueltas y 1 Posicion)
		HashMap <Player,ArrayList<Integer>> positions = new HashMap <>();
		
		for(Player p:players) {
			ArrayList <Integer> initialList = new ArrayList <>();
			initialList.add(0);
			initialList.add(0);
			
			positions.put(p, initialList);
		}
		
		System.out.println("Están todos en la posición de salida del " + name);
		
		// Recoger dinero
		Player winner=null;
		double totalPrice = Player.payGame(price, players);
		System.out.printf("Los jugadores apuestan %.1f€ por cabeza\n",price);
		
		System.out.println("INFO: Jugador, Vuelta, Casilla");
		System.out.println();
		
		for (int i=1;i<=rounds;i++) {
			int maxLaps = -1;
			int maxPosition = -1;
			String info="Ronda: " + i + "\n";
			
			for (Map.Entry <Player, ArrayList<Integer>> pos : positions.entrySet()) {
				int laps = pos.getValue().get(0);
				int position = pos.getValue().get(1);
				
				int dice= 1 + random.nextInt(diceSize);
				position += dice;
				
				if(position > boardScuares) {
					 laps += position / boardScuares;
					 position = position % boardScuares;
				}
				pos.getValue().add(0,laps);
				pos.getValue().add(1,position);
				
				 if (laps > maxLaps || (laps == maxLaps && position > maxPosition)) {
				        winner = pos.getKey();
				        maxLaps = laps;
				        maxPosition = position;
				    }
				info += String.format (" |%s, %d, %d| "
						, pos.getKey().name, laps, position);
			}
			System.out.print(info + "\n");
		}
		if(winner!=null) {
			System.out.printf("%s ha ganado %.1f€!!\n",winner.name, totalPrice);
		}else {
			System.err.println("Nadie gana :(");
		}
		
	}	
}
