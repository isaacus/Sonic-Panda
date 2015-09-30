package com.example.pandasonic;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class WayPoint {
	String name;
	String statusCode;
	ArrayList<String> typeList;
	String placeId;
	boolean partialMatch;
	
	public WayPoint(String name, String statusCode, ArrayList<String> typeList, String placeId, boolean partialMatch) {
		this.name = name;
		this.statusCode = statusCode;
		this.typeList = typeList;
		this.placeId = placeId;
		this.partialMatch = partialMatch;
	}
}

class Step {
	String distance;
	double distanceValue;
	String duration;
	double durationValue;
	
	String instructions;
	String maneuver;
	
	public Step(String distance, double distanceValue, String duration, double durationValue, String instructions, String maneuver) {
		this.distance = distance;
		this.distanceValue = distanceValue;
		this.duration = duration;
		this.distanceValue = durationValue;
		this.instructions = instructions;
		this.maneuver = maneuver;
	}
}

class Leg {
	String distance;
	double distanceValue;
	String duration;
	double durationValue;
	ArrayList<Step> stepList;
	
	public Leg(ArrayList<Step> stepList, String distance, double distanceValue, String duration, double durationValue) {
		this.stepList = stepList;
		this.distance = distance;
		this.distanceValue = distanceValue;
		this.duration = duration;
		this.durationValue = durationValue;
	}
}

class Route {
	private ArrayList<Leg> legList;
	
	private int[] wayPointOrder;
	
	public Route(ArrayList<Leg> legList, int[] wayPointOrder) {
		this.legList = legList;
		this.wayPointOrder = wayPointOrder;
	}
}

public class RoutePlanner {
	
	private String routeStr = "";
	
	private ArrayList<Route> routeList;
	private ArrayList<WayPoint> wayPointList;
	
	public RoutePlanner(String routeStr) {
		this.routeStr = routeStr;
		wayPointList = new ArrayList<WayPoint>();
		routeList = new ArrayList<Route>();
	}
	
	public boolean parse() {
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject)parser.parse(this.routeStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(obj == null)
			return false;
		
		JSONArray wayPoints = (JSONArray) obj.get("geocoded_waypoints");
		
		for(int i = 0; i < wayPoints.size(); i++) {
			String placeId = ((JSONObject) wayPoints.get(i)).get("place_id").toString();
			String statusCode = ((JSONObject) wayPoints.get(i)).get("geocoder_status").toString();
			JSONArray types = (JSONArray) ((JSONObject) wayPoints.get(i)).get("types");
			
			ArrayList<String> typeList = new ArrayList<String>();
			
			for(int j = 0; j < types.size(); j++) {
				typeList.add((types.get(j)).toString());
			}
			
			wayPointList.add(new WayPoint(null, statusCode, typeList, placeId, false));
		}
		
		JSONArray routes = (JSONArray) obj.get("routes");
		
		for(int i = 0; i < routes.size(); i++) {
			
			JSONArray legs = (JSONArray) ((JSONObject) routes.get(i)).get("legs");
			
			ArrayList<Leg> legList = new ArrayList<Leg>();
			
			for(int j = 0; j < legs.size(); j++) {
				String distance = ((JSONObject) ((JSONObject) legs.get(j)).get("distance")).get("text").toString();
				double distanceValue = Double.valueOf(((JSONObject) ((JSONObject) legs.get(j)).get("distance")).get("value").toString());
	
				String duration = ((JSONObject) ((JSONObject) legs.get(j)).get("duration")).get("text").toString();
				double durationValue = Double.valueOf(((JSONObject) ((JSONObject) legs.get(j)).get("duration")).get("value").toString());
				
				JSONArray steps = (JSONArray) ((JSONObject) legs.get(j)).get("steps");
				
				ArrayList<Step> stepList = new ArrayList<Step>();
				
				for(int k = 0; k < steps.size(); k++) {
					String stepDistance = ((JSONObject) ((JSONObject) steps.get(k)).get("distance")).get("text").toString();
					double stepDistanceValue = Double.valueOf(((JSONObject) ((JSONObject) steps.get(k)).get("distance")).get("value").toString());
	
					String stepDuration = ((JSONObject) ((JSONObject) steps.get(k)).get("duration")).get("text").toString();
					double stepDurationValue = Double.valueOf(((JSONObject) ((JSONObject) steps.get(k)).get("duration")).get("value").toString());
					
					String instructions = ((JSONObject) steps.get(k)).get("html_instructions").toString();
					
					Object maneuverObj = ((JSONObject) steps.get(k)).get("maneuver");
					String maneuver = "";
					if(maneuverObj != null)
						maneuver = maneuverObj.toString();
					
					//if(maneuverObj != null)
					//	maneuver = ((JSONObject) steps.get(k)).get("maneuver").toString();
					
					stepList.add(new Step(stepDistance, stepDistanceValue, stepDuration, stepDurationValue, instructions, maneuver));					
				}
				
				legList.add(new Leg(stepList, distance, distanceValue, duration, durationValue));
			}
			
			JSONArray wayPointOrder = (JSONArray) ((JSONObject) routes.get(i)).get("waypoint_order");
			
			int[] order = new int[wayPointOrder.size()];
			
			for(int j = 0; j < wayPointOrder.size(); j++)
				order[j] = Integer.valueOf((wayPointOrder.get(j)).toString());
			
			routeList.add(new Route(legList, order));
		}
		
		return true;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		URL url;
		try {
			url = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=Adelaide,SA&destination=Adelaide,SA&waypoints=optimize:true|Barossa+Valley,SA|Clare,SA|Connawarra,SA|McLaren+Vale,SA&key=AIzaSyDYZurWyusHaKrHIe5RpQ3ooArKSJ-031M");
		
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();			

			InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			
			Scanner scanner  = new Scanner(in,"UTF-8").useDelimiter("\\A");
			
			String result = scanner.hasNext() ? scanner.next() : "";
			
			result = result.replaceAll("\u003c", "<").replaceAll("\u003e", ">");
			
			RoutePlanner planner = new RoutePlanner(result);
			
			planner.parse();
			
			System.out.println(result);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
