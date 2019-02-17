package bots;
import elf_kingdom.*;
import java.util.*;

public class MyBot implements SkillzBot {
	static Elf bunkerBuilderElf;
	static ArrayList<Location> bunkerManaFLoocation = null, bunkerPortalsLocation = null;
	static boolean bunkerFlag;
	
	@Override
	public void doTurn(Game game){
		// TODO Auto-generated method stub
		if(game.turn == 1) setup(game);
		
		bunkerFlag = buildBunker(game, bunkerBuilderElf);
		if (bunkerFlag) bunker(game);
		
		
	}
	
	public static void setup(Game game) {
		bunkerBuilderElf = game.getMyLivingElves()[0];
		/**
		 * bunker global variables;
		 */
		bunkerManaFLoocation = SkillzLib.getMyBunkerMFLocs(game);
		bunkerPortalsLocation = SkillzLib.getMyBunkerPLocs(game);
	}
	
	public static boolean buildBunker(Game game, Elf builderElf) {
		if(! bunkerManaFLoocation.isEmpty()) {
			//if the main queue is still empty - the MF are not finished
			if(bunkerManaFLoocation.size() != bunkerPortalsLocation.size()) {
				// Basically says that now we need to take care the MF
				if(builderElf.getLocation().equals(bunkerManaFLoocation.get(0))) {
					// in case of being at the location
					if(builderElf.canBuildManaFountain()) {
						builderElf.buildManaFountain();
						System.out.println("build mana f");
						bunkerManaFLoocation.remove(bunkerManaFLoocation.get(0));
						System.out.println("out of list (rotman)");
					}
				}
				else {
					if (!builderElf.alreadyActed) 
						builderElf.moveTo(bunkerManaFLoocation.get(0));
						System.out.println("move to build mana F");
				}
					
			}else {
				// Basically says that now we need to take care the portals
				if(builderElf.getLocation().equals(bunkerPortalsLocation.get(0))) {
					// in case of being at the location
					if(builderElf.canBuildPortal()) {
						builderElf.buildPortal();
						System.out.println("build portal");
						bunkerPortalsLocation.remove(bunkerPortalsLocation.get(0));
						System.out.println("out of list (rotman)");
					}
				}
				else {
					if (!builderElf.alreadyActed) 
						builderElf.moveTo(bunkerPortalsLocation.get(0));
						System.out.println("move to build portal");
				}
			}
			
			return false;
		}
		else return true;
	}

	public static boolean bunker(Game game) {
		return true;
	}
	
	

}
