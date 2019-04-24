
package outDoorActivities;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.json.*;

public class CityMaker {
	private static String name;
	private static int population;
	private static int capitalStatus;
	private static double lng;
	private static String country;
	private static String iso2;
	private static double lat;
	private static String province;
	private static ArrayList<City> cities=new ArrayList<City>();
	private static ArrayList<Province> provinces=new ArrayList<Province>();
	private static ArrayList<Country> countries=new ArrayList<Country>();
	private static boolean provinceFound;
	private static boolean countryFound;
	private static int pop=0;

	public static ArrayList<City> areaMaker(String fileName) {
		try {
			File file = new File(fileName);
			try(InputStream is=new FileInputStream(file)){
				JsonReader rdr = Json.createReader(is);
				JsonArray results = rdr.readArray();

				//System.out.println(results.size());
				for (int i=0;i<results.size();i++) {//the number of cities are entered manually, I could not find a way yet to find the number of cities in Json file
					JsonObject city = results.getValuesAs(JsonObject.class).get(i);
					//System.out.println(results.lastIndexOf(city)+1+ "-" + city.getString("city"));
					name=city.getString("city");
					population=Integer.parseInt(city.getString("population"));
					province=city.getString("admin");
					lat=Double.parseDouble(city.getString("lat"));
					lng=Double.parseDouble(city.getString("lng"));
					country=city.getString("country");
					iso2=city.getString("iso2");
					if(lat>45 || lat<-35){//check for skiiable conditions to create skiiable and sailableandskiiable cities.
						if (city.getString("capital").equals("primary")) {//looking for capital cities of countries
							capitalStatus=CapitalStatus.PRIMARY;
							cities.add(new SkiiableCity(name,lng,lat,population,capitalStatus,province, country));//for cities with "primary" as capital status, only skiiable is considered
						}else if(city.getString("capital").equals("admin")){
							capitalStatus=CapitalStatus.ADMIN;
							cities.add(new SkiiableCity(name,lng,lat,population,capitalStatus,province, country));//for cities with "admin" as capital status, only skiiable is considered
						}else {
							capitalStatus=CapitalStatus.NONE;//for cities with no capital status sailalbe and skiiable cities considered.
							cities.add(new SailableAndSkiiableCity(name,lng,lat,population,capitalStatus,province, country));
						}
					}else if (city.getString("capital").equals("primary")) {//looking for capital cities of countries
						capitalStatus=CapitalStatus.PRIMARY;
						cities.add(new City(name,lng,lat,population,capitalStatus,province, country));//for cities with "primary" as capital status, only skiiable is considered
					}else if(city.getString("capital").equals("admin")){
						capitalStatus=CapitalStatus.ADMIN;
						cities.add(new City(name,lng,lat,population,capitalStatus,province, country));//for cities with "admin" as capital status, only skiiable is considered
					}else {
						capitalStatus=CapitalStatus.NONE;//for cities with no capital status sailalbe and skiiable cities considered.
						cities.add(new SailableCity(name,lng,lat,population,capitalStatus,province, country));
					}
					//Province update
					provinceFound=false;//when true means the province of current city is found in list of provinces	
					if(provinces.size()>0) {//if the list of provinces is not empty
						for (int j=0;j<provinces.size();j++) {//with this loop we search all provinces
							if (provinces.get(j).getName().equals(province)) {//condition to check if province for current city exists
								provinces.get(j).addCity(cities.get(i));//province exists, so add city to list of cities of that province
								provinceFound=true;//indicate that province exists and there is no need to create a new one

								if (capitalStatus==2) {//in the other hand if the city is capital of province then...
									provinces.get(j).setCapitalOfProvince(cities.get(i));//set current city as capital of current province
								}
							} 
							//if province is not found during the search, so it makes a new province
						}
						if (!provinceFound) provinces.add(new Province(province,lng,lat,population,new ArrayList<City>(Arrays.asList(cities.get(i))),cities.get(i),country));
						//if there is no province available, just make it without searching
					} else provinces.add(new Province(province,lng,lat,population,new ArrayList<City>(Arrays.asList(cities.get(i))),cities.get(i),country));

					for(int j=0;j<provinces.size();j++){//lets update countries
						countryFound=false;
						if(countries.size()==0){//there is no country, let make first one
							countries.add(new Country(country,lng,lat,provinces.get(j).getPopulation(),iso2, new ArrayList(Arrays.asList(provinces.get(j))),provinces.get(j).getCapitalOfProvince()));
						}else{
							for(int k=0;k<countries.size();k++){
								if(countries.get(k).getName().equals(provinces.get(j).getCountry())){//looking for country
									countries.get(k).addProvince(provinces.get(j));//country found, so add province to that country
									countryFound=true;//set the flag, so no duplicate countries will be made
								}

							}
							if (!countryFound){//country not found, so lets make it
								countries.add(new Country(country,lng,lat,provinces.get(j).getPopulation(),iso2, new ArrayList(Arrays.asList(provinces.get(j))),provinces.get(j).getCapitalOfProvince()));
							}
						}
					}
				}
				for(int k=0;k<countries.size();k++) {
					for(int l=0;l<cities.size();l++){
						if(cities.get(l).getCapitalStatus()==CapitalStatus.PRIMARY && cities.get(l).getCountry().equals(countries.get(k).getName())){//the city is capital of country
							countries.get(k).setCapitalOfCountry(cities.get(l));
						}
					}	
				}

				System.out.println(cities.size()+" City, "+provinces.size()+" Provinces "+countries.size()+" Country found.");
			}
		}
		catch(Exception ex) {
			System.out.println("Exception Occured "+ex);
		}
		return cities;
	}

}



