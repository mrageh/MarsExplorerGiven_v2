package MarsExplorerGiven_v2;

import java.util.ArrayList;
import java.util.Iterator;

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

	public void actCollaborative(Field f, Mothership m,
			ArrayList<Rock> rocksCollected) {
		// Drop the crumbs when traveling back towards the ship
		// f.dropCrumbs(newLocation, 1);
	}

	public void actSimple(Field f, Mothership m, ArrayList<Rock> rocksCollected) {
		System.out.println();
		/*
		 * Move from location to location 
		 * TODO: extract this to a private method
		 */

		Location location = this.getLocation();
		Location newLocation = f.freeAdjacentLocation(location);
		f.clearLocation(location);
		f.place(this, newLocation);
		this.setLocation(newLocation);

		while (!carryingSample) {
			/* 
			 * Move around looking for rocks
			 * Pick up rock
			 */
			f.clearLocation(location);
			f.place(this, newLocation);
			this.setLocation(newLocation);
			
			if (f.isNeighbourTo(newLocation, Rock.class)) {
				System.out.println("next to rock");
				this.pickUpRock(f, getLocation(), rocksCollected);
			}
		}

		while(carryingSample) {
			f.clearLocation(location);
			f.place(this, newLocation);
			this.setLocation(newLocation);
			
			if (f.isNeighbourTo(newLocation, Obstacle.class)) {
				System.out.println("next to obstacle");
			} else if (f.isNeighbourTo(newLocation, Mothership.class)) {
				System.out.println("next to mothership");
				
			}
	
		}

		
		System.out.println(location);
		System.out.println(newLocation);

		/*
		 * Check for motherships signal Move back towards mothership Drop rock
		 * off at mothership avoid obstacles
		 */
	}

	private void pickUpRock(Field f, Location l, ArrayList<Rock> rocksCollected) {
		// check if object at current location is a rock
		if (f.getObjectAt(l) instanceof Rock) {
			Rock e = (Rock) f.getObjectAt(l);
			// Add it to rocks collected!!!!
			rocksCollected.add(e);
			// set carrying sample to true
			setCarryingSample(true);
		}
	}
	
	private void setCarryingSample(Boolean bool) {
		this.carryingSample = bool;
	}
	
	private void dropRockSample(Field f, Location l, ArrayList<Rock> rocksCollected) {
		if (f.getObjectAt(l) instanceof Mothership) {
			
			// Drop rock collected
			System.out.println("Dropping off rock" + rocksCollected.get(0));
			Rock rock = rocksCollected.get(0);
			// set carrying sample to true
			setCarryingSample(false);
		}
	}
}
