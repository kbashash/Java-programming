package outDoorActivities;

public class SkiiableCity extends City implements Skiiable {
	
	
	public SkiiableCity() {
		super();
	}

	public SkiiableCity(String name, Double longitude, Double latitude,
			int population, int capitalStatus,String province,String country) {
		super(name, longitude, latitude, population, capitalStatus, province, country);
	}

	public boolean toSki(Weather weather){
		if(weather.getVisibility()>1000) return true;
		else return false;
	}
}
