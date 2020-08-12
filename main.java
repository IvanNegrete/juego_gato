package juego_gato;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean turnoX = true;
		boolean victoria = false;
		Scanner leer = new Scanner (System.in);
		while (true) {
			System.out.print("Turno de: ");
			if(turnoX) {
				System.out.println("x");
			}else {
				System.out.println("o");
			}
			pintarTablero();
			System.out.println("Ingrese la columna");
			int x = leer.nextInt();
			System.out.println("Ingrese la fila");
			int y = leer.nextInt();
			if((x>=1 && x<=3) && (y>=1 && y<=3) && tablero[y][x]==0) {
				if(turnoX) {
					tablero[y][x]=4;
					turnoX=false;
				}else {
					tablero[y][x]=5;
					turnoX=true;
				}
			}else {
				System.out.println("Posiscion invalida");
			}
			if(revisarEmpate()) {
				volver_a_jugar();
			}
		}
	}
 
	//se crea el tablero de juego
	static int tablero [][] = 
			{{7,1,2,3},
  			{1,0,0,0},
  			{2,0,0,0},
  			{3,0,0,0}};
	
	public static void pintarTablero() {
		for (int i = 0; i < tablero.length; i ++) {
			for (int e = 0; e < tablero[0].length; e ++) {
				switch(tablero[i][e]) {
				//del 1 al 3 son las filas y columnas
				case 1: case 2: case 3:
					System.out.print(tablero[i][e]);
					break;
					
				case 4://para pintar la x
					System.out.print("x");
					break;
					
				case 5://para pintar la o
					System.out.print("o");
					break;
					
				default://Pinta espacios vacios
					System.out.print(" ");
					break;
				}
			}
			System.out.println("");
		}
	}
	
	public static void volver_a_jugar() {
		Scanner leer = new Scanner(System.in);
		boolean error = true;
		while(error) {
			System.out.println("¿Desea volver a jugar?");
			System.out.println("1.Si  2.No");
			int seleccion = leer.nextInt();
			switch(seleccion) {
			case 1:
				for (int i = 1; i < tablero.length; i ++) {
					for (int e = 1; e < tablero[0].length; e ++) {
						tablero[i][e]=0;
					}
				}
				error = false;
				break;
			case 2:
				System.exit(0);
				break;
				default:
					System.out.println("Opcion no valida");
					error = true;
					break;
			}
		}
	}
	
	public static boolean revisarEmpate() {
		for(int i = 0; i < tablero.length; i ++) {
			for (int e = 0; e < tablero[0].length; e ++) {
				if(tablero[i][e] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
}
