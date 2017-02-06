package Data;

import org.newdawn.slick.opengl.Texture;
import static Helpers.Artist.*;

public enum TowerType {

	CannonRed(new Texture[] { quickLoad("cannonBase"), quickLoad("cannonGun") },ProjectileType.CannonBall, 10, 1000, 1),
	CannonBlue(new Texture[] { quickLoad("cannonBaseBlue"), quickLoad("cannonGunBlue") },ProjectileType.CannonBall, 30, 1000, 1), 
	CannonIce(new Texture[] {quickLoad("iceBase"), quickLoad("iceGun")},ProjectileType.IceBall,10,1000,1);

	Texture[] textures;
	ProjectileType projectileType;
	int damage, range;
	float firingSpeed;

	TowerType(Texture[] textures, ProjectileType projectileType,int damage, int range, float firingSpeed) {
		this.textures = textures;
		this.projectileType = projectileType;
		this.damage = damage;
		this.range = range;
		this.firingSpeed = firingSpeed;
	}
}
