package Data;

import static Helpers.Artist.DrawQuadTex;
import static Helpers.Artist.DrawQuadTexRot;
import static Helpers.Artist.quickLoad;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class Tower_Cannon {

	private float x, y, timeSinceLastShot, firingSpeed, angle;
	private int width, height, damage , range;
	private Texture baseTexture, cannonTexture;
	private Tile startTile;
	private ArrayList<Projectile> projectiles;
	private ArrayList<Enemy> enemies;
	private Enemy target;
	private boolean targeted;

	public Tower_Cannon(Texture baseTexture, Tile startTile, int damage, int range, ArrayList<Enemy> enemies) {
		this.baseTexture = baseTexture;
		this.cannonTexture = quickLoad("cannonGun");
		this.startTile = startTile;
		this.x = startTile.getX();
		System.out.println("cannonx= " + x);
		this.y = startTile.getY();
		this.damage = damage;
		this.range = range;
		this.width = (int) startTile.getWidth();
		this.height = (int) startTile.getHeight();
		this.firingSpeed = 1;
		this.timeSinceLastShot = 0;
		this.projectiles = new ArrayList<Projectile>();
		this.enemies = enemies;
		this.targeted = false;
	}
	

	

	


	

	




	public void draw() {
		DrawQuadTex(baseTexture, x, y, width, height);
		DrawQuadTexRot(cannonTexture, x, y, width, height, angle);
	}
}
