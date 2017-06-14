package me.despanos.customerinviter;

public class Customer {
	
	private Long userId;
	private String name;
	private Location location;
	
	public Customer(Long userId, String name, Location location) {
		this.userId = userId;
		this.name = name;
		this.location = location;
	}

	public Long getUserId() {
		return userId;
	}
	
	public String getName() {
		return name;
	}
	
	public Location getLocation() {
		return location;
	}

}
