package com.amadornes.lib.gui.component;

import com.amadornes.lib.gui.Appearance;
import com.amadornes.lib.render.RenderHelper;

public class ComponentButton extends Component implements ISize {

	protected int width, height;

	private boolean isHovering = false;

	public ComponentButton(int x, int y, int width, int height) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}

	@Override
	public void render(Appearance appearance, int mx, int my, float f) {
		if (mx >= x && mx < x + width && my >= y && my < y + height) {
			isHovering = true;
		} else {
			isHovering = false;
		}

		// Border
		RenderHelper.drawColoredRect(x - 1, y - 1, width + 2, 1, appearance.getBorderColor());
		RenderHelper.drawColoredRect(x - 1, y + height, width + 2, 1, appearance.getBorderColor());
		RenderHelper.drawColoredRect(x - 1, y, 1, height, appearance.getBorderColor());
		RenderHelper.drawColoredRect(x + width, y, 1, height, appearance.getBorderColor());
		// Inside
		if (isHovering) {
			RenderHelper.drawColoredRect(x, y, width, height, appearance.getButtonHoverColor());
		} else {
			RenderHelper.drawColoredRect(x, y, width, height, appearance.getComponentColor());
		}
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public void onMouseClick(int x, int y, int btn) {

	}

}
