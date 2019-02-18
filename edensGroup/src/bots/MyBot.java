package bots;
import elf_kingdom.*;

import java.util.*;

public class MyBot implements SkillzBot {
	
	public static final int MAX_MANA_FOR_ATTACK = 300;
	public static int HALF_OF_ALACHSON;
	
	static Elf bunkerBuilderElf;
	static ArrayList<Elf> allMyInitialElfForBunkering; // ask yuval
	static ArrayList<Location> bunkerManaFLoocation = null, bunkerPortalsLocation = null;
	static boolean buildingBunkerFlag, GlobalBunkerFlagForAttacking;
	
	@Override
	public void doTurn(Game game){
		// TODO Auto-generated method stub
		if(game.turn == 1) setup(game);
		
		if(GlobalBunkerFlagForAttacking) {
		// the bunker stuff
		buildingBunkerFlag = buildBunker(game, bunkerBuilderElf);
		bunker(game, buildingBunkerFlag);
		
		
		boolean attackCondition = false; // just for have attack condition
		if((game.getMyManaFountains().length >= 3 && ((game.getAllEnemyElves().length > game.getEnemyLivingElves().length)|| game.getMyMana() >= MAX_MANA_FOR_ATTACK)) || game.turn == 300) {
			GlobalBunkerFlagForAttacking = false;
			System.out.println("Let's attack");
		}
		
		}else {
			// the Attacking mitparetzet stuff
			AttackingMode(game);
			
		}
		
		
	}
	
	public static void setup(Game game) {
		GlobalBunkerFlagForAttacking = true;
		
		/**
		 * Elves #defining
		 */
		allMyInitialElfForBunkering = SkillzLib.convertArray2List(game.getMyLivingElves());
		bunkerBuilderElf = allMyInitialElfForBunkering.remove(0); 
		
		/**
		 * bunker global variables;
		 */
		bunkerManaFLoocation = SkillzLib.getMyMFLocs(game);
		bunkerManaFLoocation = SkillzLib.fllipArrayList(bunkerManaFLoocation);
		System.out.println(bunkerManaFLoocation);
		
		bunkerPortalsLocation = SkillzLib.getMyPortalLocs(game);
		bunkerPortalsLocation = SkillzLib.fllipArrayList(bunkerPortalsLocation);
		System.out.println(bunkerPortalsLocation);
	}
	
	public static boolean buildBunker(Game game, Elf builderElf) {
		if(builderElf.isAlive()) {
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
		else
			return false;
	}

	public static void bunker(Game game, boolean buildingFlag) {
		if(buildingFlag == false) {
			for(Elf myUnBuilderElves : allMyInitialElfForBunkering) {
				SkillzLib.elfScout(game, myUnBuilderElves);
			}
			for(Portal defencePortal : game.getMyPortals()) {
				SkillzLib.defendBunker(game, defencePortal);
			}
		}else {
			for (Elf elf : game.getMyLivingElves()) {
				SkillzLib.elfScout(game, elf);
			}
			for(Portal portal : game.getMyPortals()) {
				SkillzLib.defendBunker(game, portal);
			}
		}
	}
	
	
	public static void AttackingMode(Game game) {
		
		ArrayList<Portal> myDefPortals = SkillzLib.getPortalsInRange(SkillzLib.convertArray2List(game.getMyPortals()), game.getMyCastle().getLocation(), SkillzLib.getHalfOfAlachson(game));
		for(Portal p: myDefPortals) {
			SkillzLib.defendBunker(game, p);
		}
		
		for(Elf elf : game.getMyLivingElves()) {
			SkillzLib.allOutMode(game, elf);
		}
		
		ArrayList<Portal> myAttackPortals = SkillzLib.getPortalsInRange(SkillzLib.convertArray2List(game.getMyPortals()), game.getEnemyCastle().getLocation(), SkillzLib.getHalfOfAlachson(game));
		/**
		 * handle attack portals
		 */
		handleAttackPortals(myAttackPortals);
	}
	
	
	public static void handleAttackPortals(ArrayList<Portal> attackPortals) {
		for (Portal portal : attackPortals) {
			handleAttackPortal(portal);
		}
	}
	
	/**
	 * need implementation
	 * @param p
	 */
	public static void handleAttackPortal(Portal p) {
		if(p.canSummonLavaGiant())
			p.summonLavaGiant();
	}
	
}
