package me.despanos.customerinviter;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.Test;

public class FileUtilsTest {
	
	@Test(expected = FileNotFoundException.class)
	public void whenFileNotFoundThenAnExceptionShouldBeThrown() throws ParseException, IOException{
		List<Customer> customers = FileUtils.readCustomers("gistfile1.txt");
	}
	
	@Test(expected = ParseException.class)
	public void whenFileDoesNotContainValidJsonThenAnExceptionShouldBeThrown() throws ParseException, IOException{
		List<Customer> customers = FileUtils.readCustomers("/not_valid_json.txt");
	}
	
	@Test(expected = ParseException.class)
	public void whenFileIsNotJsonThenAnExceptionShouldBeThrown() throws ParseException, IOException{
		List<Customer> customers = FileUtils.readCustomers("/not_json.txt");
	}
	
	@Test
	public void testReadCustomersSize() throws ParseException, IOException {
		assertEquals(32,FileUtils.readCustomers("/gistfile1.txt").size());
	}
	
	@Test
	public void testReadCustomersObject() throws ParseException, IOException {
		Customer customer = FileUtils.readCustomers("/gistfile2.txt").get(0);
		assertEquals("Christina McArdle", customer.getName());
		assertEquals(new Long(12), customer.getUserId());
		assertEquals(52.986375, customer.getLocation().getLatitude(), 1e-6);
		assertEquals(-6.043701, customer.getLocation().getLongitude(), 1e-6);
	}

}
