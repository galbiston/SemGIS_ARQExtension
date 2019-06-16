package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class LocateAlong extends FunctionBase3{

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1, NodeValue arg2) {
		GeometryWrapper geom1 = GeometryWrapper.extract(arg0);
        Double a_measure=arg1.getDouble();
        Double offest=arg2.getDouble();
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
