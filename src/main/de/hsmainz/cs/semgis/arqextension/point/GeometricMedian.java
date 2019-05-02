package de.hsmainz.cs.semgis.arqextension.point;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

/**
 * Returns the geometric median of a MultiPoint. 
 *
 */
public class GeometricMedian extends FunctionBase1{

	@Override
	public NodeValue exec(NodeValue arg0) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getXYGeometry();
        	throw new UnsupportedOperationException("Not supported yet.");
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
