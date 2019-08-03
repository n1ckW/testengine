package testengine.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import testengine.Game;
import testengine.gfx.Assets;
import testengine.ui.ClickListener;
import testengine.ui.ImageButton;
import testengine.ui.UIManager;

public class MenuState implements State {
	private UIManager uiManager;
	
	public MenuState(Game game) {
		uiManager = new UIManager();	
		
		uiManager.addObject(new ImageButton(50, 50, 64, 32, Assets.btn_start, new ClickListener() {
			@Override
			public void onClick() {
				game.setCurrentState(game.getWorldState());
			}			
		}));
	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}

	@Override
	public void onMousePress(MouseEvent e) {
		uiManager.onMousePress(e);
	}

	@Override
	public void onMouseRelease(MouseEvent e) {
		uiManager.onMouseRelease(e);		
	}

	@Override
	public void onMouseMove(MouseEvent e) {
		uiManager.onMouseMove(e);
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
