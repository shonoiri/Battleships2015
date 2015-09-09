package gameequipment;

import java.util.ArrayList;

public class SeaField {
	private static final int VERTICAL_FIELD_SIZE = 10;
	private static final int HORIZONTAL_FIELD_SIZE = 10;
	private Cell[][] field;
	private ArrayList<Ship> ships = new ArrayList<Ship>();

<<<<<<< HEAD
	public SeaField() {
		field = new Cell[VERTICAL_FIELD_SIZE][HORIZONTAL_FIELD_SIZE];
		Cell cell;
		Coordinate coordinate;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				coordinate = new Coordinate(i, j);
				cell = new Cell(coordinate);
				cell.setState(CellStates.WATER);
				field[i][j] = cell;
			}
		}
	}

=======
>>>>>>> 686fa24add6b9100cd7d3c458b20f852e79978a5
	public CellStates getCellState(Coordinate coordinate) {
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
		Cell cell = field[x][y];
		return cell;
<<<<<<< HEAD
=======
	}

	public SeaField() {
		field = new Cell[VERTICAL_FIELD_SIZE][HORIZONTAL_FIELD_SIZE];
		Cell cell;
		Coordinate coordinate;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				coordinate = new Coordinate(i, j);
				cell = new Cell(coordinate);
				cell.setState(CellStates.WATER);
				field[i][j] = cell;
			}
		}
>>>>>>> 686fa24add6b9100cd7d3c458b20f852e79978a5
	}
}
