package Data;

import static Helpers.Artist.quickLoad;

import org.lwjgl.input.Mouse;

import Helpers.Artist;
import UI.UI;

public class Game {

	private TileGrid grid;
	private Player player;
	private WaveManager waveManager;
	private UI towerPickerUI;

	public Game(int[][] map) {
		grid = new TileGrid(map);
		waveManager = new WaveManager(
				new Enemy(quickLoad("Enemy"), grid.getTile(5, 5), grid, Artist.TILE_SIZE, Artist.TILE_SIZE, 100, 25), 1,
				2);
		player = new Player(grid, waveManager);
		player.setup();
		setupUI();
	}

	private void setupUI() {
		towerPickerUI = new UI();
		towerPickerUI.addButton("CannonBlue", "cannonBlueButton", 0, 0);
		towerPickerUI.addButton("CannonIce", "iceCannonButton", 32, 0);
	}

	private void updateUI() {
		towerPickerUI.draw();
		if (Mouse.next()) {
			boolean mouseClicked = Mouse.isButtonDown(0);
			if (mouseClicked) {
				if (towerPickerUI.isButtonClicked("CannonBlue"))
				player.pickTower(new TowerCannonBlue(TowerType.CannonBlue, grid.getTile(0, 0),
						waveManager.getCurrentWave().getEnemyList()));
				if (towerPickerUI.isButtonClicked("CannonIce"))
						player.pickTower(new TowerIce(TowerType.CannonIce, grid.getTile(0, 0),
								waveManager.getCurrentWave().getEnemyList()));
			}
		}
	}

	public void update() {
		grid.draw();
		waveManager.update();
		player.update();
		updateUI();
	}
}
