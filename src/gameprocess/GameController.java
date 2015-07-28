package gameprocess;
import players.User;
import gameequipment.Cell;
import gameequipment.CellStates;
import gameequipment.Coordinate;
import gameequipment.SeaField;
import gameequipment.Ship;
import gameequipment.ShipStates;

public class GameController {
	private boolean endGame;

	public GameController(User robot, User human) {
		creataAndSetShips(robot, human);
		attack(robot, human);
	}
//TODO METHOD PLAY
	private void creataAndSetShips(User robot, User human) {
		Coordinate coordinate;
		Coordinate lc;
		int orientace;
		boolean setShip;
		for (int i = 1; i <= 4; i++) {
			for (int j = 5 - i; j >= 1; j--) {
				do {
					coordinate = human.askCoordinateOfShip();
					orientace = human.askOrientation();
					lc = createLastCoordinate(coordinate, orientace, i);
					if (canSetShip(coordinate, lc, human.getField())) {
						Ship ship = new Ship(coordinate, human.getField(), lc);
						human.getField().getShips().add(ship);
						setShip = true;
					} else {
						human.incorrectCoordinate();
						setShip = false;
					}
				} while (!setShip);
			}
		}
		human.getField().showField();
		for (int i = 1; i <= 4; i++) {
			for (int j = 5 - i; j >= 1; j--) {
				do {
					coordinate = robot.askCoordinateOfShip();
					orientace = robot.askOrientation();
					lc = createLastCoordinate(coordinate, orientace, i);
					if (canSetShip(coordinate, lc, robot.getField())) {
						Ship ship = new Ship(coordinate, robot.getField(), lc);
						robot.getField().getShips().add(ship);
						setShip = true;
					} else {
						robot.incorrectCoordinate();
						setShip = false;
					}
				} while (!setShip);
			}
		}
		robot.getField().showMap();
	}

	private void attack(User robot, User human) {
		Coordinate coordinate;
		do {
			coordinate = robot.move();
			if (human.getField().getCell(coordinate).getState() == CellStates.SUNKSHIP
					|| human.getField().getCell(coordinate).getState() == CellStates.SHOT) {
				robot.incorrectCoordinate();
				coordinate = robot.move();
			}
			if (checkMove(human.getField(), coordinate)) {
				robot.missShot();
			} else {
				robot.goodShot();
			}
			human.getField().showMap();
			if(endGame){
				System.out.println("My proigrali, komandir....");
				break;
			}
			coordinate = human.move();
			if (robot.getField().getCell(coordinate).getState() == CellStates.SUNKSHIP
					|| robot.getField().getCell(coordinate).getState() == CellStates.SHOT) {
				human.incorrectCoordinate();
				coordinate = human.move();
			}
			if (checkMove(robot.getField(), coordinate)) {
				human.missShot();
			} else {
				human.goodShot();
			}
			robot.getField().showField();
			if(endGame){
				System.out.println("My pobedili, komandir!");
				break;
			}

		} while (!endGame);
	}

	private boolean canSetShip(Coordinate c, Coordinate lc, SeaField field) {

		boolean canSetShip = true;

		if (lc.inRange()) {

			for (int i = Math.min(c.getX(), lc.getX()); i <= Math.max(c.getX(),
					lc.getX()); i++) {
				for (int j = Math.min(c.getY(), lc.getY()); j <= Math.max(
						c.getY(), lc.getY()); j++) {
					Coordinate temp = new Coordinate(i, j);
					if (field.getCell(temp).getState() != CellStates.WATER) {
						canSetShip = false;
						break;
					}
				}
			}
		} else {
			canSetShip = false;
		}
		return canSetShip;
	}

	private Coordinate createLastCoordinate(Coordinate c, int orient, int len) {
		int x, y;
		Coordinate lc = null;
		switch (orient) {
		case 1:
			x = c.getX() - len + 1;
			y = c.getY();
			lc = new Coordinate(x, y);
			break;
		case 4:
			x = c.getX();
			y = c.getY() + len - 1;
			lc = new Coordinate(x, y);
			break;
		case 3:
			x = c.getX() + len - 1;
			y = c.getY();
			lc = new Coordinate(x, y);
			break;
		case 2:
			x = c.getX();
			y = c.getY() - len + 1;
			lc = new Coordinate(x, y);
			break;
		}
		return lc;
	}

	private boolean checkMove(SeaField field, Coordinate c) {
		Cell cel;
		cel = field.getCell(c);
		boolean missShot = true;
		for (Ship item : field.getShips()) {

			if (item.getShip().contains(cel)) {

				cel.setState(CellStates.SUNKSHIP);
				item.getShip().remove(cel);
				missShot = false;
				if (item.getShip().isEmpty()) {
					item.setState(ShipStates.Sunk);
					field.getShips().remove(item);
					if (field.getShips().isEmpty()) {
						endGame = true;
						break;
					}
				}
				break;
			}
		}
		if (cel.getState() != CellStates.SUNKSHIP) {
			cel.setState(CellStates.SHOT);
		}
		if (field.getShips().isEmpty()) {
			endGame = true;
		}
		return missShot;
	}
}