package com.jpongsick.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.jpongsick.game.Config;
import com.jpongsick.game.JPongSick;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "JPongSICK";
		config.addIcon("JPongSICK_icon.png", Files.FileType.Internal);
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new JPongSick(), config);
	}
}
