package me.despanos.customerinviter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileUtils {

	public static List<Customer> readCustomers(String customerFile) throws IOException, ParseException {
	    InputStream in = FileUtils.class.getResourceAsStream(customerFile);
	    if (in!=null){
	    	List<Customer> customers = new ArrayList<>();
	    	JSONParser parser = new JSONParser();
	    	JSONObject jsonObject;
	    	try (BufferedReader br = new BufferedReader(new InputStreamReader(in))){
	    		String line;
	    		while ((line = br.readLine()) != null) {
	    			jsonObject = (JSONObject) parser.parse(line);
	    			customers.add(getCustomer(jsonObject));
	    		}
	    	}
	    	return customers;
	    }
	    throw new FileNotFoundException("File " + customerFile + " not found!");
	}
	
	private static Customer getCustomer(JSONObject jsonObject) {
		Long userId = (Long)jsonObject.get("user_id");
		String userName = (String)jsonObject.get("name");
		Location userLocation = new Location(Double.parseDouble((String)jsonObject.get("latitude")), Double.parseDouble((String)jsonObject.get("longitude")));
		return new Customer(userId, userName, userLocation);
	}

}
