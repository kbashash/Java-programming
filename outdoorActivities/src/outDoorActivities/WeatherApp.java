package outDoorActivities;
import java.util.ArrayList;
import java.util.Arrays;
import java.net.*;
import java.util.Scanner;
import java.io.*;

import javax.json.*;

public class WeatherApp {
	public static ArrayList<City> cities=new ArrayList<City>();

	public static void areaMaker() {

		//city objects
		City montreal=new SailableCity("Montreal",-73.583333,45.5,2356556,CapitalStatus.NONE);
		City quebec=new SkiiableCity("Quebec",-71.25,46.8,528595,CapitalStatus.ADMIN);

		City vancouver=new SailableAndSkiiableCity("Vancouver",-123.133333,49.25,2313328,CapitalStatus.NONE);

		City ottawa=new SkiiableCity("Ottawa",-75.7,45.416667,1145000,CapitalStatus.PRIMARY);
		City london=new SailableCity("London",-81.25,42.983333,335035,CapitalStatus.NONE);
		City hamilton=new City("Hamilton",-79.857484,43.256101,519949,CapitalStatus.NONE);


		City calgary=new City("Calgary",-114.083333,51.083333,915322,CapitalStatus.NONE);
		City edmonton=new City("Edmonton",-113.5,53.55,712391,CapitalStatus.ADMIN);

		City winnipeg=new SkiiableCity("Winnipeg",-97.166667,49.883333,575313,CapitalStatus.ADMIN);

		City halifax=new SailableAndSkiiableCity("Halifax",-63.6,44.65,222874,CapitalStatus.ADMIN);

		//now put them in Arraylist

		cities.add(montreal);
		cities.add(quebec);
		cities.add(ottawa);
		cities.add(london);
		cities.add(hamilton);
		cities.add(calgary);
		cities.add(edmonton);
		cities.add(halifax);
		cities.add(vancouver);
		cities.add(winnipeg);

		//instantiating new provinces
		Province quebecProvince=new Province("Quebec",-71.25,46.8,8390000,new ArrayList(Arrays.asList(montreal,quebec)),quebec,"canada");
		Province ontarioProvince=new Province("Ontario",-71.25,46.8,14000000,new ArrayList(Arrays.asList(ottawa,london,hamilton)),ottawa,"canada");
		Province albertaProvince=new Province("Alberta",-71.25,46.8,4000000,new ArrayList(Arrays.asList(calgary,edmonton)),edmonton,"canada");
		Province novaScotiaProvince=new Province("NovaScotia",-71.25,46.8,2500000,new ArrayList(Arrays.asList(halifax)),halifax,"canada");
		Province britishColombiaProvince=new Province("BritishColombia",-71.25,46.8,7900000,new ArrayList(Arrays.asList(vancouver)),vancouver,"canada");
		Province manitobaProvince=new Province("Manitoba",-71.25,46.8,2350000,new ArrayList(Arrays.asList(winnipeg)),winnipeg,"canada");


		ArrayList<Province> provinces=new ArrayList<>();
		provinces.add(quebecProvince);
		provinces.add(ontarioProvince);
		provinces.add(albertaProvince);
		provinces.add(novaScotiaProvince);		
		provinces.add(manitobaProvince);
		provinces.add(britishColombiaProvince);		

		Country canada=new Country("Canada",45.0,45.0,350000000,"CA",provinces,ottawa);

	}

	public static void main(String[] args) {
		boolean cityNotFound=false;
		String selectName;//holds the city name entered by user
		ArrayList<City> cities=new ArrayList<City>();
		Scanner input=new Scanner(System.in);
		//areaMaker();//this function generates the cities and provinces and countries, later it should  use json.
		cities=CityMaker.areaMaker("src/outDoorActivities/ca.json.txt");
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
						System.out.println(i+"-"+cities.get(i).getName()+"\n");
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
