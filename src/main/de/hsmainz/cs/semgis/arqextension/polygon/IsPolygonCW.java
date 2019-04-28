package de.hsmainz.cs.semgis.arqextension.polygon;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.algorithm.Orientation;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class IsPolygonCW extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
        return null;
	}
	
}
