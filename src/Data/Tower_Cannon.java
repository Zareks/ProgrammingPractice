package Data;

import static Helpers.Artist.DrawQuadTex;
import static Helpers.Artist.DrawQuadTexRot;
import static Helpers.Artist.quickLoad;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class Tower_Cannon {

	private float x, y, angle;
	private int width, height;
	private Texture baseTexture, cannonTexture;
	public Tower_Cannon(Texture baseTexture, Tile startTile, int damage, int range, ArrayList<Enemy> enemies) {
		this.baseTexture = baseTexture;
		this.cannonTexture = quickLoad("cannonGun");
		this.x = startTile.getX();
		System.out.println("cannonx= " + x);
		this.y = startTile.getY();
		this.width = (int) startTile.getWidth();
		this.height = (int) startTile.getHeight();
		new ArrayList<Projectile>();
	}
	
	public void draw() {
		DrawQuadTex(baseTexture, x, y, width, height);
		DrawQuadTexRot(cannonTexture, x, y, width, height, angle);
	}
}
