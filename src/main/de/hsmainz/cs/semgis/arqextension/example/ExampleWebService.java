package de.hsmainz.cs.semgis.arqextension.example;

import java.util.Arrays;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/rest")
public class ExampleWebService {

	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/queryservice")
    public static String queryService(@QueryParam("query") String query) { 
		return Arrays.toString(TripleStoreConnection.executeQuery(query));
	}

	
	
	
	public static void main (String[] args) {
		TripleStoreConnection.executeQuery("");
	}
	
}
