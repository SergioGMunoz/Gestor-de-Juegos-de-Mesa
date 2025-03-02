package sgm;

public class Main {
	
	public static void main(String[] args) {
		Menu.resetAviable();
		WeekDays.modifyOptions();
		
		System.out.print("\u001B[36m");
		System.out.println("Buenos días! Hoy es lunes.");
		System.out.println("Este programa es un C.R.U.D de una compañia de juegos de mesa y jugadores");
		System.out.println("Desde le menú se pueden hacer las operaciones (dependiendo del día de la semana)");
		System.out.println("Antes de empezar dime,");
		System.out.println("¿Quieres que insertemos unos cuantos datos al programa?");
		System.out.println("0 -> Si");
		System.out.println("1 -> No");
		System.out.print("\u001B[0m");
		
		if (Menu.validInt(0,1) ==0) {
			Player p1 = new Player("Javier", 75.30);
	        Player p2 = new Player("Lucía", 95.60);
	        Player p3 = new Player("Carlos", 50.25);
	        Player p4 = new Player("Sergio", 85.40);
	        Player p5 = new Player("Fernando", 60.75);
	        Player p6 = new Player("Pablo", 78.90);
	        Player p7 = new Player("Tony", 55.00);
	        Player p8 = new Player("Ana", 92.10);
	        Player p9 = new Player("David", 67.80);
	        Player p10 = new Player("Elena", 88.20);
	        CardGame c1 = new CardGame("Uno", 12.99, 2, 6, 40, 7);
	        CardGame c2 = new CardGame("Polilla Tramposa", 9.99, 3, 5, 50, 8);
	        CardGame c3 = new CardGame("Trío", 15.50, 2, 4, 52, 5);
	        CardGame c4 = new CardGame("Tute", 10.75, 2, 4, 40, 10);
	        CardGame c5 = new CardGame("Poker", 20.00, 2, 8, 52, 5);
	        RollGame r1 = new RollGame("Arkham Horror", 4, 10, 3, 6, "Londres");
	        RollGame r2 = new RollGame("Dungeons & Dragons", 4, 15, 1, 20, "Reinos Olvidados");
	        RollGame r3 = new RollGame("Vampiro", 4, 12, 2, 10, "Nueva York");
	        RollGame r4 = new RollGame("La Llamada de Cthulhu", 5, 8, 1, 100, "Arkham");
	        RollGame r5 = new RollGame("Pathfinder", 4, 10, 2, 20, "Golarion");
	        BoardGame b1 = new BoardGame("Monopoly", 55.5, 2, 5, 10, 4, 6);
	        BoardGame b2 = new BoardGame("Harmonies", 22.20, 2, 5, 20, 5, 6);
	        BoardGame b3 = new BoardGame("Catan", 30.99, 3, 4, 19, 10, 6);
	        BoardGame b4 = new BoardGame("Honey Buzz", 45.00, 1, 4, 25, 7, 6);
	        BoardGame b5 = new BoardGame("Everdell", 50.00, 1, 4, 30, 5, 6);
			
			System.out.print("\u001B[36m");
			System.out.println("He creado 10 Jugadores, 5 Juegos de Cartas, 5 de Roll y 5 de Tablero.");
			System.out.print("\u001B[0m");
		}else {
			System.out.print("\u001B[36m");
			System.out.println("De acuerdo los datos quedan vacíos entonces");
			System.out.print("\u001B[0m");
		}

		
		boolean end = false;
		while(!end) {
			end = Menu.doOptions(Menu.displayMenu());			
		}
		System.out.print("\u001B[36m");
		System.out.println("Esperamos verte pronto.");
		System.out.println("¡ADIOS!");
	}
}
