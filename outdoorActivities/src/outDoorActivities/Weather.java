package outDoorActivities;

import java.io.InputStream;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Weather {
	private static String name;
	private static int windSpeed;
	private static int windGust;
	private static int visibility;
	private static int temperature;
	
	public Weather(){
		
	}
	
	public Weather(String nam, int temp, int windspeed, int windgust, int visibiliti){
		name=nam;
		temperature=temp;
		windSpeed=windspeed;
		windGust=windgust;
		visibility=visibiliti;
	}
	public Weather(City city) {
		name = city.getName();
		fetchWeatherCondition(city);
	}

	public static void displayWeather(City city){
		fetchWeatherCondition(city);
		System.out.println("Weather condition on '"+city.getName());
		System.out.println("-Temperature: "+temperature+" C\n-Visibility: "+visibility+" Meters\n-Windspeed: "+windSpeed+" Knots\n-Windgust: "+windGust);
	}
	public static Weather fetchWeatherCondition(City city){
		try {
			
			//here comes the function to make weather from json file
			URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+city.getName()+"&units=metric&appid=122a9b7457682449757326422f77a195");
			try (InputStream is = url.openStream();
				    JsonReader rdr = Json.createReader(is)) {
				    JsonObject obj = rdr.readObject();
				    name=obj.getString("name");
				    temperature=obj.getJsonObject("main").getInt("temp");
				    visibility=obj.getInt("visibility");
			    	windSpeed=obj.getJsonObject("wind").getInt("speed");
				    if (obj.getJsonObject("wind").containsKey("gust")) windGust=obj.getJsonObject("wind").getInt("gust"); 
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return new Weather(name, temperature, windSpeed, windGust, visibility);
	}
	public String getName() {
		return name;
	}
	public void setName(String namee) {
		name = namee;
	}
	public int getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(int windspeed) {
		windSpeed = windspeed;
	}
	public int getWindGust() {
		return windGust;
	}
	public void setWindGust(int windgust) {
		windGust = windgust;
	}
	public int getVisibility() {
		return visibility;
	}
	public void setVisibility(int visibiliti) {
		visibility = visibiliti;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(int temp) {
		temperature = temp;
	}

}
