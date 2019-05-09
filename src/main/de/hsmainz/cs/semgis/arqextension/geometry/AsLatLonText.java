package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

/**
 * Return the Degrees, Minutes, Seconds representation of the given point.
 *
 */
public class AsLatLonText extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		GeometryWrapper geometry = GeometryWrapper.extract(v1);
        Geometry geom = geometry.getXYGeometry();
		String format=v2.getString();
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
