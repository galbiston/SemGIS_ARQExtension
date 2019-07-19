package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

/**
 * Compute a straight skeleton from a geometry
 *
 */
public class StraightSkeleton extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		GeometryWrapper geometry = GeometryWrapper.extract(v);
        Geometry geom = geometry.getParsingGeometry();
        throw new UnsupportedOperationException("Not supported yet.");
	}

}
