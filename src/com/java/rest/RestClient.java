package com.java.rest;

import java.io.*;
import java.net.*;
import javax.xml.xpath.*;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;
import org.apache.http.util.*;
import org.xml.sax.*;

public class RestClient {
	public static void main(String args[]) {

	}

	public static void getLargestCityAndCapital(String state) throws Exception {

		//RestTemplate restTemplate = new RestTemplate();
		rest
		JsonObject apiResponse = new JsonParser().parse(
				restTemplate.getForEntity("http://services.groupkt.com/state/get/USA/all", String.class).getBody())
				.getAsJsonObject();
		JsonArray result = apiResponse.get("RestResponse").getAsJsonObject().get("result").getAsJsonArray();

		for (int i = 0; i < result.size(); i++) {

			JsonObject entry = result.get(i).getAsJsonObject();
			if (entry.get("name").getAsString().equalsIgnoreCase(state.trim())
					|| entry.get("abbr").getAsString().equalsIgnoreCase(state.trim())) {

				if (entry.has("largest_city")) {
					System.out.println("Largest City => " + entry.get("largest_city").getAsString());
				} else {
					System.out.println("Sorry, the largest City did not exist in our system.");
				}

				if (entry.has("capital")) {
					System.out.println("Capital => " + entry.get("capital").getAsString());
				} else {
					System.out.println("Sorry, the capital did not exist in our system.");
				}

				break;
			}

		}

		System.out.println(
				"Sorry, the state '" + state + "' was invalid.  Please enter a valid state name or abbreviation:");

	}

}
