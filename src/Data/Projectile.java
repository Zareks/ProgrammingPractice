package Data;

import static Helpers.Clock.*;
import static Helpers.Artist.*;

import org.newdawn.slick.opengl.Texture;

import Helpers.Artist;

public abstract class Projectile implements Entity {

	private Texture texture;
	private float x, y, speed, xVelocity, yVelocity;
	private int damage, width, height;
	private Enemy target;
	private boolean alive;

	public Projectile(ProjectileType type, Enemy target, float x, float y, int width, int height) {
		this.texture = type.texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = type.speed;
		this.damage = type.damage;
		this.target = target;
		this.alive = true;
		this.xVelocity = 0;
		this.yVelocity = 0;

		calculateDirection();
	}

	private void calculateDirection() {
		float totalAllowedMovement = 1.0f;
		float xDistanceFromTarget = Math.abs(target.getX() - x - Artist.TILE_SIZE / 4 + Artist.TILE_SIZE / 2);
		float yDistanceFromTarget = Math.abs(target.getY() - y - Artist.TILE_SIZE / 4 + Artist.TILE_SIZE / 2);
		float totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
		float xPercentOfMovement = xDistanceFromTarget / totalDistanceFromTarget;
		xVelocity = xPercentOfMovement;
		yVelocity = totalAllowedMovement - xPercentOfMovement;
		if (target.getX() < x)
			xVelocity *= -1;
		if (target.getY() < y)
			yVelocity *= -1;
	}

	public void damage() {
		target.damage(damage);
		alive = false;
	}

	public void update() {
		if (alive) {
			x += xVelocity * speed * Delta();
			y += yVelocity * speed * Delta();
			if (CheckCollision(x, y, width, height, target.getX(), target.getY(), target.getWidth(),
					target.getHeight()))
				damage();
			draw();
		}
	}

	public void draw() {
		DrawQuadTex(texture, x, y, Artist.TILE_SIZE / 2, Artist.TILE_SIZE / 2);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public Enemy getTarget(){
		return target;
	}
	
	public void setAlive(boolean status){
		this.alive = status;
	}
}
