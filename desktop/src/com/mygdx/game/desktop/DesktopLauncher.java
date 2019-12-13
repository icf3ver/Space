package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Window;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Space";
		config.width = 1300;
		config.height = 800;

		try {
			Thread.sleep(1000);
		} catch (Exception e) {

		}
		new LwjglApplication(new Window(), config);
	}
}

