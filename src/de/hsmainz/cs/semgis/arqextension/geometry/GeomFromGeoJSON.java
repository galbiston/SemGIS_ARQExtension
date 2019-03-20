package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;
import org.wololo.jts2geojson.GeoJSONReader;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class GeomFromGeoJSON extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		String geojson=v.getString();
		GeoJSONReader reader = new GeoJSONReader();
		Geometry geom = reader.read(geojson);
        GeometryWrapper pointWrapper = GeometryWrapper.createGeometry(geom, "<http://www.opengis.net/def/crs/EPSG/0/"+geom.getSRID()+">", WKTDatatype.URI);	
        return pointWrapper.asNodeValue();  
	}

}
