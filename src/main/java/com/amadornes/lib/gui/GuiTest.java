package com.amadornes.lib.gui;

import com.amadornes.lib.gui.component.ComponentButton;

public class GuiTest extends AmaGuiScreen {

	@Override
	public void initGui() {
		addComponent(new ComponentButton(10, 10, 130, 15));
	}

}
