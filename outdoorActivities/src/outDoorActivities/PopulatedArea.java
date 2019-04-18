package outDoorActivities;

public abstract class PopulatedArea {
	private String name;
	private Double longitude;
	private Double latitude;
	private int population;
	
	public PopulatedArea() {
		
	}	
	
	public PopulatedArea(String name, Double longitude, Double latitude, int population) {
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.population = population;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}
	
	
	
}
