package MarsExplorerGiven_v2;

abstract class Entity {
	Location location;
	
	public Entity(Location location){this.location = location;}
	
	public void setLocation(Location location){
		this.location = location;
	}
	
	public Location getLocation(){
		return this.location;
	}
}
