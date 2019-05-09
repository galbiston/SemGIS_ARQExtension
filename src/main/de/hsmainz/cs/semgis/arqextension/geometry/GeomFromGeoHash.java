package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
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
