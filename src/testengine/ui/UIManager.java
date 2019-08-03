package testengine.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;

public class UIManager {
	private ArrayList<UIObject> objects;
	
	public UIManager() {
		objects = new ArrayList<UIObject>();
	}
	
	public void tick() {
		for (UIObject o: objects) {
			if (o != null)
				o.tick();
		}
	}
	
	public void render(Graphics g) {
		for (UIObject o: objects) {
			if (o != null)
				o.render(g);
		}
	}
	
	public void onMouseMove(MouseEvent e) {
		for (UIObject o: objects) {
			if (o != null)
				o.onMouseMove(e);
		}
	}
	
	public void onMouseRelease(MouseEvent e) {
		for (UIObject o: objects) {
			if (o != null)
				o.onMouseRelease(e);
		}
	}
	
	public void onMousePress(MouseEvent e) {
		for (UIObject o: objects) {
			if (o != null)
				o.onMousePress(e);
		}
	}
	
	public void addObject (UIObject e) {
		objects.add(e);
	}
	
	public void removeObject(UIObject e) {
		objects.add(e);
	}
	
	public void addObjects(Collection<UIObject> objCollection) {
		for (UIObject e: objCollection) {
			objects.add(e);
		}
	}
	
	public void removeObjects(Collection<UIObject> objCollection) {
		for (UIObject e: objCollection) {
			objects.remove(e);
		}
	}
	
}
