package gameequipment;
import java.util.*;

public class SeaField {
	private static final int VERTICAL_FIELD_SIZE = 10;
	private static final int HORIZONTAL_FIELD_SIZE = 10;
	private Cell[][] field;
	private ArrayList<Ship> ships = new ArrayList<Ship>();
	
	public CellStates getCellState(Coordinate coordinate){
		Cell cell = getCell(coordinate);
		return cell.getState();
		}

	public ArrayList<Ship> getShips() {
		return this.ships;
	}

	public void setShips(ArrayList<Ship> ships) {
		this.ships = ships;
	}

	public Cell getCell(Coordinate c) {
		int x = c.getX();
		int y = c.getY();
		return field[x][y];
	}

	public void showMap(String username) {
		System.out.println("Flot " + username + "  redeet na glazach  :  ");
		System.out.println("  |0 1 2 3 4 5 6 7 8 9\n--+-------------------");
		for (int i = 0; i < 10; i++) {
			System.out.print(i + " |");
			for (int j = 0; j < 10; j++) {
				Cell cell = this.field[j][i];
				if (cell.getState() == CellStates.SHOT) {
					System.out.print("* ");
				} else if (cell.getState() == CellStates.SUNKSHIP || cell.getState() == CellStates.SHOTSHIP) {
					System.out.print("X ");
				} else {
					System.out.print("~ ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public void showField(String username) {
		System.out.println(username +" Polozhenie del takovo :  ");
		System.out.println("  |0 1 2 3 4 5 6 7 8 9\n--+-------------------");
		for (int i = 0; i < 10; i++) {
			System.out.print(i + " |");
			for (int j = 0; j < 10; j++) {
				Cell cell = this.field[j][i];
				if (cell.getState() == CellStates.SHIP) {
					System.out.print("O ");
				} else if (cell.getState() == CellStates.SHOT) {
					System.out.print("* ");
				} else if (cell.getState() == CellStates.SUNKSHIP || cell.getState() == CellStates.SHOTSHIP ) {
					System.out.print("X ");
				} else {
					System.out.print("~ ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public SeaField() { 
		field = new Cell[VERTICAL_FIELD_SIZE][HORIZONTAL_FIELD_SIZE];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Coordinate c = new Coordinate(i, j);
				Cell cell = new Cell(c);
				cell.setState(CellStates.WATER);
				field[i][j] = cell;
				
			}
		}
	}
}
