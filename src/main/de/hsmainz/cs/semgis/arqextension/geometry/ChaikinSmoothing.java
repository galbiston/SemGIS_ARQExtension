package de.hsmainz.cs.semgis.arqextension.geometry;

import java.math.BigInteger;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class ChaikinSmoothing extends FunctionBase3{

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3) {
		GeometryWrapper geom1 = GeometryWrapper.extract(v1);
		BigInteger nIterations=v2.getInteger();
		Boolean preserveEndpoints=v3.getBoolean();
        throw new UnsupportedOperationException("Not supported yet.");
	}

}
