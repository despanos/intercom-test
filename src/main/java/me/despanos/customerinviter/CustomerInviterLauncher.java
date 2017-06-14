package me.despanos.customerinviter;

import java.io.IOException;
import java.util.Comparator;

import org.json.simple.parser.ParseException;

public class CustomerInviterLauncher {

	public static void main(String[] args){
		final Location officeLocation = new Location(Double.valueOf(args[0]).doubleValue(), Double.valueOf(args[1]).doubleValue());
		CustomerInviter customerInviter;
		try {
			customerInviter = new CustomerInviter(args[2]);
			CustomerFilter distanceFilter = new CustomerFilter() {
			    public boolean shouldKeep(Customer customer) {
			        return officeLocation.getDistanceFrom(customer.getLocation()) <= 100;
			    }
			};
			Comparator<Customer> userIdComparator = new Comparator<Customer>() {
				public int compare(Customer c1, Customer c2) {
					return c1.getUserId().compareTo(c2.getUserId());
				}
			};
			customerInviter.outputCustomers(distanceFilter, userIdComparator);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
