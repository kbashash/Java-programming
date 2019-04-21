package outDoorActivities;

public abstract class PopulatedArea {
	private String name;
	private Double longitude;
	private Double latitude;
	private int population;
	
	public PopulatedArea() {
		
	}	
	
	public  PopulatedArea(String _name, Double _longitude, Double _latitude, int _population) {
		name = _name;
		longitude = _longitude;
		latitude = _latitude;
		population = _population;
	}

	public String getName() {
		return name;
	}

	public void setName(String _name) {
		name = _name;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double _longitude) {
		longitude = _longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double _latitude) {
		latitude = _latitude;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int _population) {
		population = _population;
	}
	
	
	
}
