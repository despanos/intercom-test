package me.despanos.customerinviter;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.regex.Pattern;

import org.json.simple.parser.ParseException;
import org.junit.Test;

public class CustomerInviterTest {
	
	@Test
	public void testFilterCustomersBasedOnUserId() throws IOException, ParseException{
		CustomerInviter customerInviter = new CustomerInviter("/gistfile1.txt");
		CustomerFilter idFilter = new CustomerFilter() {
		    public boolean shouldKeep(Customer customer) {
		        return customer.getUserId()<=10;
		    }
		};
		customerInviter.filterCustomers(idFilter);
		assertEquals(10, customerInviter.getCustomers().size());
	}
	
	@Test
	public void testFilterCustomersBasedOnName() throws IOException, ParseException{
		CustomerInviter customerInviter = new CustomerInviter("/gistfile1.txt");
		CustomerFilter nameFilter = new CustomerFilter() {
		    public boolean shouldKeep(Customer customer) {
		        return customer.getName().startsWith("Enid");
		    }
		};
		customerInviter.filterCustomers(nameFilter);
		assertEquals(3, customerInviter.getCustomers().size());
	}
	
	@Test
	public void testOutputCustomers() throws IOException, ParseException{
		CustomerInviter customerInviter = new CustomerInviter("/gistfile1.txt");
		CustomerFilter surnameFilter = new CustomerFilter() {
		    public boolean shouldKeep(Customer customer) {
		        return customer.getName().endsWith("Ahearn");
		    }
		};
		Comparator<Customer> userIdComparator = new Comparator<Customer>() {
			public int compare(Customer c1, Customer c2) {
				return c1.getUserId().compareTo(c2.getUserId());
			}
		};
		//redirect System.out
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream oldStdOut = System.out;
		System.setOut(new PrintStream(outContent));
		customerInviter.outputCustomers(surnameFilter,userIdComparator);
		
		assertTrue(outContent.toString().startsWith("Name"));
		assertTrue(outContent.toString().contains("Eoin Ahearn"));
		//Olive (ID 13) should come before Michael (ID 15)
		assertTrue(outContent.toString().indexOf("Olive Ahearn") < outContent.toString().indexOf("Michael Ahearn"));
		//6 occurrences of Ahearn, therefore split() should return 7
		assertEquals(7, Pattern.compile("Ahearn").split(outContent.toString()).length);
		//restore System.out
		System.setOut(oldStdOut);
	}

}
