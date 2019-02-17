package bots;
import elf_kingdom.*;
import java.util.*;

public class MyBot implements SkillzBot {
	
	static ArrayList<Location> bunkerManaFLoocation = null, bunkerPortalsLocation = null;
	static boolean bunker;
	
	@Override
	public void doTurn(Game game){
		// TODO Auto-generated method stub
		if(game.turn == 1) setup(game);
		
		if (bunker) bunker(game);
		
		
	}
	
	public static void setup(Game game) {
		bunker = true;
	}
	
	public static boolean bunker(Game game) {
		
		return true;
	}
	
	
	

}
