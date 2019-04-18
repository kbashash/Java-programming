package outDoorActivities;

import java.util.ArrayList;

public class Province extends PopulatedArea{
	private ArrayList<City> cities= new ArrayList<City>();
	private City capitalOfProvince;
	
	public Province() {
		super();
	}
	
	public Province(String name, Double longitude, Double latitude,
			int population, ArrayList<City> cities, City capitalOfProvince) {
		super(name, longitude, latitude, population);
		this.cities=cities;
		this.capitalOfProvince=capitalOfProvince;
	}

	public ArrayList<City> getCities() {
		return cities;
	}

	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
	}

	public City getCapitalOfProvince() {
		return capitalOfProvince;
	}

	public void setCapitalOfProvince(City capitalOfProvince) {
		this.capitalOfProvince = capitalOfProvince;
	}
	
	

}
