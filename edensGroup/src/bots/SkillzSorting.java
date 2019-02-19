package bots;
import java.util.*;

import elf_kingdom.*;
public class SkillzSorting {

	public static ArrayList<MapObject> getSortArrayListOnDistFromLoc(ArrayList<MapObject> arrayList, Location loc){
		/**
		 * return the same array list just sorter
		 * the had of the array list is the closest object to the loction in the args
		 */
		PriorityQueue<MapObjectWrraper> queue = new PriorityQueue<>();
		for(MapObject obj : arrayList) {
			queue.offer(new MapObjectWrraper(obj, loc));
		}
		ArrayList<MapObject> sortArrayList = new ArrayList<>();
		while (!queue.isEmpty()) {
			sortArrayList.add(queue.poll().obj);
		}
		
		return sortArrayList;
	}
}




class MapObjectWrraper implements Comparable<MapObjectWrraper>{
	public MapObject obj;
	public Location target;
	
	public MapObjectWrraper(MapObject obj, Location target) {
		super();
		this.obj = obj;
		this.target = target;
	}

	@Override
	public int compareTo(MapObjectWrraper arg0) {
		// TODO Auto-generated method stub
		if(this.obj.distance(this.target)< arg0.obj.distance(target)) return -1;
		else if(this.obj.distance(this.target) > arg0.obj.distance(target)) return 1;
		else return 0;
	}
}


