package Data;
import static Helpers.Artist.*;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import Helpers.StateManager;
import Helpers.StateManager.GameState;
import UI.UI;


public class MainMenu {
	
	private Texture background;
	private UI menuUI;
	
	public MainMenu(){
		background = quickLoad("mainmenu");
		menuUI = new UI();
		menuUI.addButton("Play","play", WIDTH/2-128,(int) (HEIGHT * 0.45f));
		menuUI.addButton("Editor","edit", WIDTH/2-128,(int) (HEIGHT * 0.55f));
		menuUI.addButton("Quit","quit", WIDTH/2-128,(int) (HEIGHT * 0.65f));

	}
	
	//Check if a button is clicked by the user, then do something
	private void updateButtons() {
		if (Mouse.isButtonDown(0)){
			if (menuUI.isButtonClicked("Play")){
				StateManager.setState(GameState.GAME);
			}
			if (menuUI.isButtonClicked("Editor")){
				StateManager.setState(GameState.EDITOR);
			}
			if (menuUI.isButtonClicked("Quit")){
				System.exit(0);
			}
		}
	}
	
	public void update(){
		DrawQuadTex(background, 0,0,2048,1024);
		menuUI.draw();
		updateButtons();
	}

}
