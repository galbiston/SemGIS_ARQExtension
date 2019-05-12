package de.hsmainz.cs.semgis.arqextension.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.ModelFactory;

import de.hsmainz.cs.semgis.arqextension.PostGISConfig;
import io.github.galbiston.geosparql_jena.configuration.GeoSPARQLConfig;

public class TripleStoreConnection {

	public static final TripleStoreConnection INSTANCE=new TripleStoreConnection();
	
	public static final String prefixCollection="PREFIX geo: <http://www.opengis.net/ont/geosparql#>";
	
	public OntModel model;
	
	public TripleStoreConnection() {
		GeoSPARQLConfig.setupMemoryIndex();
		PostGISConfig.setup();
		model = ModelFactory.createOntologyModel();
		try {

			model.read(new FileInputStream("testdata.ttl"),null,"TTL");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String executeQuery(String query) {
		query=prefixCollection+query;
		System.out.println(query);
		try (QueryExecution qe = QueryExecutionFactory.create(query, INSTANCE.model)) {
		    ResultSet rs = qe.execSelect();
		    return ResultSetFormatter.asXMLString(rs);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(TripleStoreConnection.INSTANCE.executeQuery("SELECT ?geom ?wkt WHERE { ?geom geo:asWKT geo:ST_StartPoint(?wkt) }"));
	}
	
}
