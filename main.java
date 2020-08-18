package juego_gato;

import java.util.Scanner;

public class main {

	static boolean turnoX = true;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Se determina el jugador que inicia aleatoriamente
		if((int)(Math.random() * ((2)+1) + 1) == 1) {
			turnoX = true;
		}else {
			turnoX = false;
		}
		Scanner leer = new Scanner (System.in);
		while (true) {
			//Pide ingresar las coordenadas para colocar la marca
			System.out.print("Turno de: ");
			if(turnoX) {
				System.out.println("Jugador (x)");
			}else {
				System.out.println("PC (o)");
			}
			pintarTablero();
			if(turnoX) {
				System.out.println("Ingrese la columna");
				int x = leer.nextInt();
				System.out.println("Ingrese la fila");
				int y = leer.nextInt();
				if((x>=1 && x<=3) && (y>=1 && y<=3) && tablero[y][x]==0) {
					tablero[y][x]=4;
					turnoX=false;
				}else {
					System.out.println("Posiscion invalida");
					System.out.println("");
				}
			}else {
				turnoPC();
			}
			if(revisarVictoria() || revisarEmpate()) {
				volver_a_jugar();
			}
		}
	}
	
	public static void turnoPC() {
		boolean ocupado = false;
		do{
			int x = (int)(Math.random() * ((3-1)+1) + 1), y = (int)(Math.random() * ((3-1)+1) + 1);
			if(tablero[x][y] == 0) {
				tablero[x][y] = 5;
				ocupado = true;
				turnoX = true;
			}
		}while(!ocupado);
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
				if((Math.random() * ((2-1)+1) + 1) == 1) {
					turnoX = true;
				}else {
					turnoX = false;
				}
				error = false;
				break;
			case 2:
				System.exit(0);
				break;
				default:
					System.out.println("Opcion no valida");
					System.out.println("");
					error = true;
					break;
			}
		}
	}
	
	public static boolean revisarVictoria() {
		boolean horizontal = false, vertical = false, diagonal = false;
		int ultimoJugado = 4;
		for(int i = 1; i < tablero.length; i ++) {
			if(tablero[i][1] != 0 && tablero[i][1] == tablero[i][2] && tablero[i][1] == tablero[i][3]) {
				horizontal = true;
			}else{
				horizontal = false;
			}
			if(tablero[1][i] != 0 && tablero[1][i] == tablero[2][i] && tablero[1][i] == tablero[3][i]) {
				vertical = true;
			}else {
				vertical = false;
			}
			if(horizontal || vertical) {
				ultimoJugado = tablero[i][1];
				i = tablero.length + 1;
			}
		}
		if((tablero[1][1] != 0 && tablero[1][1] == tablero[2][2] && tablero[1][1] == tablero[3][3]) || (tablero[3][1] != 0 && tablero[3][1] == tablero[2][2] && tablero[3][1] == tablero[1][3])) {
			diagonal = true;
		}else {
			diagonal = false;
		}
		if(horizontal || vertical || diagonal) {
			pintarTablero();
			System.out.println("");
			System.out.print("Ganó el jugador ");
			if(ultimoJugado == 4) {
				System.out.println("x");
			}else {
				System.out.println("o");
			}
			System.out.println("");
			return true;
		}
		return false;
	}
	
	public static boolean revisarEmpate() {
		for(int i = 0; i < tablero.length; i ++) {
			for (int e = 0; e < tablero[0].length; e ++) {
				if(tablero[i][e] == 0) {
					return false;
				}
			}
		}
		pintarTablero();
		System.out.println("Hubo un empate");
		return true;
	}
	
}
