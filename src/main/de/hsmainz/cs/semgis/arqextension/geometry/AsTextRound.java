package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.precision.GeometryPrecisionReducer;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

/**
 * Exports the geometry as well known text with a fixed number of digits after the comma.
 *
 */
public class AsTextRound extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(v1);
            Geometry geom = geometry.getXYGeometry();
            PrecisionModel pm = new PrecisionModel(Math.pow(10.0, v2.getDouble()));
            Geometry geom_mod=GeometryPrecisionReducer.reduce(geom, pm);
            return NodeValue.makeString(geom_mod.toText());
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}