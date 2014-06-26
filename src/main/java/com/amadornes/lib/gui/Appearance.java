package com.amadornes.lib.gui;

import com.amadornes.lib.color.Color;

public abstract class Appearance {

	public static final Appearance DEFAULT;

	public abstract Color getBorderColor();

	public abstract Color getComponentColor();

	public Color getButtonColor() {
		return getComponentColor();
	}

	public abstract Color getButtonHoverColor();

	public abstract Color getButtonClickColor();

	public abstract Color getButtonOnColor();

	static {
		DEFAULT = new Appearance() {

			@Override
			public Color getComponentColor() {
				return new Color(0.2, 0.2, 0.2, 0.7);
			}

			@Override
			public Color getButtonOnColor() {
				return new Color(0.8, 0.8, 0.0, 0.7);
			}

			@Override
			public Color getButtonHoverColor() {
				return new Color(0.8, 0.8, 0.0, 0.7);
			}

			@Override
			public Color getButtonClickColor() {
				return null;
			}

			@Override
			public Color getBorderColor() {
				return new Color(0.62745098039, 0.62745098039, 0.62352941176);
			}
		};
	}

}
