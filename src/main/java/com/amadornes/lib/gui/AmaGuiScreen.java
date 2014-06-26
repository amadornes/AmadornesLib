package com.amadornes.lib.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.opengl.GL11;

import com.amadornes.lib.gui.component.Component;

public class AmaGuiScreen extends GuiScreen {

	private List<Component> components = new ArrayList<Component>();
	private Appearance appearance = Appearance.DEFAULT;

	public void addComponent(Component component) {
		this.components.add(component);
	}

	public void setAppearance(Appearance appearance) {
		this.appearance = appearance;
	}

	public Appearance getAppearance() {
		return appearance;
	}

	@Override
	public void drawScreen(int mx, int my, float f) {
		this.drawGradientRect(0, 0, this.width, this.height, 0xC010100F, 0xD010100F);
		super.drawScreen(mx, my, f);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		for (Component c : components) {
			GL11.glPushMatrix();
			c.render(appearance, mx, my, f);
			GL11.glPopMatrix();
		}
		GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	protected void keyTyped(char par1, int par2) {
		super.keyTyped(par1, par2);
		
		for (Component c : components)
			c.onKeyPress(par2, par1);
	}

	@Override
	protected void mouseClicked(int mx, int my, int btn) {
		for (Component c : components)
			c.onMouseClick(mx, my, btn);
	}

}
