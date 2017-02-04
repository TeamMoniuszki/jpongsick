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
		config.addIcon("128x128.png", Files.FileType.Internal);
		config.addIcon("64x64.png", Files.FileType.Internal);
		config.addIcon("32x32.png", Files.FileType.Internal);
		config.addIcon("16x16.png", Files.FileType.Internal);

//        config.fullscreen = true;
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new JPongSick(), config);
	}
}
