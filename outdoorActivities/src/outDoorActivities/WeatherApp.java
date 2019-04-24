package outDoorActivities;
import java.util.ArrayList;
import java.util.Arrays;
import java.net.*;
import java.util.Scanner;
import java.io.*;

import javax.json.*;

public class WeatherApp {
	public static ArrayList<City> cities=new ArrayList<City>();

	
	public static void main(String[] args) {
		boolean cityNotFound=false;
		String selectName;//holds the city name entered by user
		ArrayList<City> cities=new ArrayList<City>();
		Scanner input=new Scanner(System.in);
		//areaMaker();//this function generates the cities and provinces and countries, later it should  use json.
		cities=CityMaker.areaMaker("src/outDoorActivities/cities_ca.json");
		while(true) {
			System.out.println("-Enter city name to search for\n-Type 'Exit' to exit the program\n-Type 'List' to see a list of cities:");

			//System.out.println("Select city to continue:\n1-Montreal\n2-Quebec\n3-Ottawa\n4-London\n5-Hamilton\n6-Calgary\n7-Edmonton\n8-Halifax\n9-Vancouver\n10-Winnipeg\n0-Exit");
			try{
				selectName=input.next();
				if (selectName.toLowerCase().equals("exit")) {
					input.close();
					System.out.println("Program terminated by user.\n");
					System.exit(0);//user decided to exit
				}
				if (selectName.toLowerCase().equals("list")) {
					for(int i=0;i<cities.size();i++) {
						System.out.println(i+"-"+cities.get(i).getName()+"--"+cities.get(i).getCapitalStatus()+"\n");
					}
				}else {
					cityNotFound=true;
					for(int i=0;i<cities.size();i++) {//finding the user searched city
						if (cities.get(i).getName().toLowerCase().equals(selectName.toLowerCase())) {
							City selectCity=cities.get(i);//taking the selected city
							cityNotFound=false;
							Weather.displayWeather(selectCity);//static display method in weather class, displays the weather condition.

							System.out.println("Possible Outdoor Activities are:");

							if(selectCity.toBike(Weather.fetchWeatherCondition(selectCity))) {//check for biking conditions
								System.out.println("-Biking");
							}
							if(selectCity.toFly(Weather.fetchWeatherCondition(selectCity))) {//check for flying conditions
								System.out.println("-Flying");
							}

							if(selectCity instanceof Sailable) {//check sailing conditions if city is sailable
								if (((SailableCity)selectCity).toSail(Weather.fetchWeatherCondition(selectCity))) System.out.println("-Sailing");
							} 
							if (selectCity instanceof Skiiable) {//check sailing conditions if city is skiiable
								if (((Skiiable)selectCity).toSki(Weather.fetchWeatherCondition(selectCity))) System.out.println("-Skiing");
							}	

						}
					}
					if (cityNotFound) {
						throw new Exception("City Not Found, try again\n");
					}
				}

			}
			catch(Exception ex){
				System.out.print(ex.getLocalizedMessage());
			}
		}

	}
}
