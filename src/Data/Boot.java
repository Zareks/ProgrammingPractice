package Data;

import static Helpers.Artist.beginSession;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import Helpers.Clock;
import Helpers.StateManager;

public class Boot {

	public Boot() {
		
		
		//call static method in artist class to init OpenGL calls
		beginSession();
		
		
		//Main game loop
		while (!Display.isCloseRequested()) {
			Clock.update();
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			StateManager.update();
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	public static void main(String[] args) {
		new Boot();
	}
}
