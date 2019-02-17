package bots;
import java.util.ArrayList;

import elf_kingdom.*;

public class SkillzLib {
	
	/*
	 * comments
	 */
	
	/*
	 * constractors  : Location(int : y, int : x){
	 * 
	 */
	
	/*
	 * comments
	 */
	
	public static Location copyAndGetLocation(Location location) {
		return new Location(location.row, location.col);
	}
	
	
	public int howManyObjectsAroundLocation(Game game, Location location, GameObject[] objects, int range) {
    	int count=0;
    	for(GameObject object: objects) {
    		if(object.inRange(location, range))
    			count++;
    	}
    	return count;
    }
    public Portal returnClosestPortal(Location l, Portal[] stock) {
        int minDist = Integer.MAX_VALUE;
        Portal minPortal = null;
        for(Portal p : stock) {
            if (p.location.distance(l)<minDist) {
                minDist = p.location.distance(l);
                minPortal = p;
            }
        }
        return minPortal;
    }

    public Portal getClosestPortal(Game game, Castle enemyCastle){
		Portal[] portals = game.getMyPortals();
		Portal closest = null;
		if(portals.length > 0){
			int dist = portals[0].distance(enemyCastle);
			closest = portals[0];
			for(int i = 1; i<game.getMyPortals().length; i++){
				if(portals[i].distance(enemyCastle) < dist){
					dist = portals[i].distance(enemyCastle);
					closest = portals[i];
				}
			}
		}else{
			System.out.println("NO PORTALS! CRASH NOW");
			
		}
		return closest;
	}
    
    public static ArrayList<Location> getMyBunkerMFLocs(Game game){
        int radius = game.castleSize+ game.portalSize;
        Location middle = new Location(game.rows/2, game.cols/2);
        Location myCastle = new Location(game.getMyCastle().location.row, game.getMyCastle().location.col);
        int deltaR = myCastle.row - middle.row;
        int deltaC = myCastle.col - middle.col;
        double theta;
        if(deltaC == 0)
            theta = Math.PI/2;
        else
            theta = Math.atan(deltaR/deltaC);

        if(deltaR < 0)
            theta += Math.PI;

        ArrayList<Location> locs = new ArrayList<Location>();
        int row1, col1;
        for(int i = 0; i < 3; i++) {
            row1 = (int) (radius * Math.sin(theta - (i-1) * Math.PI/4)) + 1;
            col1 = (int) (radius * Math.cos(theta - (i-1) * Math.PI/4)) + 1;
            locs.add(new Location(row1, col1));
        }
        return locs;
    }
    
    public static ArrayList<Location> getMyBunkerPLocs(Game game) {
		
    	return null;
	}
	
	
	
	
}
