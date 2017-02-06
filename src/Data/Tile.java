package Data;

import org.newdawn.slick.opengl.Texture;

import Helpers.Artist;

import static Helpers.Artist.*;

public class Tile {
	private float x,y;
	private int width,height;
	private Texture texture;
	private TileType type;
	
	public Tile(float x,float y, int width, int height, TileType type)
	{
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.type = type;
		this.texture=quickLoad(type.textureName);
	}
	
	public void draw(){
		DrawQuadTex(texture,x,y,width,height);
	}
	
	public int getXPlace(){
		return (int) x/Artist.TILE_SIZE;
	}
	
	public int getYPlace(){
		return (int) y/Artist.TILE_SIZE;
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

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}
	
}
