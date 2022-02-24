package Scanner;

import java.util.ArrayList;

public class Constantes {
	
	
	public String DEFVAR = "defvar";
	
	public String BLOCKED = "blocked-p";
	
	public String MOVE = "move";
	
	public String TURN = "turn";
	
	public String FACE = "face";
	
	public String PUT = "put";
	
	public String PICK = "pick";
	
	public String MOVEDIR = "move-dir";
	
	public String RUN = "run-dirs";
	
	public String MOVEFACE = "move-face";
	
	public String SKIP = "skip";
	
	public String IF = "if";
	
	public String LOOP = "loop";
	
	public String DEFUN = "defun";

	public String FACING = "facing-p";
	
	public String CANPUT = "can-put-p";
	
	public String CANPICK = "can-pick-p";
	
	public  String CANMOVE = "can-move-p";
	
	public String REPEAT = "repeat";
	
	public String NOT = "not";
	
	public String ROTATE= "rotate";
	
	public String FOO = "foo";
	
	public String BALLOONS = "Balloons";
	
	public String CHIPS= "Chips";
	
	public String GOEND = "goend";
	
	public String ONE = "one";
	
	public String LEFT = "left";
	
	public String FRONT = "front";

	public String BACK = "back";
	
	public String RIGHT = "right";
	
	public String NORTH = "north";
	
	public ArrayList<String> comandos;
		
	public Constantes()
	{
		comandos= new ArrayList<String>(); 
		for(int i=0; i<10; i++) {
			comandos.add(Integer.toString(i));
		}
		comandos.add("(");
		comandos.add(")");
		comandos.add("c");
		comandos.add("p");
		comandos.add(DEFVAR);
		comandos.add(BLOCKED);
		comandos.add(MOVE);
		comandos.add(TURN);
		comandos.add(FACE);
		comandos.add(PUT);
		comandos.add(PICK);
		comandos.add(MOVEDIR);
		comandos.add(RUN);
		comandos.add(MOVEFACE);
		comandos.add(SKIP);
		comandos.add(IF);
		comandos.add(LOOP);
		comandos.add(DEFUN);
		comandos.add(FACING);
		comandos.add(CANPUT);
		comandos.add(CANPICK);
		comandos.add(CANMOVE);
		comandos.add(REPEAT);
		comandos.add(NOT);
		comandos.add(ROTATE);
		comandos.add(FOO);
		comandos.add(BALLOONS);
		comandos.add(CHIPS);
		comandos.add(GOEND);
		comandos.add(ONE);
		comandos.add(LEFT);
		comandos.add(FRONT);
		comandos.add(BACK);
		comandos.add(RIGHT);
		comandos.add(NORTH);
	}
	
	public boolean validacion(String palabra) {
		return comandos.contains(palabra);
	}
	
}
