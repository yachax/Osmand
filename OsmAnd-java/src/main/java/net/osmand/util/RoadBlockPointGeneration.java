package net.osmand.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class RoadBlockPointGeneration {
	
	private boolean isTest = true;
	private final static float top = 52.4797f; 
	private final static float bottom = 52.0368f; 
	private final static float left = 4.6408f;
	private final static float right = 5.2740f;
	private static int pointCount = 10000;
	
	public static void main(String[] args) {
		Set<LatLon> points = new HashSet<>();
		Random rd = new Random();
		while (points.size() != pointCount) {
			points.add(generateRandomLatLonWithinBBox(top, bottom, left, right, rd));
		}
		for (LatLon ll : points) {
			System.out.println("{\"type\": \"Feature\",\"geometry\": {\"type\": \"Point\",\"coordinates\": [" + ll.lon + ", " +ll.lat +"]},\"properties\": {}},");
		}
	}
	
	static LatLon generateRandomLatLonWithinBBox(float top, float bottom, float left, float right, Random rd) {
		float yRange = top - bottom;
		float xRange = right - left;
		
		
		float x = rd.nextFloat();
		float y = rd.nextFloat();
		
		while (y > yRange) {
			y = rd.nextFloat();
		}
		
		while (x > xRange) {
			x = rd.nextFloat();
		}
		
		x = x + left;
		y = y + bottom;
		//System.out.println("{\"type\": \"Feature\",\"geometry\": {\"type\": \"Point\",\"coordinates\": [" + y + ", " +x +"]},\"properties\": {}},");
		return new LatLon(y,x);
		
	}
	
	
	
	static class LatLon{
		
		float lat;
		float lon;
				
		public LatLon(float lat, float lon) {
			this.lat = lat;
			this.lon = lon;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return String.format("lat=\"%1$s\" lon=\"%2$s\"", lat, lon);
		}
	}
}
