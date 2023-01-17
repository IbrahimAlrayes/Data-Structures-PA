public class VehicleHiringManager {
	
	LocatorMap<String> locmap;
	
	public VehicleHiringManager() {
		locmap = new TreeLocatorMap<String>();
	}

	// Returns the locator map.
	public LocatorMap<String> getLocatorMap() {
		return locmap;
	}

	// Sets the locator map.
	public void setLocatorMap(LocatorMap<String> locatorMap) {
		locmap = locatorMap;
	}

	// Inserts the vehicle id at location loc if it does not exist and returns true.
	// If id already exists, the method returns false.
	public boolean addVehicle(String id, Location loc) {
		if(id == null || loc == null) {
			
			return false;
		}
		else {
			if(locmap.add(id, loc).first) {
			return true;
			}
			return false;
			}
		
	}

	// Moves the vehicle id to location loc if id exists and returns true. If id not
	// exist, the method returns false.
	public boolean moveVehicle(String id, Location loc) {
		if(id == null || loc == null) {
			return false;
			}
		else {
			if(locmap.move(id, loc).first) {
			return true;
			}
			return false;
			}
		
	}

	// Removes the vehicle id if it exists and returns true. If id does not exist,
	// the method returns false.
	public boolean removeVehicle(String id) {
		if(id == null) {
			return false;
		}
		else {
			if(locmap.remove(id).first) {
			return true;
		}
			return false;
		}
		
	}

	// Returns the location of vehicle id if it exists, null otherwise.
	public Location getVehicleLoc(String id) {
		
		if(id == null) {
			Location loc = null;
			return loc;
		}
		else {
			return locmap.getLoc(id).first;
		}
		
	}

	// Returns all vehicles located within a square of side 2*r centered at loc
	// (inclusive of the boundaries).
	public List<String> getVehiclesInRange(Location loc, int r) {
		List<String> l = new LinkedList<String>(); 
		//leatest edit
		if(loc == null) {
			return l;
		}
		else {
			//Get Lower Left boundary
			Location lowerLeft = new Location(loc.x - r , loc.y - r);
			//Get Upper Right boundary
			Location upperRight = new Location(loc.x + r , loc.y + r);
			l = locmap.getInRange(lowerLeft, upperRight).first;
			return l;
		}
		
	}
}
