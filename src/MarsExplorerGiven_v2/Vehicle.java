package MarsExplorerGiven_v2;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Adam
 *
 */
class Vehicle extends Entity {
	public boolean carryingSample;

	public Vehicle(Location l) {
		super(l);
		this.carryingSample = false;
	}

	public void act(Field f, Mothership m, ArrayList<Rock> rocksCollected) {
		// actCollaborative(f,m,rocksCollected);
		actSimple(f, m, rocksCollected);
	}

	public void actCollaborative(Field f, Mothership m, ArrayList<Rock> rocksCollected) {
		// Drop the crumbs when traveling back towards the ship
		// f.dropCrumbs(newLocation, 1);
	}

	public void actSimple(Field f, Mothership m, ArrayList<Rock> rocksCollected) {
		System.out.println();
		//moveLocation(f, getLocation(), f.freeAdjacentLocation(location));
		while (!carryingSample) {
			/*
			 * Move around looking for rocks Pick up rock
			 */
			moveLocation(f, getLocation(), f.freeAdjacentLocation(getLocation()));

			System.out.println("The variable's location: " + location);
			System.out.println(f.freeAdjacentLocation(getLocation()));
			
			if (f.isNeighbourTo(f.freeAdjacentLocation(getLocation()), Rock.class)) {
				System.out.println("next to rock");
				this.pickUpRock(f, getLocation(), rocksCollected);
			}
		}

	}

	/**
	 * Get location of rock Add it to rocks collected!!!! Set carrying sample to
	 * true
	 * 
	 * @param f
	 * @param l
	 * @param rocksCollected
	 */
	private void pickUpRock(Field f, Location l, ArrayList<Rock> rocksCollected) {
		System.out.println("Get here?");
		Location rockLocation = f.getNeighbour(l, Rock.class);
		Rock rock = (Rock) f.getObjectAt(rockLocation);
		System.out.println("Am picking up => " + rock);
		rocksCollected.add(rock);
		setCarryingSample(true);
	}
	
	/**
	 * @param bool
	 */
	private void setCarryingSample(Boolean bool) {
		this.carryingSample = bool;
	}
	
	/*
	 * Move around the map
	 */
	private void moveLocation(Field f, Location location, Location newLocation) {
		f.clearLocation(location);
		f.place(this, newLocation);
		this.setLocation(newLocation);
	}
}
