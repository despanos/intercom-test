package me.despanos.customerinviter;

import static org.junit.Assert.*;

import org.junit.Test;

public class LocationTest {

	@Test
	public void testGetDistanceFrom() {
		//cross-checking distance using http://keisan.casio.com/exec/system/1224587128
		Location pointA = new Location(53.3393,-6.2576841);
		Location pointB = new Location(37.9838096, 23.7275388);
		assertEquals(2854.242157, pointA.getDistanceFrom(pointB),0.001);
		
		pointA = new Location(52.986375,-6.043701);
		pointB = new Location(51.92893, -10.27699);
		assertEquals(309.936566, pointA.getDistanceFrom(pointB),0.001);
		
		pointA = new Location(53.3393,-6.2576841);
		pointB = new Location(51.92893, -10.27699);
		assertEquals(313.247685, pointA.getDistanceFrom(pointB),0.001);
	}
	
	@Test
	public void distanceToSelfShouldBeZero() {
		Location pointA = new Location(52.986375,-6.043701);
		assertEquals(0, pointA.getDistanceFrom(pointA),0.001);
	}

}
