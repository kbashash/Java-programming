package outDoorActivities;

public class SailableAndSkiiableCity extends City implements Sailable, Skiiable {

	public SailableAndSkiiableCity() {
		super();
	}

	public SailableAndSkiiableCity(String name, Double longitude,
			Double latitude, int population, int capitalStatus) {
		super(name, longitude, latitude, population, capitalStatus);
	}
	
	public boolean toSail(Weather weather){
		if(weather.getWindSpeed()>=5 && weather.getWindSpeed()<=20 && weather.getWindGust()<=25) return true;
		else return false;
	}
	
	public boolean toSki(Weather weather){
		if(weather.getVisibility()>1000) return true;
		else return false;
	}
	
	
}
