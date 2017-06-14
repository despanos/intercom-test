package me.despanos.customerinviter;

public class Location {
	
	private double latitude;
	private double longitude;
	private final static double EARTH_RADIUS = 6371;
	
	public Location(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getDistanceFrom(Location otherPoint){
		double longitudeDifference = Math.abs(longitude - otherPoint.getLongitude());
		double centralAngle = Math.acos(Math.sin(Math.toRadians(latitude))*Math.sin(Math.toRadians(otherPoint.getLatitude())) + 
				Math.cos(Math.toRadians(latitude))*Math.cos(Math.toRadians(otherPoint.getLatitude()))*Math.cos(Math.toRadians(longitudeDifference)));
		return EARTH_RADIUS * centralAngle;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

}
