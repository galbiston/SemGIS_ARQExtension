package de.hsmainz.cs.semgis.arqextension.geometry;

import java.math.BigInteger;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

/**
 * Return a GeoHash representation of the geometry
 *
 */
public class GeoHash extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		GeometryWrapper geom1 = GeometryWrapper.extract(v1);
		BigInteger maxchars=v2.getInteger();
        throw new UnsupportedOperationException("Not supported yet.");
	}

}
