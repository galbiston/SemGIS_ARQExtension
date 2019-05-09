package de.hsmainz.cs.semgis.arqextension.polygon;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

/**
 * Orients all exterior rings clockwise and all interior rings counter-clockwise. 
 *
 */
public class ForcePolygonCW extends FunctionBase1{

	@Override
	public NodeValue exec(NodeValue v) {
		GeometryWrapper geom1 = GeometryWrapper.extract(v);
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
