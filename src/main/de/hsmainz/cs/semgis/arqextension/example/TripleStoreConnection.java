package de.hsmainz.cs.semgis.arqextension.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.ModelFactory;

import de.hsmainz.cs.semgis.arqextension.PostGISConfig;
import io.github.galbiston.geosparql_jena.configuration.GeoSPARQLConfig;

public class TripleStoreConnection {

	public static final TripleStoreConnection INSTANCE=new TripleStoreConnection();
	
	public static final String prefixCollection="PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+System.lineSeparator()+"PREFIX geo: <http://www.opengis.net/ont/geosparql#>";
	
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
	
	public static String[] executeQuery(String query) {
		query=prefixCollection+query;
		System.out.println(query);
		try (QueryExecution qe = QueryExecutionFactory.create(query, INSTANCE.model)) {
		    ResultSet rs = qe.execSelect();
		    List<QuerySolution> test=ResultSetFormatter.toList(rs);
		    System.out.println(test.size()+" Results ");
		    return new String[] {test.toString(),test.size()+""};
		}
	}
	
	public static void main(String[] args) {
		String[] res=TripleStoreConnection.INSTANCE.executeQuery("SELECT ?geom ?wkt WHERE { ?geom geo:asWKT ?wkt . FILTER(!geo:ST_IsCollection(?wkt)) }");
		//System.out.println(res[0]);
		System.out.println(res[1]);
		System.out.println("=====================================================================================================");
		res=TripleStoreConnection.INSTANCE.executeQuery("SELECT ?geom ?wkt WHERE { ?geom geo:asWKT ?wkt . FILTER(geo:ST_Area(geo:ST_SimplifyVW(?wkt))>10) }");
		//System.out.println(res[0]);
		System.out.println(res[1]);
	}
	
}
