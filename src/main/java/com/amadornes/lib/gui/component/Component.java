package com.amadornes.lib.gui.component;

import com.amadornes.lib.gui.Appearance;

public abstract class Component {

	protected int x, y;

	public abstract void render(Appearance appearance, int mx, int my, float f);

	public Appearance getCustomAppearance() {
		return null;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void onMouseClick(int x, int y, int btn) {
	}

	public void onKeyPress(int key, char c) {
	}

}
