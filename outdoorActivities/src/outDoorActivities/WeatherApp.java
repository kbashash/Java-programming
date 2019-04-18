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
		Province quebecProvince=new Province("Quebec",-71.25,46.8,8390000,new ArrayList(Arrays.asList(montreal,quebec)),quebec);
		Province ontarioProvince=new Province("Ontario",-71.25,46.8,14000000,new ArrayList(Arrays.asList(ottawa,london,hamilton)),ottawa);
		Province albertaProvince=new Province("Alberta",-71.25,46.8,4000000,new ArrayList(Arrays.asList(calgary,edmonton)),edmonton);
		Province novaScotiaProvince=new Province("NovaScotia",-71.25,46.8,2500000,new ArrayList(Arrays.asList(halifax)),halifax);
		Province britishColombiaProvince=new Province("BritishColombia",-71.25,46.8,7900000,new ArrayList(Arrays.asList(vancouver)),vancouver);
		Province manitobaProvince=new Province("Manitoba",-71.25,46.8,2350000,new ArrayList(Arrays.asList(winnipeg)),winnipeg);

		
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
		int selection;

		Scanner input=new Scanner(System.in);
		areaMaker();//this function generates the cities and provinces and countries, later it should  use json.
		while(true) {
		System.out.println("Select city to continue:\n1-Montreal\n2-Quebec\n3-Ottawa\n4-London\n5-Hamilton\n6-Calgary\n7-Edmonton\n8-Halifax\n9-Vancouver\n10-Winnipeg\n0-Exit");
		try{
			selection=input.nextInt()-1; //the menu starts from 1 but the index starts from 0;
			if (selection==-1) {
				input.close();
				System.exit(0);//user decided to exit
			}
			City selectCity=cities.get(selection);//taking the selected city
			
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
			System.out.println("To continue press any key and hit enter...");	
			
			input.next();
			
		}
		catch(Exception ex){
			System.out.print("Invalid Input!\n");
		}
		}
		
}
}
