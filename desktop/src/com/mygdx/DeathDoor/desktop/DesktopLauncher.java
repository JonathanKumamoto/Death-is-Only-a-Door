package com.mygdx.DeathDoor.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.DeathDoor.DeathDoorGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1088;
		config.height = 544;
		new LwjglApplication(new DeathDoorGame(), config);
	}
}
