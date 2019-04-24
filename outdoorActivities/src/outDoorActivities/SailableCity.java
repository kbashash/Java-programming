package outDoorActivities;

public class SailableCity extends City implements Sailable {

	
	public SailableCity() {
		super();
	}

	public SailableCity(String name, Double longitude, Double latitude,
			int population, int capitalStatus, String province, String country) {
		super(name, longitude, latitude, population, capitalStatus,province,country);
	}

	public boolean toSail(Weather weather){
		if(weather.getWindSpeed()>=5 && weather.getWindSpeed()<=20 && weather.getWindGust() <=25) return true;
		else return false;
	}
}
