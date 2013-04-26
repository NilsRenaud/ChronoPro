package org.ChronoPro.config;

import java.awt.Font;
import java.io.File;

public class DisplayConstants {
	
	private static final String FONT_CHRONO_FILE = "./font/dotted.ttf";
	
	public static Font fontChrono;
	
	static{
		try {
			fontChrono = Font.createFont(Font.TRUETYPE_FONT, new File(FONT_CHRONO_FILE));
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		fontChrono = fontChrono.deriveFont(Font.PLAIN, 50);
	}
	
}
