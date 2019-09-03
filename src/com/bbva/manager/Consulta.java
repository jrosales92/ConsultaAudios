package com.bbva.manager;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Consulta {

	public static void main(String[] args) {
	try {

		Client client = Client.create();
		WebResource webResource = client.resource("http://150.100.22.50:9090/v3/_search/expunic/expunic");

		String input = "{\"must\":{\"t\": \"EXPUNICO\", \"nc\": \"D0176518\", \"ct\": \"007453460000000040\"}}";
		
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);
		if (response.getStatus() != 202) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		
		JSONObject json = new JSONObject(output);
		JSONArray geodata = json.getJSONArray("result");
		
		for (int i = 0; i < geodata.length(); i++) {
			JSONObject obj = (JSONObject) geodata.getJSONObject(i);
			System.out.println("aplicacion: " + obj.getString("dd") + " ext: " + obj.getString("e") + " sha1N: " + obj.getString("sha1N"));
		}
		
	  } catch (Exception e) {
		e.printStackTrace();
	  }
	}
}
