package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Geometry;
import org.wololo.jts2geojson.GeoJSONReader;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
/**
 * Return a geometry from a GeoHash string.
 *
 */
public class GeomFromGeoHash extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v, NodeValue v2) {
		String geojson=v.getString();
		Double precision=v2.getDouble();
        throw new UnsupportedOperationException("Not supported yet."); 
	}

}
