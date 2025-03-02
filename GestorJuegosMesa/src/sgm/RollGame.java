package sgm;

import java.util.ArrayList;

public class RollGame extends Game implements Playable{
	
	int numberOfDice;
	int diceSize;
	String setting;
	
	static ArrayList <Element> ROLLGAMES = new ArrayList <>();
	
	//Lista con acciones aleatorias para los juegos de rol. 
	//En algunas es considerado muerte en otros no. 
	static ArrayList<String> events = new ArrayList<String>() {{
		//MUERE 0-6 
        add(" se ha caído por un precipicio y ha muerto.");
        add(" es asesinado por un grupo de bandidos en un oscuro callejón.");
        add(" activa una trampa y cae en una red de cuerdas");
        add(" abre un portal dimensional, y le traga por dentro");
        add(" es tradicionado por un aliado inesperadamente y muere.");
        add(" se encuentra con un anciano zombie que le muerde.");
        add(" bebe una poción misteriosa y se vuelve todo negro.");
        //VIVE 7-9
        add(" ha descubierto una herramienta que podría darte una gran ventaja.");
        add(" despierta en una sala desconocida sin recordar cómo llegó.");
        add(" recoge un objeto mágico con una luz sobrenatural.");
    }};
    
    
	public RollGame(String name, int minPlayers, int maxPlayers, int numberOfDice, int diceSize,
			String setting) {
		super(name, 0, minPlayers, maxPlayers);
		this.numberOfDice = numberOfDice;
		this.diceSize = diceSize;
		this.setting = setting;
		ROLLGAMES.add(this);
	}
	
	/*Elimina un juego de rol del programa. */
	void delElement () {
		delete = true;
		ROLLGAMES.remove(this);
	}

	@Override
	public String toString() {
		return String.format("JUEGO DE ROLL %s"
				+ "· Ambientado en %s\n"
				+ "· Se usa %d dado%s\n"
				+ "· %d cara%s por dado\n",super.toString(),setting,numberOfDice, 
				numberOfDice!=1 ? "s":"", diceSize, diceSize!=1 ? "s":"");
	}
	
	/* Realiza un juego donde los jugadores hacen jugadas 
	 * aleatorias hasta que queda uno vivo.*/
	@Override
	public void playGame(ArrayList <Player> players) {
		 
		String text = String.format("El temible %s les espera a los jugadores de %s.\n"
				+ "¿Quien lanzará el mayor numero en un dado de %d cara%s?\n"
				,setting, name, diceSize, diceSize!=1 ? "s":"");
		System.out.println(text);
        
        Player master = players.remove(random.nextInt(players.size()));
        System.out.println(master.name + " será el master.");
        
		int alivePlayers = players.size();
		while (alivePlayers>1) {
			//Seleciona un jugador y un evento aleatoriamante
			
			Player randomPlayer = players.remove(random.nextInt(players.size()));
			int numEvent = random.nextInt(RollGame.events.size());
			System.out.println(randomPlayer.name + RollGame.events.get(numEvent));
			if(numEvent>=7) {//JUEGADOR VIVE
				players.add(randomPlayer);
			}
			alivePlayers = players.size();
		}
		System.out.println("El ultimo jugador en pie es " + players.get(0).name +", ha ganado!!");
		players.get(0).win(0);
		
	}
}
