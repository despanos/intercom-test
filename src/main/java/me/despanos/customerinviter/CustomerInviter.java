package me.despanos.customerinviter;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.json.simple.parser.ParseException;

public class CustomerInviter {
	
	private List<Customer> customers;
	
	public CustomerInviter(String customerFile) throws IOException, ParseException{
		customers = FileUtils.readCustomers(customerFile);
	}
	
	public void outputCustomers(CustomerFilter filter, Comparator<Customer> sortComparator){
		filterCustomers(filter);
		Collections.sort(customers, sortComparator);
		System.out.printf("%-30s%s%n","Name", "ID");
		for (Customer customer:customers)
			System.out.printf("%-30s%s%n", customer.getName(), customer.getUserId());
	}
	
	public void filterCustomers(CustomerFilter filter) {
		Iterator<Customer> iterator = customers.iterator();
		while (iterator.hasNext()){
			Customer currentCustomer = iterator.next();
			if (!filter.shouldKeep(currentCustomer))
				iterator.remove();
		}
	}

	public List<Customer> getCustomers() {
		return customers;
	}
	
}
