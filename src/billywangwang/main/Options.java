package billywangwang.main;

import java.util.HashMap;
import billywangwang.main.util.FileUtils;

public class Options {
	
	public static int 		RES_WIDTH, RES_HEIGHT;
	
	public static int 		KEY_MOVEUP, KEY_MOVELEFT, KEY_MOVEDOWN, KEY_MOVERIGHT;
	
	public static double 	WINDOW_SCALE_X, WINDOW_SCALE_Y;
	
	public Options(){
		try{
			HashMap<String, HashMap<String, String>> iniFile = FileUtils.loadIni("options.ini");
			RES_WIDTH = Integer.parseInt(iniFile.get("screen").get("width"));
			RES_HEIGHT = Integer.parseInt(iniFile.get("screen").get("height"));
			Game.frame.setSize(RES_WIDTH, RES_HEIGHT);
			KEY_MOVEUP = Integer.parseInt(iniFile.get("keys").get("up"));
			KEY_MOVELEFT = Integer.parseInt(iniFile.get("keys").get("left"));
			KEY_MOVEDOWN = Integer.parseInt(iniFile.get("keys").get("down"));
			KEY_MOVERIGHT = Integer.parseInt(iniFile.get("keys").get("right"));
			
			WINDOW_SCALE_X = RES_WIDTH / Game.WIDTH;
			WINDOW_SCALE_Y = RES_HEIGHT / Game.HEIGHT;
			
			System.out.println(WINDOW_SCALE_X + ", " + WINDOW_SCALE_Y);
			
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
}
