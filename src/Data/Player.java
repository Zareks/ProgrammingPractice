package Data;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import Helpers.Clock;

import static Helpers.Artist.*;

import java.util.ArrayList;

public class Player {
	private TileGrid grid;
	private TileType[] types;
	private WaveManager waveManager;
	private ArrayList<Tower> towerList;
	private boolean leftMouseButtonDown, rightMouseButtonDown, holdingTower;
	private Tower tempTower;
	public static int Cash, Lives;

	public Player(TileGrid grid, WaveManager waveManager) {
		this.grid = grid;
		this.types = new TileType[3];
		this.types[0] = TileType.Grass;
		this.types[1] = TileType.Dirt;
		this.types[2] = TileType.Water;
		this.waveManager = waveManager;
		this.towerList = new ArrayList<Tower>();
		this.leftMouseButtonDown = false;
		this.rightMouseButtonDown = false;
		this.holdingTower = false;
		this.tempTower = null;
		Cash = 0;
		Lives = 0;
	}

	// init lives and cash values for player
	public void setup() {
		Cash = 50;
		Lives = 10;
	}

	public static boolean modifyCash(int amount) {
		if (Cash + amount >= 0) {
			Cash += amount;
			return true;
		}
		return false;
	}

	public static void modifyLives(int amount) {
		Lives += amount;
	}

	public void update() {

		if (holdingTower) {
			tempTower.setX(getMouseTile().getX());
			tempTower.setY(getMouseTile().getY());
			tempTower.draw();
		}
		// update all towers in game
		for (Tower t : towerList) {
			t.update();
			t.draw();
			t.updateEnemtList(waveManager.getCurrentWave().getEnemyList());
		}
		// handle mouse input
		if (Mouse.isButtonDown(0) && !leftMouseButtonDown) {
			placeTower();
		}

		if (Mouse.isButtonDown(1) && !rightMouseButtonDown) {
			
		}

		leftMouseButtonDown = Mouse.isButtonDown(0);
		rightMouseButtonDown = Mouse.isButtonDown(1);

		// Handle keyboard input
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
				Clock.ChangeMultiplier(0.2f);
				;
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()) {
				Clock.ChangeMultiplier(-0.2f);
				;
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_T && Keyboard.getEventKeyState()) {

			}
		}
	}

	public void pickTower(Tower t) {
		tempTower = t;
		holdingTower = true;
	}

	private void placeTower() {
		if (holdingTower) {
			if (modifyCash(-tempTower.getCost())) {
				towerList.add(tempTower);
			}
		}
		holdingTower = false;
		tempTower = null;

	}

	private Tile getMouseTile() {
		return grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE);
	}
}
