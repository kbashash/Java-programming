package outDoorActivities;

import java.util.ArrayList;

public class Province extends PopulatedArea{
	private ArrayList<City> cities= new ArrayList<City>();
	private City capitalOfProvince;
	private String country;
	
	public Province() {
		super();
	}
	
	public Province(String name, Double longitude, Double latitude,
			int population, ArrayList<City> cities, City capitalOfProvince,String country) {
		super(name, longitude, latitude, population);
		this.cities=cities;
		this.capitalOfProvince=capitalOfProvince;
		this.country=country;
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
	
	public boolean equals(String name) {
		if (this.getName().equals(name)) return true;
		else return false;
	}
	public boolean equals(Province province) {
		if (this.getName().equals(province.getName())) return true;
		else return false;
	}
	public void addCity(City city) {
		this.cities.add(city);
		super.setPopulation(super.getPopulation()+city.getPopulation());
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	

}
