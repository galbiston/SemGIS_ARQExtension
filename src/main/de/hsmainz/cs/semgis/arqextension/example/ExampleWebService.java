package de.hsmainz.cs.semgis.arqextension.example;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class ExampleWebService {

	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String queryService(String query) {
		return TripleStoreConnection.executeQuery(query);
	}

	
	
	
	public static void main (String[] args) {
		TripleStoreConnection.executeQuery("");
	}
	
}
