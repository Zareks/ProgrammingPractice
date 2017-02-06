package Data;


import org.newdawn.slick.opengl.Texture;
import static Helpers.Artist.*;

public enum ProjectileType {

	CannonBall(quickLoad("bullet"),10,500), IceBall(quickLoad("bulletIce"),6,450);
	
	
	Texture texture;
	int damage;
	float speed;

	ProjectileType(Texture texture, int damage, float speed) {
		this.texture = texture;
		this.damage = damage;
		this.speed = speed;
	}
}
