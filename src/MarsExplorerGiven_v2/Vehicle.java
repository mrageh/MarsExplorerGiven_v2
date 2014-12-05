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

	public void actCollaborative(Field f, Mothership m,
			ArrayList<Rock> rocksCollected) {
		// Drop the crumbs when traveling back towards the ship
		// f.dropCrumbs(newLocation, 1);
	}

	public void actSimple(Field f, Mothership m, ArrayList<Rock> rocksCollected) {
		System.out.println();
		// moveLocation(f, getLocation(), f.freeAdjacentLocation(location));
		while (!carryingSample) {
			/*
			 * Move around looking for rocks Pick up rock
			 */
			Location nextLocation = f.freeAdjacentLocation(getLocation());

			if (f.isNeighbourTo(getLocation(), Rock.class)) {
				System.out.println("next to rock");
				this.pickUpRock(f, getLocation(), rocksCollected);
			}
			moveLocation(f, getLocation(), nextLocation);
		}

		while (carryingSample) {
			Location nextLocation = f.freeAdjacentLocation(getLocation());
			System.out.println("Next Location: " + nextLocation);

			if (f.isNeighbourTo(getLocation(), Mothership.class)) {
				System.out.println("next to mothership");
				this.dropRockSample(f, getLocation(), rocksCollected);
			}
			moveLocation(f, getLocation(), nextLocation);
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

	/**
	 * Leave rock sample at mother ship
	 * 
	 * @param f
	 * @param l
	 * @param rocksCollected
	 */
	private void dropRockSample(Field f, Location l, ArrayList<Rock> rocksCollected) {
		Rock rock = rocksCollected.get(0);
		Location motherShipLocation = f.getNeighbour(l, Mothership.class);
		rocksCollected.remove(0);
		System.out.println("Dropping off rock sample: " + rock);
		System.out.println("At mothership in: " + motherShipLocation);
		f.place(rock, motherShipLocation);
		setCarryingSample(false);
	}
}
