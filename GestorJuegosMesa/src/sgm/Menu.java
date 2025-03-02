package sgm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {
	
	static ArrayList <String> options = new ArrayList <String>() {{
		add(" 0 -> Jugar juego de cartas"); 
		add(" 1 -> Jugar juego de roll"); 
		add(" 2 -> Jugar jugar juego de tablero"); 
		add(" 3 -> Crear"); 
		add(" 4 -> Consultar");  
		add(" 5 -> Actualizar"); 
		add(" 6 -> Borrar");  
		add(" 7 -> Pasar día");
		add(" 8 -> Cerrar programa");
	}};
	
	static ArrayList<Boolean> aviable = new ArrayList<Boolean>();
	
	static Scanner s = new Scanner(System.in);

	/* Restablece la lista aviable creando tantas opciones 
	 * como options y disponibles */
	static void resetAviable() {
	    aviable.clear();
	    for (int i = 0; i < options.size(); i++) {
	        // Si es el índice 1, asignar 'false', en el resto 'true'
	    	 aviable.add(true);
	    }
	}
	
	/*Limpiar consola. Crea 50 filas en blanco */
	static void clear() {
		for (int i=0;i<50;i++) {
			System.out.println();
		}
	}
	
	/* Muestra el menú de opciones. 
	 * Devolviendo la opción seleccionada.  */
	static Integer displayMenu() {
		//PAUSA PARA CONTINUAR
		System.out.printf("(Envía cualquier tecla para continuar)");
		if (s.hasNext()) {
			s.nextLine();
			clear();
		}
		System.out.print("\u001B[32m");
		System.out.println("··········SELECCIONA UNA OPCIÓN··········");
		Integer num = 0;
		for (int i=0;i<options.size();i++) {
			if (aviable.get(i)) {
			    System.out.println(options.get(i));
			} else {
			    System.out.println(options.get(i) + "\u001B[31m ("+WeekDays.getDay()
			    + " no disponible) \u001B[32m");
			}
		}
		System.out.println("·········································");
		System.out.print("\u001B[0m");
		return validOption();
	}
	
	/*Valida opción menu en bucle pidiendo por scanner 
	 * el numero de nuevo. Devuelve la opción válida.*/
	public static int validOption() {
		int num = 0;
		boolean flag = false;
		do {
			if (s.hasNextInt()){
				num = s.nextInt();
				s.nextLine();
				if(num>=0 && num<aviable.size()){
					if(!aviable.get(num)) {
						System.err.printf("El %s no se puede hacer eso, intentelo de nuevo: \n",WeekDays.getDay());
					}else {
						flag=true;
					}
				}else {
					System.err.println("El número no está dentro del rango solicitado, intentelo de nuevo: ");
				}
			}else {
				System.err.println("Debe introducir un número, intentelo de nuevo: ");
				s.nextLine();
			}
		}while (!flag);
		return(num);
	}
	
	/*Valida la entrada de scanner en bucle en un rango de
	 * números. Devuelve la opción validada.  */
	public static int validInt(int a, int b) {
		int num=0;
		boolean flag=false;
		do {
			if (s.hasNextInt()){
				num=s.nextInt();
				s.nextLine();
				flag=true;
				if(num>=a && num<=b){
					flag=true;
				}else {
					System.out.println("El número no está dentro del rango solicitado, intentelo de nuevo: ");
					flag=false;
				}
			}else {
				System.out.println("Debe introducir un número, intentelo de nuevo: ");
				s.nextLine();
				flag=false;
			}
		}while (!flag);
		return(num);
	}
	
	// Valida que un double no sea mayor que 0 ni menor que 1000
	public static double validMoney() {
	    double num = 0;
	    boolean flag = false;
	    do {
	        if (s.hasNextDouble()) {
	            num = s.nextDouble();
	            s.nextLine();
	            if (num >= 0 && num <= 1000) {
	                flag = true;
	            } else {
	                if (num < 0) {
	                    System.out.println("El dinero no puede ser negativo, intentelo de nuevo: ");
	                } else {
	                    System.out.println("El dinero no puede ser mayor que 1000, intentelo de nuevo: ");
	                }
	                flag = false;
	            }
	        } else {
	            System.out.println("Debe introducir un número, intentelo de nuevo: ");
	            s.nextLine();
	            flag = false;
	        }
	    } while (!flag);
	    return num;
	}
	
	/* Ejecuta las acciones del menú según en el valor de num.
	 *  Devuelve true si num es 8. */
	static boolean doOptions (int num) {
		switch (num){
		case 0:
		case 1:
		case 2:
			playGame(num);
			break;
		case 3:
			create();
			break;
		case 4:
			System.out.print("\u001B[32m");
			System.out.println("Selecciona que quieres consultar: ");
			query(listChoice());
			break;
		case 5:
			System.out.print("\u001B[32m");
			System.out.println("Selecciona que quieres modificar: ");
			update(listChoice());
			break;
		case 6:
			System.out.print("\u001B[32m");
			System.out.println("Selecciona que quieres borrar: ");
			delate(listChoice());
			break;
		case 7: 
			WeekDays.changeDay();
			break;
		case 8:
			return true;
		}
		return false;
	}
	
	/* Permite seleccionar y jugar un juego. Valida la 
	 * cantidad de jugadores, que tengan dinero suficiente 
	 * y comienza el juego si es posible. */
	static void playGame(int num) {
		System.out.print("\u001B[32m");
		System.out.println("Selecciona a cual jugar: ");
		Game toPlay=null;
		
		switch (num) {
        case 0:
        	printIndexList(CardGame.CARDGAMES);
        	toPlay = (Game) CardGame.CARDGAMES.get(validInt(0,CardGame.CARDGAMES.size()));
        	break;
        case 1:
        	printIndexList(RollGame.ROLLGAMES);
        	toPlay = (Game) RollGame.ROLLGAMES.get(validInt(0,RollGame.ROLLGAMES.size()));
        	break;
        case 2:
        	printIndexList(BoardGame.BOARDGAMES);
        	toPlay = (Game) BoardGame.BOARDGAMES.get(validInt(0,BoardGame.BOARDGAMES.size()));
        	break;
		}
		
		System.out.print("\u001B[0m");
		
		int min = toPlay.minPlayers;
		int max = toPlay.maxPlayers;
		int numPlayers = Player.PLAYERS.size();
		
		//MINIMO JUGADORES Y AGRUPARLOS
		ArrayList <Player> players = new ArrayList <>();
		
		if (min > Player.PLAYERS.size()) {
			System.err.printf("El mínimo de para %s es %d jugadores y solo existen %d en el programa\n",
					toPlay.name,toPlay.minPlayers,Player.PLAYERS.size());
			return;
		}else {
			System.out.printf("Dime quienes (entre %d y %d) van a jugar"
					+ " (-1 para parar)\n", min,max);
			printIndexList(Player.PLAYERS);
			boolean end=false;
			for (int i=0; i<max && !end; i++) {
				int input =validInt(-1,Player.PLAYERS.size());
				if (input == -1) {
					if (players.size()>=min) {
						end=true;
					}else {
						System.err.println("Todavía no has llegado al minimo de jugadores");
						i--;
					}
				}else {
					Player player = (Player) Player.PLAYERS.get(input);
					if (players.contains(player))  {
						System.err.println("El jugador ya esta selecionado");
						i--;
					}else {
						players.add(player);
						System.out.println(player.name + " añadido!");
					}
				}
			}
		}
		//CALCULAR SI TODOS LOS JUGADORES TIENEN DINERO
		if(Player.allHaveMoney(toPlay.price,players)) {
			System.out.println("Vamos a jugar!!!");
			//PAUSA PARA CONTINUAR
			System.out.printf("(Envía cualquier tecla para continuar)");
			if (s.hasNext()) {
				s.nextLine();
				clear();
			}
			if(toPlay instanceof Playable) {
				Playable p = (Playable) toPlay;
				p.playGame(players);
			}else {
				System.err.println(toPlay.name + " no es jugable");
			}
		}else {
			System.err.println("Los jugadores no tienen dinero suficiente");
			return;
		}
		
	}
	
	/*Solicita los datos necesarios para crear 
	 * juegos o jugadores */
	static void create() {
		
	    System.out.print("\u001B[32m");
	    System.out.println("¿Qué quieres crear?");
	    System.out.println("0 -> Jugador/a");
	    System.out.println("1 -> Juego de mesa");
	    System.out.println("·········································");
	    System.out.print("\u001B[0m");

	    switch (validInt(0, 1)) {
	        case 0: // Jugadores
	            System.out.println("¿Cómo se llama el jugador?");
	            String name = s.nextLine();
	            System.out.println("¿Cuánto dinero tiene en la cuenta?");
	            double money = validMoney();
	            Player p = new Player(name, money);
	            System.out.printf("Creado %s con %.2f€\n", name, money);
	            break;
	        case 1: // Juegos
	            System.out.println("¿Cómo se llama el juego?");
	            String nameG = s.nextLine();
	            System.out.println("¿Cuál es el mínimo de jugadores (2-50)?");
	            int min = validInt(2, 50);
	            System.out.printf("¿Cuál es el máximo (%d-50)?\n", min);
	            int max = validInt(min, 50);
	            
	            System.out.println("¿Qué tipo de juego es?");
	            System.out.println("0 -> Juego de cartas");
	            System.out.println("1 -> Juego de rol");
	            System.out.println("2 -> Juego de tablero");
	            
	            double price = 0;
	            
	            switch (validInt(0, 2)) {
	                case 0:
	                    System.out.println("¿Cuánto dinero va a costar la partida (0-1000)?");
	                    price = validMoney();
	                    
	                    System.out.println("¿Cuántas cartas tendrá la baraja (10-1000)?");
	                    int deck = validInt(10, 1000);
	                    
	                    int maxCards = deck / max;
	                    if (maxCards <= 1) {
	                        System.err.println("Las cartas son demasiado pocas para tantos jugadores");
	                        deck += max;
	                        System.out.println("Estableciendo la baraja a " + deck + " cartas");
	                    }
	                    
	                    maxCards = deck / max;
	                    
	                    System.out.printf("¿Cuántas cartas se reparten a cada jugador (1-%d)?\n", maxCards);
	                    int handSize = validInt(1, maxCards);
	                    
	                    CardGame c = new CardGame(nameG, price, min, max, deck, handSize);
	                    System.out.printf("Creado juego de cartas %s\n", nameG);
	                    break;
	                case 1:
	                    System.out.println("¿Dónde estará ambientado el juego?");
	                    String setting = s.nextLine();
	                    
	                    System.out.println("¿Cuántos dados habrá en juego (1-10)?");
	                    int numberOfDice = validInt(1, 10);
	                    
	                    System.out.println("¿Cuántas caras tienen los dados (6-50)?");
	                    int diceSize = validInt(6, 50);
	                    
	                    RollGame r = new RollGame(nameG, min, max, numberOfDice, diceSize, setting);
	                    System.out.printf("Creado juego de rol %s\n", nameG);
	                    break;
	                case 2:
	                    System.out.println("¿Cuánto dinero va a costar la partida (0-1000)?");
	                    price = validMoney();
	                    
	                    System.out.println("¿Cuántas casillas tiene el tablero (5-100)?");
	                    int boardSquares = validInt(5, 100);
	                    
	                    System.out.println("¿Cuántas rondas durará el juego (1-50)?");
	                    int rounds = validInt(1, 50);
	                    
	                    System.out.println("¿Cuántas caras tiene el dado (6-50)?");
	                    int diceSizeB = validInt(6, 50);
	                    
	                    BoardGame b = new BoardGame(nameG, price, min, max, boardSquares, rounds, diceSizeB);
	                    System.out.printf("Creado juego de tablero %s\n", nameG);
	                    break;
	            }
	            break;
	    }
	}

	/*Permite imprimir un elemento a seleccionar 
	 * por el usuario. */
	static void query(ArrayList <Element> list) {
		if(list.isEmpty()) {
			System.err.println("No existen existencias");
			return;
		}
		System.out.print("\u001B[32m");
		System.out.println("Selecciona cual consultar:");
		printIndexList(list);
		System.out.print("\u001B[0m");
		
		System.out.println(list.get(validInt(0,list.size())));
	}
	
	/*Permite modificar un elemento, eliminándolo y 
	 * creando uno de las mismas características */
	static void update(ArrayList <Element> list) {
		if(list.isEmpty()) {
			System.err.println("No existen existencias");
			return;
		}
		System.out.print("\u001B[32m");
		System.out.println("Selecciona cuál quieres modificar: ");
		printIndexList(list);
		System.out.print("\u001B[0m");
		Element remove = list.get(validInt(0,list.size()));
		remove.delElement();
		System.out.println("Reemplazando " + remove.name);
		if (remove instanceof Player) {
			
			System.out.println("¿Cómo se llama ahora el jugador?");
            String name = s.nextLine();
            System.out.println("¿Cuánto dinero tiene ahora en la cuenta?");
            double money = validMoney();
            Player p = new Player(name, money);
            System.out.printf("Actualizado %s a %s con %.2f€\n",remove.name, name, money);
            
            //Mantener partidas ganadas
            p.setGamesWon(((Player)remove).getGamesWon());
            
		}else if (remove instanceof Game) {
			
			System.out.println("¿Cómo se llama ahora el juego?");
            String nameG = s.nextLine();
            System.out.println("¿Cuál es el mínimo nuevo de jugadores (2-50)?");
            int min = validInt(2, 50);
            System.out.printf("¿Cuál es el máximo nuevo de jugadores (%d-50)?\n", min);
            int max = validInt(min, 50);
            
            double price = 0;
            
            if (remove instanceof CardGame) {
            	System.out.println("¿Cuánto dinero va a costar ahora la partida (0-1000)?");
                price = validMoney();
                
                System.out.println("¿Cuántas cartas tendrá ahora la baraja (10-1000)?");
                int deck = validInt(10, 1000);
                
                int maxCards = deck / max;
                if (maxCards <= 1) {
                    System.err.println("Las cartas son demasiado pocas para tantos jugadores");
                    deck += max;
                    System.out.println("Estableciendo la baraja a " + deck + " cartas");
                }
                
                maxCards = deck / max;
                
                System.out.printf("¿Cuántas cartas se reparten ahora a cada jugador (1-%d)?\n", maxCards);
                int handSize = validInt(1, maxCards);
                
                CardGame c = new CardGame(nameG, price, min, max, deck, handSize);
                System.out.printf("Actualizando juego de %s a %s \n",remove.name, nameG);
			}else if (remove instanceof RollGame) {
				 System.out.println("¿Dónde estará ahora ambientado el juego?");
                 String setting = s.nextLine();
                 
                 System.out.println("¿Cuántos dados habrá ahora en juego (1-10)?");
                 int numberOfDice = validInt(1, 10);
                 
                 System.out.println("¿Cuántas caras tienen ahora los dados (6-50)?");
                 int diceSize = validInt(6, 50);
                 
                 RollGame r = new RollGame(nameG, min, max, numberOfDice, diceSize, setting);
                 System.out.printf("Actualizando juego de %s a %s \n",remove.name, nameG);
			}else {
				 System.out.println("¿Cuánto dinero va a costar ahora la partida (0-1000)?");
                 price = validMoney();
                 
                 System.out.println("¿Cuántas casillas tiene ahora el tablero (5-100)?");
                 int boardSquares = validInt(5, 100);
                 
                 System.out.println("¿Cuántas rondas durará ahora el juego (1-50)?");
                 int rounds = validInt(1, 50);
                 
                 System.out.println("¿Cuántas caras tiene ahora el dado (6-50)?");
                 int diceSizeB = validInt(6, 50);
                 
                 BoardGame b = new BoardGame(nameG, price, min, max, boardSquares, rounds, diceSizeB);
                 System.out.printf("Actualizando juego de %s a %s \n",remove.name, nameG);
			}
            
		}else {
			System.err.println("El elemento no es ni jugador ni juego");
		}
			
	}
		
	/*Elimina un elemento del programa */
	static void delate(ArrayList <Element> list) {
		if(list.isEmpty()) {
			System.err.println("No existen existencias");
			return;
		}
		System.out.print("\u001B[32m");
		System.out.println("Selecciona cual quieres borrar:");
		printIndexList(list);
		System.out.print("\u001B[0m");
		Element remove = list.get(validInt(0,list.size()));
		remove.delElement();
		System.out.println("Eliminando... " + remove.name);
	}
	
	/*El usuario elige entre juegos(varios) y jugadores.
	 * Devuelve la lista con la opción elegida. */ 
	static ArrayList<Element> listChoice() {
	    System.out.print("\u001B[32m");
	    System.out.println("0 -> Jugadores");
	    System.out.println("1 -> Juegos de cartas");
	    System.out.println("2 -> Juegos de rol");
	    System.out.println("3 -> Juegos de tablero");
	    System.out.println("·········································");
	    System.out.print("\u001B[0m");

	    switch (validInt(0, 3)) {
	        case 0:
	            return Player.PLAYERS;
	        case 1:
	            return CardGame.CARDGAMES;
	        case 2:
	            return RollGame.ROLLGAMES;
	        case 3:
	            return BoardGame.BOARDGAMES;
	    }
	    return new ArrayList<>();
	}
	
	/*Imprime una lista con índices.*/
	static void printIndexList (ArrayList <Element> list) {
		for (int i =0; i<list.size();i++) {  // Recorrer la lista
	        System.out.printf("%d -> %s\n",i,list.get(i).name);
	    }
	}
}
