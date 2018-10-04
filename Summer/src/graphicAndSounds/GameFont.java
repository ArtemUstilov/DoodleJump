package graphicAndSounds;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import engine.UsableM;

public class GameFont {
	static Font u;
	public static Font getFont(float f){
		if(u==null){
		try {
			u = Font.createFont(Font.TRUETYPE_FONT, new File(UsableM.getPathToFont())	);
		} catch (FontFormatException | IOException e1) {
			e1.printStackTrace();
		}}
		return u.deriveFont(f);
	}
}
