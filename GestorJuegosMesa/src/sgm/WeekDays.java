package sgm;

import java.util.ArrayList;

public class WeekDays {
	static int dayNum;
	static ArrayList <String> DAYS = new ArrayList <String>() {{
		add("Lunes");
		add("Martes");
		add("Miercoles");
		add("Jueves");
		add("Viernes");
		add("Sabado");
		add("Domingo");
	}};
	
	/*Pasa al día siguiente si es el domingo pasa el lunes. */
	static void changeDay(){
		dayNum = ((dayNum != 6) ? (dayNum+1) : 0);
		modifyOptions();
		System.out.println(tooStringDay());
	}
	 
	static String getDay() {
		return DAYS.get(dayNum);
	}
	
	// Imprime el string del dia que es. 
	static String tooStringDay(){
		return "Ya es " + getDay() + "! ";
	}
	
	/*Modifica la disponibilidad de las opciones 
	 * del menú según el día que es. */
	static void modifyOptions() {
		Menu.resetAviable();
		
		switch (dayNum){
		case 0:
			Menu.aviable.add(1,false);
			break;
		case 1:
			Menu.aviable.add(0,false);
			Menu.aviable.add(1,false);
			Menu.aviable.add(2,false);
			break;
		case 2:
			Menu.aviable.add(1,false);
			break;
		case 3:
			Menu.aviable.add(0,false);
			Menu.aviable.add(2,false);
			break;
		case 4:
			Menu.aviable.add(0,false);
			break;
		case 5:
		case 6:
			Menu.aviable.add(0,false);
			Menu.aviable.add(1,false);
			Menu.aviable.add(2,false);
			Menu.aviable.add(3,false);
			Menu.aviable.add(4,false);
			Menu.aviable.add(5,false);
			Menu.aviable.add(6,false);
			break;
			
		}
	}
;}
