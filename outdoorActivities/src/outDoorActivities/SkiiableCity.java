package outDoorActivities;

public class SkiiableCity extends City implements Skiiable {
	
	
	public SkiiableCity() {
		super();
	}

	public SkiiableCity(String name, Double longitude, Double latitude,
			int population, int capitalStatus) {
		super(name, longitude, latitude, population, capitalStatus);
	}

	public boolean toSki(Weather weather){
		if(weather.getVisibility()>1000) return true;
		else return false;
	}
}
