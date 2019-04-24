package outDoorActivities;

public class City extends PopulatedArea {
	
	private int capitalStatus;
	private String province;
	private String country;
	
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public City() {
		super();
	}
	
	public City(String name, Double longitude, Double latitude, int population, int capitalStatus,String province, String country) {
		super(name, longitude, latitude, population);
		this.capitalStatus=capitalStatus;
		this.province=province;
		this.country=country;
	}
	
	public boolean toFly(Weather weather){
	if(weather.getVisibility()>5000 && weather.getWindSpeed()<30) return true;
	else return false;
	}

	public boolean toBike(Weather weather){
		if(weather.getTemperature()>=-5 && weather.getTemperature() <=30) return true;
		else return false;
	}

	public int getCapitalStatus() {
		return capitalStatus;
	}

	public void setCapitalStatus(int capitalStatus) {
		this.capitalStatus = capitalStatus;
	}
	
	

}
