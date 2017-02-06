package Data;

import static Helpers.Artist.quickLoad;

import Helpers.Artist;

public class Game {

	private TileGrid grid;
	private Player player;
	private WaveManager waveManager;
	// Temp Variables

	public Game(int[][] map) {
		grid = new TileGrid(map);
		waveManager = new WaveManager(new Enemy(quickLoad("Enemy"), grid.getTile(5, 5), grid, Artist.TILE_SIZE, Artist.TILE_SIZE, 100, 25), 1, 2);
		player = new Player(grid, waveManager);
		player.setup();
	}

	public void update() {
		grid.draw();
		waveManager.update();
		player.update();
	}
}
