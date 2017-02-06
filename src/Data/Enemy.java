package Data;

import org.newdawn.slick.opengl.Texture;

import static Helpers.Artist.*;
import static Helpers.Clock.*;

import java.util.ArrayList;
public class Enemy implements Entity{
	private int width,height,currentCheckpoint;
	private float speed,x,y, health, startHealth;
	private Texture texture,healthBackground,healthForeground,healthBorder;
	private Tile startTile;
	private TileGrid grid;
	private boolean first = true, alive = true;
	
	private ArrayList<Checkpoint> checkpoints;
	private int[] directions;
	
	public Enemy(Texture texture,Tile startTile,TileGrid grid, int width, int height, float speed, float health){
		this.texture = texture;
		this.healthBackground = quickLoad("healthBackground");
		this.healthForeground = quickLoad("healthForeground");
		this.healthBorder     = quickLoad("healthBorder");
		this.x = startTile.getX();
		System.out.println(x);
		this.y = startTile.getY();
		System.out.println(y);
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.health = health;
		this.startHealth = health;
		this.startTile = startTile;
		this.grid = grid;
		
		this.checkpoints = new ArrayList<Checkpoint>();
		this.directions = new int[2];
		//[0] = x | [1] = y
		this.directions[0] = 0;
		this.directions[1] = 0;
		this.currentCheckpoint = 0;
		populateCheckpointList();
	}
	
	private void populateCheckpointList(){
		checkpoints.add(findNextC(startTile,directions = findNextDir(startTile)));
		
		
		int counter = 0;
		boolean cont = true;
		while (cont){
			int[] currentD = findNextDir(checkpoints.get(counter).getTile());
			//Check if a next dir/check exists, end after 20 checkpoints (arbitrary)
			if (currentD[0] == 2 || counter == 20){
				cont = false;
			}else{
				checkpoints.add(findNextC(checkpoints.get(counter).getTile(), 
						directions = findNextDir(checkpoints.get(counter).getTile())));
			}
			counter ++;
		}
	}
	
	private Checkpoint findNextC(Tile s, int[] dir){
		Tile next = null;
		Checkpoint c = null;
		//Boolean to decide if CP is found
		boolean found = false;
		// counts loops
		int counter = 1;
		while (!found){
			if (s.getXPlace()+ dir[0]*counter == grid.getTileswide() || 
					s.getYPlace() + dir[1] * counter == grid.getTileshigh() ||
					s.getType() != grid.getTile(s.getXPlace()+ dir[0]*counter, 
					s.getYPlace()+dir[1]*counter).getType()){
				found = true;
				//move back one tile
				counter -= 1;
				next =  grid.getTile(s.getXPlace()+ dir[0]*counter, s.getYPlace()+dir[1]*counter);
			}
			counter++;
		}
	c = new Checkpoint(next, dir[0], dir[1]);
	return c;
	}
	
	private int[] findNextDir(Tile s){
		int[] dir = new int[2];
		Tile u = grid.getTile(s.getXPlace(),s.getYPlace() - 1);
		Tile r = grid.getTile(s.getXPlace()+1, s.getYPlace());
		Tile d = grid.getTile(s.getXPlace(), s.getYPlace()+1);
		Tile l = grid.getTile(s.getXPlace()-1, s.getYPlace());
		
		if (s.getType() == u.getType() && directions[1] != 1){
			dir[0]= 0;
			dir[1]=-1;
		}else if(s.getType() == r.getType() && directions[0] != -1){
			dir[0]= 1;
			dir[1]= 0;
		}else if(s.getType() == d.getType() && directions[1] != -1){
			dir[0]= 0;
			dir[1]= 1;
		}else if (s.getType() == l.getType() && directions[0] != 1){
			dir[0]=-1;
			dir[1]= 0;
		} else {
			dir[0]= 2;
			dir[1]= 2;
		}
		
		return dir;
	}
	
	public void update(){
		System.out.println(speed);
		if (first)
			first = false;
		else{
			if (checkpointReached()){
				if (currentCheckpoint + 1 == checkpoints.size())
					endOfMazeReached();
				else
					currentCheckpoint++;
			}else {
				x += Delta() * checkpoints.get(currentCheckpoint).getxDir() * speed;
				y += Delta() * checkpoints.get(currentCheckpoint).getyDir() * speed;
			}
		}
	}
	
	private void endOfMazeReached(){
		Player.modifyLives(-1);
		die();
	}
	
	private boolean checkpointReached(){
		boolean reached = false;
		Tile t = checkpoints.get(currentCheckpoint).getTile();
		//check if position reached tile within variance of 3 (arbitrary)
		
		if (x> t.getX() - 3 && 
				x < t.getX() + 3 &&
				y > t.getY() - 3 &&
				y < t.getY() + 3){
			reached = true;
			x = t.getX();
			y = t.getY();
		}
		return reached;
	}
	
	private void die() {
		alive = false;
	}
	
	public void damage(int amount){
		health -= amount;
		if (health <= 0){
			die();
			Player.modifyCash(5);
		}
	}
	
	public void draw(){
		float healthPercentage = health/startHealth;
		DrawQuadTex(texture,x,y,width,height);
		DrawQuadTex(healthBackground,x,y - (TILE_SIZE/4),width,TILE_SIZE/8);
		DrawQuadTex(healthForeground,x,y - (TILE_SIZE/4),TILE_SIZE*healthPercentage,TILE_SIZE/8);
		DrawQuadTex(healthBorder,x,y - (TILE_SIZE/4),width,TILE_SIZE/8);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Tile getStartTile() {
		return startTile;
	}

	public void setStartTile(Tile startTile) {
		this.startTile = startTile;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}
	
	public TileGrid getTileGrid(){
		return grid;
	}
	
	public boolean isAlive(){
		return alive;
	}
}
