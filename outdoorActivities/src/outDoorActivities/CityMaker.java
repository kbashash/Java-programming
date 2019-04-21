
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
	private static int capital;

	public static ArrayList<City> areaMaker(String fileName) {
		try {
			File file = new File(fileName);
			try(InputStream is=new FileInputStream(file)){
				JsonReader rdr = Json.createReader(is);
				JsonObject obj = rdr.readObject();
				JsonArray results = obj.getJsonArray("cityInfos");


				for (int i=0;i<247;i++) {//the number of cities are entered manually, I could not find a way yet to find the number of cities in Json file
					JsonObject city = results.getValuesAs(JsonObject.class).get(i);
					//System.out.println(results.lastIndexOf(city)+1+ "-" + city.getString("city"));

					name=city.getString("city");
					population=Integer.parseInt(city.getString("population_proper"));
					province=city.getString("admin");
					lat=Double.parseDouble(city.getString("lat"));
					lng=Double.parseDouble(city.getString("lng"));
					country=city.getString("country");
					iso2=city.getString("iso2");
					if (city.getString("capital")=="primary") {//this condition basically is designed to store the capital status but it was modified to make all type of cities.
						capitalStatus=CapitalStatus.PRIMARY;
						cities.add(new SailableCity(name,lng,lat,population,capitalStatus));
						capital=i;//holding the capital city index to add later as capital city to country
					} else if(city.getString("capital")=="admin"){
						capitalStatus=CapitalStatus.ADMIN;
						cities.add(new SkiiableCity(name,lng,lat,population,capitalStatus));
					}else {
						capitalStatus=CapitalStatus.NONE;
						if (i%2==0)cities.add(new SkiiableCity(name,lng,lat,population,capitalStatus));
						else cities.add(new City(name,lng,lat,population,capitalStatus));
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
					//if there is no province available, just make it withouth searching
					} else provinces.add(new Province(province,lng,lat,population,new ArrayList<City>(Arrays.asList(cities.get(i))),cities.get(i),country));
				}
				System.out.println(cities.size()+" City, "+provinces.size()+" Provinces and 1 Country made.");	
			}
		}
		catch(Exception ex) {
			System.out.println("Exception Occured "+ex);
		}
		return cities;
	}

}



