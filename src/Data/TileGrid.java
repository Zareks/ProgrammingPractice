package Data;

import Helpers.Artist;

public class TileGrid {
	public Tile[][] map;
	private int tileswide,tileshigh;
	
	//creates empty(grass) map when passed to args
	public TileGrid(){
		this.tileswide = 20;
		this.tileshigh = 12;
		map = new Tile[tileswide][tileshigh];
		for (int i = 0;i<map.length;i++){
			for (int j = 0;j< map[i].length;j++){
				map[i][j] = new Tile(i*Artist.TILE_SIZE,j*Artist.TILE_SIZE,Artist.TILE_SIZE,Artist.TILE_SIZE,TileType.Grass);
			}
		}
	}
	//creates specific map that is passed by args
	public TileGrid(int[][] newMap){
		this.tileswide = newMap[0].length;
		this.tileshigh = newMap.length;
		map = new Tile[20][12];
		for (int i=0;i<map.length;i++){
			for (int j=0;j<map[i].length;j++){
				switch (newMap[j][i]){
				case 0:
					map[i][j] = new Tile(i*Artist.TILE_SIZE,j*Artist.TILE_SIZE,Artist.TILE_SIZE,Artist.TILE_SIZE,TileType.Grass);
					break;
				case 1:
					map[i][j] = new Tile(i*Artist.TILE_SIZE,j*Artist.TILE_SIZE,Artist.TILE_SIZE,Artist.TILE_SIZE,TileType.Dirt);
					break;
				case 2:
					map[i][j] = new Tile(i*Artist.TILE_SIZE,j*Artist.TILE_SIZE,Artist.TILE_SIZE,Artist.TILE_SIZE,TileType.Water);
					break;
				}
			}
		}
	}
	
	public void setTile(int xCoord,int yCoord, TileType type){
		map[xCoord][yCoord] = new Tile(xCoord*Artist.TILE_SIZE,yCoord*Artist.TILE_SIZE,Artist.TILE_SIZE,Artist.TILE_SIZE,type);
	}
	
	public Tile getTile(int xPlace, int yPlace){
		if(xPlace<tileswide && yPlace < tileshigh && xPlace > -1 && yPlace > -1){
			return map[xPlace][yPlace];
		}else{
			return new Tile(0,0,0,0,TileType.NULL);
		}
	}
	
	public void draw(){
		for (int i=0;i<map.length;i++){
			for (int j=0;j<map[i].length;j++){
				map[i][j].draw();
			}
		}
	}

	public int getTileswide() {
		return tileswide;
	}

	public void setTileswide(int tileswide) {
		this.tileswide = tileswide;
	}

	public int getTileshigh() {
		return tileshigh;
	}

	public void setTileshigh(int tileshigh) {
		this.tileshigh = tileshigh;
	}
	
	
}
